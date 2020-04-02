package org.linn.center.service;

import org.linn.center.entity.Share;
import org.linn.common.base.BaseService;
import org.linn.common.domain.dto.ShareAuditDTO;

import java.util.Optional;

/**
 * (Share)表服务接口
 */
public interface ShareService extends BaseService<Share> {

    Optional<Share> auditById(String id, ShareAuditDTO shareAuditDTO);

    void auditByIdWithRocketMqLog(String id, ShareAuditDTO auditDTO , String transactionId);

}