package edu.xiao.java01jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.xiao.java01jwt.constant.ResponseCodeEnum;
import edu.xiao.java01jwt.model.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 自定义授权失败处理
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * Handles an access denied failure.
     *
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param response              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException      in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 设置响应类型
        response.setContentType("application/json;charset=UTF-8");
        // 设置响应状态码
        String json = new ObjectMapper().writeValueAsString(Result.error(ResponseCodeEnum.FORBIDDEN_ERROR_403));
        response.getWriter().println(json);
        response.getWriter().flush();
    }
}
