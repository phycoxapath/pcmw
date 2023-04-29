package indi.gd.pcmw.controller.interceptor;

import indi.gd.pcmw.controller.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("in interceptor");
        if (request.getMethod().equals("OPTIONS")){
            return true;
        }
        String token = request.getHeader("Authorization");
        if (null != token && !"".equals(token)) {
            System.out.println("has token");
            int flag = JwtUtil.verifyToken(token);
            System.out.println("verify:"+flag);
            if (flag == 1){
                response.setHeader("Access-Control-Expose-Headers", "TokenExpired");
                response.setHeader("TokenExpired","true");
            }else if (flag == 2){
                response.setStatus(401);
                return false;
            }
        }else {
            System.out.println("has not token");
            response.setStatus(401);
            return false;
        }
        return true;
    }


}
