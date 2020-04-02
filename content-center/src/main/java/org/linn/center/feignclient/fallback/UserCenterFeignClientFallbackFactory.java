package org.linn.center.feignclient.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.linn.center.feignclient.UserCenterFeignClient;
import org.linn.common.domain.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * created by linn  20/3/29 14:12
 **/
@Component
@Slf4j
public class UserCenterFeignClientFallbackFactory implements FallbackFactory<UserCenterFeignClient> {

    @Override
    public UserCenterFeignClient create(Throwable throwable) {
        return new UserCenterFeignClient() {

            @Override
            public UserDTO findById(String id) {
                log.warn("远程调用 user-center 失败进入异常处理 :", throwable);
                return UserDTO.builder()
                        .wxNickname("一个默认用户")
                        .build();
            }
        };
    }
}
