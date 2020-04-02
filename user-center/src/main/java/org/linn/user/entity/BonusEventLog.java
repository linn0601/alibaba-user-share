package org.linn.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * created by linn  20/3/25 22:00
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bonus_event_log")
public class BonusEventLog {

    @Id
    //为实体类生成一个唯一表示的主键，@GenerateValue提供了主键的生成策略
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    //自定义主键生成策略
    @GenericGenerator(
            name = "uuid2",
            strategy = "uuid2",
            parameters = {@org.hibernate.annotations.Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
            }
    )
    private String id;

    @Column(name = "user_id")
    private String userId;
    //积分操作的值
    @Column(name = "value")
    private Integer value;
    //事件
    @Column(name = "event")
    private String event;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "description")
    private String description;
}
