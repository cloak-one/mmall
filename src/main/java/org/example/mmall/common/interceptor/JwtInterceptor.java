package org.example.mmall.common.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mmall.response.api.ResultCode;
import org.example.mmall.exception.Asserts;
import org.example.mmall.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private JwtTokenUtil jwtToken;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(tokenHeader);
        //执行认证
        boolean flag = false;
        if(StringUtils.isBlank(authHeader)){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            flag = jwtToken.verify(authToken);

        }
        return flag;
    }

}
