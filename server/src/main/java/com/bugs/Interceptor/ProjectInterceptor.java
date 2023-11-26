package com.bugs.Interceptor;

import com.bugs.dao.UserDao;
import com.bugs.domain.User;
import com.bugs.exception.ServiceException;
import com.bugs.utils.jwtUtils;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class ProjectInterceptor implements HandlerInterceptor {
    @Autowired
    UserDao userDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isNullOrEmpty(token)) {
            throw new ServiceException("需要先登录");
        }
        Map<String, Object> map = jwtUtils.parseToken(token);
        if (map == null) {
            throw new ServiceException("需要先登录");
        }
        // TODO 需不需要验证一下这个userId用户是否存在
        User user = userDao.getUserByUserid((String) map.get("userId"));
        if (user == null) {
            throw new ServiceException("需要先登录");
        }
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("...");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("...");
//    }
}
