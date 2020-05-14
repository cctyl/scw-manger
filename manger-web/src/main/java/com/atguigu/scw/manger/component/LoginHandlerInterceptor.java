package com.atguigu.scw.manger.component;

import com.atguigu.scw.manger.bean.TUser;
import com.atguigu.scw.manger.constant.MyConstants;
import com.atguigu.scw.manger.service.UserService;
import com.atguigu.scw.manger.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作为扩展功能在MyMvcConfi类的addInterceptors方法中 被使用
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;
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

            //判断是否携带了自动登录的token
            String logintoken = CookieUtils.getCookieValue(request, "autologin");
            if (logintoken!=null){
                //已经携带了token，允许自动登录
                //查询redis，找出对应的userid
                //直接用token查询用户，详细情况在service中实现
                TUser autoLoginUser =  userService.getUserByToken(logintoken);
                if (autoLoginUser==null){
                    //没有和这个token对应的用户，那就不放行，让他去登录
                    return false;
                }

                //将用户放入session中
                request.getSession().setAttribute(MyConstants.LOGIN_USER,autoLoginUser);

                return true;
            }else {
                //没有登录，也没有携带token，就是没有登录
                //未登陆，返回登陆页面
                request.setAttribute("msg","没有权限请先登陆");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return false;
            }



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
