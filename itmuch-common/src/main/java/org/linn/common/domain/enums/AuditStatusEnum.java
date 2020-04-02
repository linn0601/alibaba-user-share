package org.linn.common.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * created by linn  20/3/15 14:43
 **/
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    //待审核
    NOT_YET,
    //通过
    PASS,
    //不通过
    REJECT
}
