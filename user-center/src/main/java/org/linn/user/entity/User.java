package org.linn.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
@Entity
@DynamicUpdate(value = true)
public class User implements Serializable {

    private static final long serialVersionUID = 475638672669131032L;

    @Id
    //@GeneratedValue提供了主键的生成策略
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO) //generator主键生成器的名称  strategy生成策略：默认由程序决定
    @GenericGenerator(
            name = "uuid2",
            strategy = "uuid2",
            parameters = {@org.hibernate.annotations.Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
            }
    )
    private String id;

    @Column(name = "username")
    private String username;
    //微信ID
    @Column(name = "wx_id")
    private String wxId;
    //微信昵称
    @Column(name = "wx_nickname")
    private String wxNickname;
    //角色
    @Column(name = "roles")
    private String roles;
    //头像地址
    @Column(name = "avatar_url")
    private String avatarUrl;
    //创建时间
    @Column(name = "create_time")
    private Date createTime;
    //更新时间
    @Column(name = "update_time")
    private Date updateTime;
    //积分
    @Column(name = "bonus")
    private Integer bonus;

}