package org.linn.center.dao;


import org.linn.center.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * (Share)表数据库访问层
 */
@Repository
public interface ShareDao extends JpaRepository<Share, String> {

}