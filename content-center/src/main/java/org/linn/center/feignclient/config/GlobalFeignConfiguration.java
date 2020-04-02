package org.linn.center.feignclient.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * created by linn  20/3/28 17:37
 * feign打印日志级别 并在注解@FeignClient(configuration中配置该类)
 * 注意：如果加@configuration注解需要将该类移到启动类所在的包外面，不然会被重复扫描(@componentScan)。
 **/
public class GlobalFeignConfiguration {

    @Bean
    public Logger.Level level(){
        //指定请求的级别
        return Logger.Level.FULL;
    }
}
