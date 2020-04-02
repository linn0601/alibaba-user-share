package org.linn.center.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (Share)实体类
 */
@Data
@Entity
@Table(name = "share")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate(value = true)
public class Share implements Serializable {

    private static final long serialVersionUID = 596305568486735826L;

    @Id
    @GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO) //generator主键生成器的名称  strategy生成策略：默认由程序决定
    @GenericGenerator(
            name = "uuid2",
            strategy = "uuid2",
            parameters = {@org.hibernate.annotations.Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
            })
    private String id;
    //发布人ID
    @Column(name = "user_id")
    private String userId;

    //标题
    @Column(name = "title")
    private String title;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //更新时间
    @Column(name = "update_time")
    private Date updateTime;

    //是否原创
    @Column(name = "is_original")
    private Boolean isOriginal;

    //作者
    @Column(name = "author")
    private String author;

    //封面
    @Column(name = "cover")
    private String cover;

    //概要信息
    @Column(name = "summary")
    private String summary;

    //价格
    @Column(name = "price")
    private Integer price;

    //下载url
    @Column(name = "download_url")
    private String downloadUrl;

    //下载数
    @Column(name = "buy_count")
    private Integer buyCount;

    //是否显示
    @Column(name = "show_flag")
    private Boolean showFlag;

    //审核状态 NOT_YET 待审核 PASS 审核通过
    @Column(name = "audit_status")
    private String auditStatus;

    //审核不通过原因
    @Column(name = "reason")
    private String reason;

}