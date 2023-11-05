package com.iloveyou;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class ServerConfiguration implements WebMvcConfigurer {
	@Value("${spring.data.rest.base-path}")
	private String path;

	public static final boolean USE_AUTH = true;

	@Autowired
	AuthenticationFilter authenticationFilter;

	@Autowired
	AuthorizationFilter authorizationFilter;

	@Autowired
	BodyFilter entityFilter;

	@Bean
	public FilterRegistrationBean<AuthenticationFilter> registerAuthenticationFilter() {
		final FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(authenticationFilter);
		registrationBean.setOrder(1);
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<AuthorizationFilter> registerAuthorizationFilter() {
		final FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(authorizationFilter);
		registrationBean.setOrder(2);
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<BodyFilter> registerBodyFilter() {
		final FilterRegistrationBean<BodyFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(entityFilter);
		registrationBean.setOrder(3);
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix(path, (clazz) -> true);
	}
}
