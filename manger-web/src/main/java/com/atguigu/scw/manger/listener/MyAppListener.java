package com.atguigu.scw.manger.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 项目启动的时候，将一些常用的数据放在application域中
 * 需要在web.xml中配置
 *
 */
public class MyAppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        //1.项目路径存在appication域中
        servletContext.setAttribute("ctp",servletContext.getContextPath());

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //2.项目关闭，销毁域中所有数据

    }
}
