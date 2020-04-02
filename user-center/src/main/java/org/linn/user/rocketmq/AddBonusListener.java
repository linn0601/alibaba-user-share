package org.linn.user.rocketmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.linn.common.domain.message.UserAddBonusMsgDTO;
import org.linn.user.dao.BonusEventLogDao;
import org.linn.user.dao.UserDao;
import org.linn.user.entity.BonusEventLog;
import org.linn.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * created by linn  20/3/30 20:12
 * strOptional.ifPresentOrElse(System.out::println, () -> System.out.println("Null"));
 **/
@Service
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {

    private final UserDao userDao;
    private final BonusEventLogDao bonusEventLogDao;

    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        //当收到消息的时候执行的业务
        String userId = userAddBonusMsgDTO.getId();
        Optional<User> user = userDao.findById(userId);

        user.ifPresent(u -> {
                    //1.为用户添加积分
            Integer bonus = userAddBonusMsgDTO.getBonus();
            userDao.save(User.builder()
                            .id(userAddBonusMsgDTO.getId())
                            .bonus(u.getBonus()+ bonus)
                            .build());
                    //2.记录日志
                    bonusEventLogDao.save(
                            BonusEventLog.builder()
                                    .userId(u.getId())
                                    .value(bonus)
                                    .event("CONTRIBUTE")
                                    .description("加积分")
                                    .createTime(new Date())
                                    .build());
                    log.info("积分添加完毕");
                }
        );
    }
}
