package com.huzhirong.ssm.common.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.huzhirong.ssm.common.utils.JWTUtils;
import com.huzhirong.ssm.common.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的令牌
        String token = request.getHeader("token");
        // 校验令牌
        try {
            JWTUtils.verify(token);
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().println(R.ok().put("msg", "请求成功"));
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
        } catch (TokenExpiredException e) {
            e.printStackTrace();
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 出现异常，响应错误信息
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(R.error(401, "token无效，请重新登录"));
        return false;
    }
}
