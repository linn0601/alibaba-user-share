package org.linn.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * created by linn  20/3/13 9:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareDTO {

    private String id;
    //发布人ID
    private String userId;
    //标题
    private String title;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //是否原创
    private Boolean isOriginal;
    //作者
    private String author;
    //封面
    private String cover;
    //概要信息
    private String summary;
    //价格
    private Integer price;
    //下载url
    private String downloadUrl;
    //下载数
    private Integer buyCount;
    //是否显示
    private Boolean showFlag;
    //审核状态 NOT_YET 待审核 PASS 审核通过
    private String auditStatus;
    //审核不通过原因
    private String reason;
    //微信昵称
    private String wxNickname;
}
