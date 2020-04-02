package org.linn.center.dao;


import org.linn.center.entity.RocketTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * (RocketTransactionLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-31 13:22:28
 */
@Repository
public interface RocketTransactionLogDao extends JpaRepository<RocketTransactionLog, String> {

    Optional<RocketTransactionLog> findByTransactionId(String transactionId);

}