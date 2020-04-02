package org.linn.center.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * created by linn  20/3/31 13:18
 **/
@Entity
@Table(name = "rocket_transaction_log")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RocketTransactionLog {

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

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "log")
    private String log;
}
