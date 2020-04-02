package org.linn.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.linn.user.dao.UserDao;
import org.linn.user.entity.User;
import org.linn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-03-12 19:13:09
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return  userDao.findById(id);
    }
}