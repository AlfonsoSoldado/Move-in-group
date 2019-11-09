package com.moveingroup.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.moveingroup.utils.Constantes;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {

		// Autorizado de urls

		http.csrf().disable();
		
		http.headers().frameOptions().disable();

		http.authorizeRequests()
		
			.antMatchers("/usuario/**").hasAuthority(Constantes.ROL_USUARIO)
			.antMatchers("/empresa/**").hasAuthority(Constantes.ROL_EMPRESA)
			.antMatchers("/admin/**").hasAuthority(Constantes.ROL_ADMIN)
			.anyRequest().permitAll()
			.and().exceptionHandling()
			.accessDeniedPage("/403.xhtml")
			.and().logout().permitAll().logoutUrl("/logout").logoutSuccessHandler(new LogoutHandler())
			.and().addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
			
	    }
}
