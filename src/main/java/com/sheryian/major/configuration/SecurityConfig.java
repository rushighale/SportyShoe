package com.sheryian.major.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sheryian.major.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	@Autowired
	CustomUserDetailService customUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		
				.authorizeRequests()
				.antMatchers("/","/shop/**","/register").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")              // check kro admin hai kya
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/")
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
				.oauth2Login()
				.loginPage("/login")
				.successHandler(googleOAuth2SuccessHandler)
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)                             // invalid krenge session, cookies ko krenge remove
				.deleteCookies("JSESSIONID")
				.and()
				.exceptionHandling()
				.and()
				.csrf()
				.disable();
				
		http.headers().frameOptions().disable();                // for h2 database , when in future use other DB then delete it
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(customUserDetailService);   // from above info retrive the user and ek custom user ka obj build kro and use passkr do
	}

	@Override
	public void configure(WebSecurity web) throws Exception { // web security for static content
		
		web.ignoring().antMatchers("/resources/**","/static/**","/images/**","/productImages/**","/css/**","/js/**");
	}
	
	
	
	
	

	
	
}
