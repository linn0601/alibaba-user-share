package org.linn.user.dao;

import org.linn.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * (User)表数据库访问层
 */
@Repository
public interface UserDao extends JpaRepository<User,String> {

}