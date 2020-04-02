package org.linn.center.sentinel.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * created by linn  20/3/29 15:52
 **/
@Component
public class CustomBlockHandler implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        ErrorMessage message = null;
        if (e instanceof FlowException) {
            message = ErrorMessage.builder().status(100)
                    .msg("访问http限流操作").build();
        } else if (e instanceof DegradeException) {
            message = ErrorMessage.builder().status(101)
                    .msg("访问http降级操作").build();
        } else if (e instanceof ParamFlowException) {
            message = ErrorMessage.builder().status(102)
                    .msg("热点参数限流操作").build();
        } else if (e instanceof SystemBlockException) {
            message = ErrorMessage.builder().status(103)
                    .msg("系统不满足规则").build();
        } else if (e instanceof AuthorityException) {
            message = ErrorMessage.builder().status(104)
                    .msg("授权规则不通过").build();
        }

        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        new ObjectMapper()
                .writeValue(response.getWriter(), message);
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ErrorMessage {

    private String msg;
    private Integer status;

}
