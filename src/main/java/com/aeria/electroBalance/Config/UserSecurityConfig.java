package com.aeria.electroBalance.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**","/static/**");
	    web.httpFirewall(allowUrlEncodedSlashHttpFirewall());

	}

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		DefaultHttpFirewall firewall = new DefaultHttpFirewall();
		firewall.setAllowUrlEncodedSlash(true);
		return firewall;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").authenticated();
        
        http.antMatcher("/user/**")
            .authorizeRequests().anyRequest().hasAuthority("USER")
            .and()
            .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/user/NvFacture")
                .failureUrl("/user/login?error")
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/");
		
    }
}
