package org.linn.center.feignclient;

import org.linn.center.feignclient.fallback.UserCenterFeignClientFallback;
import org.linn.center.feignclient.fallback.UserCenterFeignClientFallbackFactory;
import org.linn.common.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * created by linn  20/3/28 16:34
 * configuration = FeignClientConfiguration.class 配置类，配置日志
 **/
//@FeignClient(name = "user-center", configuration = FeignClientConfiguration.class)
@FeignClient(name = "user-center",
        fallbackFactory = UserCenterFeignClientFallbackFactory.class
        //fallback = UserCenterFeignClientFallback.class
        )
/**
 *  一旦远程调用失败了被流控了，那么就会UserCenterFeignClientFallback.class 类进行处理
 */
public interface UserCenterFeignClient {

    /**
     * http://user-center/user/{id}
     * 在内容服务根据用户id去用户服务查找用户信息
     * @param id 用户ID
     */
    @GetMapping("/user/{id}")
    UserDTO findById(@PathVariable String id);
}
