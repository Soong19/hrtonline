package com.hrt.manage.security;

import cn.hutool.json.JSONUtil;
import com.hrt.commonutils.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

        // 生成jwt，并放置到请求头中

        // 返回类的编写和视频不一样，可能出错，注意
        R r = R.ok();
        servletOutputStream.write(JSONUtil.toJsonStr(r).getBytes(StandardCharsets.UTF_8));
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
