package com.aeria.electroBalance.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	@Value("${extern.resoures.path}")
	private String path;
	@Value("${extern.resources.Dir}")
	private String resourceDir;
	
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/Func_resources/**").addResourceLocations("classpath:/static/Func_resources/");
		registry.addResourceHandler("/"+resourceDir+"/**").addResourceLocations("file:"+path);
	}
}
