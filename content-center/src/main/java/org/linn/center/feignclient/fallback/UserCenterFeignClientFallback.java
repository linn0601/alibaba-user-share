package org.linn.center.feignclient.fallback;

import org.linn.center.feignclient.UserCenterFeignClient;
import org.linn.common.domain.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * created by linn  20/3/29 13:30
 **/
@Component
public class UserCenterFeignClientFallback implements UserCenterFeignClient {

    @Override
    public UserDTO findById(String id) {
        return UserDTO.builder()
                .wxNickname("一个默认用户")
                .build();
    }
}
