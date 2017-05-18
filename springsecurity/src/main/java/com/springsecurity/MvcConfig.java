package com.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.springsecurity.events.EventService;
import com.springsecurity.events.api.APIScheduler;
import com.springsecurity.user.UserService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com")
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	@Autowired private UserService userService;
	@Autowired private EventService eventService;
	
	 @Bean
	    public ViewResolver viewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	       // viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/jsp/");
	        viewResolver.setSuffix(".jsp");
	        
	        new APIScheduler(userService,eventService);
				
	        return viewResolver;
	    }
	 
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	    }
}
