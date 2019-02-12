package com.moveingroup.security.front;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
@Order(99)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Bean
//    public ViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver bean = new InternalResourceViewResolver();
//        bean.setPrefix("/");
//        bean.setRedirectHttp10Compatible(false);
//        return bean;
//    }
//	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    
		http.csrf().disable();

		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.anyRequest().permitAll()
		
				.and().logout().permitAll().logoutUrl("/logout").logoutSuccessHandler(new LogoutHandler()).and()
				.exceptionHandling().accessDeniedPage("/403.xhtml").and()
				.addFilterBefore(new JwtFilterFront(), UsernamePasswordAuthenticationFilter.class);
		
		http.sessionManagement().maximumSessions(1).expiredUrl("/explotacion/olvidoPassword.xhtml**");
	}
}
