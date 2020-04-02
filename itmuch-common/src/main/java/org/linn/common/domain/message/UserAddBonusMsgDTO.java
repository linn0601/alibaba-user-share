package org.linn.common.domain.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by linn  20/3/29 21:18
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAddBonusMsgDTO {
    //用户id
    private String id;
    //加多少积分
    private Integer bonus;
}
