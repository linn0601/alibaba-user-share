package org.linn.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.linn.user.dao.BonusEventLogDao;
import org.linn.user.entity.BonusEventLog;
import org.linn.user.service.BonusEventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * created by linn  20/3/25 22:21
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BonusEventLogServiceImpl implements BonusEventLogService {

    private final BonusEventLogDao bonusEventLogDao;

    @Override
    public BonusEventLog save(BonusEventLog bonusEventLog) {
        return bonusEventLogDao.save(bonusEventLog);
    }

    @Override
    public Optional<BonusEventLog> findById(String id) {
        return bonusEventLogDao.findById(id);
    }
}
