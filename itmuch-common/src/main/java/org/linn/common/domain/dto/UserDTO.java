package org.linn.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * created by linn  20/3/13 9:07
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 475638672669131032L;

    private String id;

    private String username;
    //微信ID
    private String wxId;
    //微信昵称
    private String wxNickname;
    //角色
    private String roles;
    //头像地址
    private String avatarUrl;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //积分
    private Integer bonus;
}
