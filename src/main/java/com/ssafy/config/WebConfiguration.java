package com.ssafy.config;

import com.ssafy.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
	private JWTInterceptor jwtInterceptor;

	public WebConfiguration(JWTInterceptor jwtInterceptor) {
		this.jwtInterceptor = jwtInterceptor;
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
//				.allowedOrigins("http://localhost:8080", "http://localhost:8081")
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
//				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
				.maxAge(1800);
	}

}
