package com.moveingroup.security.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.moveingroup.services.UserAccountService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAccountService userAccountService;

	private String loginDatosPersonales = "/loginDniTlf";

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.csrf().disable();

		http.authorizeRequests()

				.anyRequest().authenticated()

				.and().exceptionHandling().accessDeniedPage("/403.xhtml").and()
				.addFilterBefore(new LoginFilter(loginDatosPersonales, authenticationManager(), userAccountService),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
