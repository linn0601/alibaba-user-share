package org.linn.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.linn.common.domain.enums.AuditStatusEnum;

/**
 * created by linn  20/3/15 14:41
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareAuditDTO {

    //审核状态
    private AuditStatusEnum auditStatusEnum;
    //原因
    private String reason;
}
