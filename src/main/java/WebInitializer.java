import web.config.WebConfig;
import core.config.CoreConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebConfig.class);

        DispatcherServlet dispatcherServletSpring = new DispatcherServlet(rootContext);
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("DispatcherServlet", dispatcherServletSpring);
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setLoadOnStartup(1);

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(CoreConfig.class);
        servletContext.addListener(new ContextLoaderListener(applicationContext));
    }
}
