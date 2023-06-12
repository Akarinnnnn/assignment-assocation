package oop.clubsv3;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter
{
	private static final String[] AnonUrls = {
			"/login", "/register", "/index", "/login-failure",
			"/css/**", "/js/**", "/img/**"
	};
	private static final String[] AuthorizedUrls = {
			"/member/**", "/club/**", "/activity/**"
	};
	
	private final DataSource dataSource;
	public SecurityConfigurer(DataSource dataSource) {this.dataSource = dataSource;}
	
	@Override
	public void configure(HttpSecurity security) throws Exception
	{
		security.httpBasic().and().formLogin(o -> {
			o.failureUrl("/login-failure")
					// .loginPage("/login2")
					.permitAll(true);
			o.defaultSuccessUrl("/");
		});
		security.logout().logoutSuccessUrl("/");
		
		// security.
		
		security.authorizeHttpRequests((authz) -> {
			authz.antMatchers(AnonUrls).permitAll();
			authz.antMatchers(AuthorizedUrls).authenticated();
		});
		// return security.build();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		var encoder = new BCryptPasswordEncoder();
		var jdbcbuilder = builder.jdbcAuthentication()
								  .dataSource(dataSource)
								  .passwordEncoder(encoder);
		// builder.
		UserDetailsService details = jdbcbuilder.getUserDetailsService();
		try {
			details.loadUserByUsername("user1");
		} catch (UsernameNotFoundException e) {
			// TODO 默认用户密码
			jdbcbuilder.withUser(User.withUsername("user1").password(encoder.encode("987654721.33")).roles("user"));
		}
		
		
		// return builder.build();
	}
	
	//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer()
//	{
//		return web -> {
//
//		};
//	}
	
}
