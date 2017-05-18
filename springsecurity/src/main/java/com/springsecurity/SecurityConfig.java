package com.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)throws Exception{
		auth.ldapAuthentication().userDnPatterns("uid={0},"+LdapSearch.BASE_DN)
		.contextSource().url(LdapSearch.LDAP_URL)
		.managerDn(LdapSearch.SEARCH_USER_DN).managerPassword(LdapSearch.SEARCH_USER_PW);
	}
		@Override
	protected void configure(HttpSecurity http)throws Exception{
			http.authorizeRequests().antMatchers("/encrypt","/resources/**").permitAll().anyRequest().fullyAuthenticated().and().formLogin();
			
	}
}
