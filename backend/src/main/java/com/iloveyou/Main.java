package com.iloveyou;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableJpaRepositories
public class Main {

	// @Value("${jwt.secret}")
	// private String secret;

	// private static void addUrlPatterns(FilterRegistrationBean registrationBean) {
    //     registrationBean.addUrlPatterns("/api/animals");
	// 	registrationBean.addUrlPatterns("/api/account");
	// 	registrationBean.addUrlPatterns("/api/role/*");
	// }

	// @Bean
    // public FilterRegistrationBean<AuthenticationFilter> registerAuthenticationFilter() {
    //     final FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
    //     registrationBean.setFilter(new AuthenticationFilter(secret));
	// 	addUrlPatterns(registrationBean);
	// 	registrationBean.setOrder(1);

    //     return registrationBean;
    // }

	// @Bean
    // public FilterRegistrationBean<AuthorizationFilter> registerAuthorizationFilter() {
    //     final FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
    //     registrationBean.setFilter(new AuthorizationFilter());
	// 	addUrlPatterns(registrationBean);
	// 	registrationBean.setOrder(2);
		
    //     return registrationBean;
    // }

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
