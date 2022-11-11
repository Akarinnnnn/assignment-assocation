package oop.clubsv3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebSecurity
public class SecurityConfigurer
{
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception
	{
		security.formLogin(o -> {
			o.failureUrl("/login-failure")
					.loginPage("/login2")
					.permitAll(true);
			
		});
		
		security.authorizeHttpRequests((authz) -> {
			authz.antMatchers("/login2", "/register").permitAll();
			authz.anyRequest().authenticated();
		});
		return security.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer()
	{
		return web -> {
			web.ignoring().antMatchers("/login", "/register");
		};
	}
}
