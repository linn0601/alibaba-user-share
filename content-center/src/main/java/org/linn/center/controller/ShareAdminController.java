package org.linn.center.controller;

import lombok.RequiredArgsConstructor;
import org.linn.center.entity.Share;
import org.linn.center.service.ShareService;
import org.linn.common.domain.dto.ShareAuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * created by linn  20/3/15 14:39
 **/
@RestController
@RequestMapping("/admin/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareAdminController {

    private final ShareService shareService;

    @PutMapping("/audit/{id}")
    public Optional<Share> auditById(@PathVariable String id , @RequestBody ShareAuditDTO shareAuditDTO){
        //TODO 认证授权
        return shareService.auditById(id, shareAuditDTO);
    }

}
