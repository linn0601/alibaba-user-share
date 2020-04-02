package org.linn.user.dao;

import org.linn.user.entity.BonusEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by linn  20/3/25 22:18
 **/
@Repository
public interface BonusEventLogDao extends JpaRepository<BonusEventLog, String> {
}
