package org.linn.center.sentinel.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * created by linn  20/3/29 16:37
 **/
@Component
public class CustomUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String s) {
        String[] split = s.split("/");
        return Arrays.stream(split).map(string -> {
            if (NumberUtils.isNumber(string)) {
                return "{num}";
            }
            return string;
        }).reduce((a, b) -> a + "/" + b).orElse("");
    }
}
