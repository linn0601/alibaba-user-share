package org.linn.center.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.linn.center.dao.ShareDao;
import org.linn.center.entity.Share;
import org.linn.center.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * (Share)表控制层
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {

    private final ShareService shareService;
    private final ShareDao shareDao;

    @GetMapping("/share/{id}")
    public Optional<?> selectOne(@PathVariable String id) {
        return shareService.findById(id);
    }

    @PutMapping("/share")
    public Share shareDao(){
        Share share = Share.builder()
                .id("2")
                .reason("我喜欢你")
                .build();
        return shareDao.saveAndFlush(share);
    }

}