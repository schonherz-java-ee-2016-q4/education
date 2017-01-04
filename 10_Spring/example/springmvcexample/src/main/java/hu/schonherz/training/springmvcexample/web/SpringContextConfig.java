package hu.schonherz.training.springmvcexample.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc // You need this annotation so Spring will execute the methods within properly
public class SpringContextConfig extends WebMvcConfigurerAdapter {
    /*
        WebMvcConfigurerAdapter is a helper class to easily configure Spring's Web MVC aspects by
        overriding methods that you need.
    */

    /**
     * This method will be executed automatically if @EnableWebMvc is present and
     * will register new ViewResolvers into the pack of existing ones.
     *
     * the registry parameter is a special Spring-managed container of ViewResolvers.
     */
    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        registry.viewResolver(new InternalResourceViewResolver("/WEB-INF/jsp/", ".jsp"));
    }

    /**
     * This method will be executed automatically if @EnableWebMvc is present and
     * will register new ResourceHandlers into the pack of existing ones.
     *
     * the registry parameter is a special Spring-managed container of ResourceHandlers.
     *
     * This particular method will enable you to use /resources/css/style.css in your JSP
     * and it will be resolved to /WEB-INF/resources/css/style.css by Spring
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resources/**")
            .addResourceLocations("/WEB-INF/resources/"); // DON'T forget to end your resource location with a /!!
    }
}
