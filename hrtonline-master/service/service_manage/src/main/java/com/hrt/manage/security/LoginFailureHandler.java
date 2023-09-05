package com.hrt.manage.security;


import cn.hutool.json.JSONUtil;
import com.hrt.commonutils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        System.out.println("进入了权限失败处理方法");

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        R r = R.error().message("没有权限访问");
        servletOutputStream.write(JSONUtil.toJsonStr(r).getBytes(StandardCharsets.UTF_8));
        servletOutputStream.flush();
        servletOutputStream.close();

    }

}
