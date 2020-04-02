package org.linn.center.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.linn.center.dao.RocketTransactionLogDao;
import org.linn.center.dao.ShareDao;
import org.linn.center.entity.RocketTransactionLog;
import org.linn.center.entity.Share;
import org.linn.center.feignclient.UserCenterFeignClient;
import org.linn.center.service.ShareService;
import org.linn.common.domain.dto.ShareAuditDTO;
import org.linn.common.domain.dto.ShareDTO;
import org.linn.common.domain.dto.UserDTO;
import org.linn.common.domain.enums.AuditStatusEnum;
import org.linn.common.domain.message.UserAddBonusMsgDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * (Share)表服务实现类
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {

    private final ShareDao shareDao;
    private final UserCenterFeignClient userCenterFeignClient;
    //private final RabbitSend rabbitSend;
    private final RocketMQTemplate rocketMQTemplate;
    private final RocketTransactionLogDao rocketTransactionLogDao;

    @Override
    public Share save(Share share) {
        return shareDao.save(share);
    }

    @Override
    @SentinelResource(value = "findById")
    public Optional<?> findById(String id) {
        //获取分享详情
        Optional<Share> share = shareDao.findById(id);
        String userId = share.map(Share::getUserId).orElse("");
        //使用http请求
        UserDTO userDTO = userCenterFeignClient.findById(userId);
        //消息装配
        ShareDTO shareDTO = new ShareDTO();
        BeanUtils.copyProperties(share.orElse(new Share()), shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return Optional.of(shareDTO);
    }

    @Override
    public Optional<Share> auditById(String id, ShareAuditDTO auditDTO) {
        //1.查询share是否存在，不存在或者当前的audit_status != NOT-YET 抛异常;
        Optional<Share> share = this.shareDao.findById(id);
        share.ifPresent(obj -> {
            if (!"NOT_YET".equals(obj.getAuditStatus()))
                throw new IllegalArgumentException("该分享已经审核通过");
            System.out.println(AuditStatusEnum.PASS);
            //3.如果为Pass 为发布人添加积分
            if (AuditStatusEnum.PASS.equals(auditDTO.getAuditStatusEnum())) {
                //如果为通过就发送半消息
                String uuid = UUID.randomUUID().toString();
                this.rocketMQTemplate.sendMessageInTransaction(
                        "tx-add-bonus-group",
                        "add-bonus",
                        MessageBuilder.withPayload(
                                UserAddBonusMsgDTO.builder()
                                        .id(obj.getUserId())
                                        .bonus(50)
                                        .build()
                        ).setHeader(RocketMQHeaders.TRANSACTION_ID, uuid)
                                .setHeader("share_id", id)
                                .build(),
                        auditDTO
                );
            }else {
                //只做数据库审核
                this.auditByIdInDB(id, auditDTO);
            }
            //提交本地事务，将状态修改成功
           /* this.auditByIdInDB(id,auditDTO);*/
            //2.审核资源,将状态设置为PASS/REJECT
        });
        return share;
    }

    @Transactional(rollbackFor = Exception.class)
    public void auditByIdInDB(String id, ShareAuditDTO auditDTO) {
        //更新不为空的值，需要开启@DynamicUpdate(value = true)注解
        Optional<Share> share1 = shareDao.findById(id);
        Share share = share1.get();
        share.setAuditStatus(auditDTO.getAuditStatusEnum().toString());
        share.setReason(auditDTO.getReason());
        this.shareDao.save(share);
        //写入缓存
    }

    @Transactional(rollbackFor = Exception.class)
    public void auditByIdWithRocketMqLog(String id, ShareAuditDTO auditDTO , String transactionId){
        this.auditByIdInDB(id,  auditDTO);
        this.rocketTransactionLogDao.save(
          RocketTransactionLog.builder()
                  .id(id)
                  .transactionId(transactionId)
                  .log("审核分享。。。。")
                  .build()
        );
    }
}