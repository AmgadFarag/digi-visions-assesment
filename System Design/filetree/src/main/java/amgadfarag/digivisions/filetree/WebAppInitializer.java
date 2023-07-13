// package amgadfarag.digivisions.filetree;

// import org.springframework.web.WebApplicationInitializer;
// import org.springframework.web.context.ContextLoaderListener;
// import org.springframework.web.context.WebApplicationContext;
// import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
// import org.springframework.web.servlet.DispatcherServlet;

// import jakarta.servlet.ServletContext;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRegistration;

// public class WebAppInitializer implements WebApplicationInitializer {

//     @Override
//     public void onStartup(ServletContext servletContext) throws ServletException {
//         WebApplicationContext context = getContext();
//         servletContext.addListener(new ContextLoaderListener(context));

//         ServletRegistration.Dynamic dispatcher = servletContext.addServlet("eventTrackerDispatcherServlet", new DispatcherServlet(context));

//         dispatcher.setLoadOnStartup(1);
//         dispatcher.addMapping("*.html");
//     }

//     private AnnotationConfigWebApplicationContext getContext() {
//         AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//         context.register(amgadfarag.digivisions.filetree.config.WebConfig.class);

//         return context;
//     }
// }