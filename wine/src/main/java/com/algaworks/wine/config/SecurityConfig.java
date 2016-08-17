package com.algaworks.wine.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("ildo-zrj").password("trust").roles("ADMIN", "UPLOAD").and()
			.withUser("guess").password("admin").roles("ADMIN");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/layout/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/wines/new").hasRole("ADMIN")
				.antMatchers("/wines/**").hasRole("UPLOAD")
				.anyRequest().authenticated()
					.and().formLogin()
						.loginPage("/login")
						.permitAll()
						.and().logout()
							.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
}
