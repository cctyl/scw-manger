package com.atguigu.scw.manger.component;

import com.atguigu.scw.manger.constant.MyConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作为扩展功能在MyMvcConfi类的addInterceptors方法中 被使用
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行前执行的
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute(MyConstants.LOGIN_USER);
        if(user == null){

            //未登陆，返回登陆页面
            request.setAttribute("msg","没有权限请先登陆");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return false;
        }else{
            //已登陆，放行请求
            return true;
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
