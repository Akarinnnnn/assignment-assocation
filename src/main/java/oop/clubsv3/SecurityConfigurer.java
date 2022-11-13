package oop.clubsv3;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfigurer
{
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception
	{
		security.formLogin(o -> o.failureUrl("/login-failed")
										.loginPage("/login2")
										.permitAll(true));
		
		security.authorizeHttpRequests((authz) ->
									   {
										   authz.antMatchers(
												   "/login2",
												   "/register",
												   "/login-failed"
										   ).permitAll();
										   authz.anyRequest().authenticated();
									   });
		return security.build();
	}
	
	@Bean
	public AuthenticationManager configureAuthManager(AuthenticationManagerBuilder builder
	) throws Exception
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		builder.authenticationProvider(provider);
		return builder.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer()
	{
		return web -> web.ignoring().antMatchers(
				"/login",
				"/register",
				"/login-failed"
		);
	}
	
	@Bean
	public UserDetailsService userDetailsManager(
			@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
			DataSource src,
			PasswordEncoder encoder)
	{
		UserDetailsManager manager = new JdbcUserDetailsManager(src);
		if (!manager.userExists("aDmin"))
		{
			
			manager.createUser(User.withUsername("user")
									   .password(encoder.encode("987654721.33"))
									   .roles("user")
									   .build()
			);
		}
		return manager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		// Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(); classnotfound
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
