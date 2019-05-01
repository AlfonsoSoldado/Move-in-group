package com.moveingroup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.moveingroup.services.UserAccountService;
import com.moveingroup.utils.Constantes;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAccountService userAccountService;

	private String loginUsuario = "/loginUsuario";

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/");
        bean.setRedirectHttp10Compatible(false);
        return bean;
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.csrf().disable();

		http.authorizeRequests()

//				.antMatchers("/usuario/**").hasAuthority(Constantes.ROL_USUARIO)
//				.antMatchers("/empresa/**").hasRole(Constantes.ROL_EMPRESA)
//				.antMatchers("/admin/**").hasRole(Constantes.ROL_ADMIN)
				.anyRequest().permitAll()

				.and().formLogin().loginPage("/index.xhtml").permitAll()
				.failureUrl("/403.xhtml")
				.and().logout().permitAll().logoutUrl("/logout").logoutSuccessHandler(new LogoutHandler())
				
				.and().exceptionHandling().accessDeniedPage("/403.xhtml").and()
				.addFilterBefore(new LoginFilter(loginUsuario, authenticationManager(), userAccountService),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
