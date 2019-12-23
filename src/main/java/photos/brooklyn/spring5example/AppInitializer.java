package photos.brooklyn.spring5example;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * This replaces the older way of using xml and specifying component scan
 */
public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.scan("photos.brooklyn.spring5example");
        container.addListener(new ContextLoaderListener(ctx));
        ServletRegistration.Dynamic dispatcher = container.addServlet("mvc", new DispatcherServlet(ctx));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
