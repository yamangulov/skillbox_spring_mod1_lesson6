package org.example;

import org.example.web.config.WebContextConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Andrey.Yamangulov
 * Date: 14.08.2021
 * Time: 22:05
 */
public class WebAppInitializer implements WebApplicationInitializer {
    Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("loading application config");
        XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
        applicationContext.setConfigLocation("classpath:app-config.xml");
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        logger.info("loading web config");
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(WebContextConfig.class);


        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        logger.info("dispatcher is ready");
    }
}
