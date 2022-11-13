package oop.clubsv3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter
{
	private final DataSource dataSource;
	
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	public SecurityConfigurer(DataSource dataSource) {this.dataSource = dataSource;}
	
	@Override
	public void configure(HttpSecurity security) throws Exception
	{
		security.httpBasic().and().formLogin(o -> {
			o.failureUrl("/login-failure")
					// .loginPage("/login2")
					.permitAll(true);
			o.defaultSuccessUrl("/index.html");
		});
		security.logout();
		
		// security.
		
		security.authorizeHttpRequests((authz) -> {
			authz.antMatchers("/login2", "/register").permitAll();
			authz.anyRequest().authenticated();
		});
		// return security.build();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception
	{
		// builder.
		builder.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(new BCryptPasswordEncoder())
				.withUser(User.withUsername("user1").password("987654721.33").roles("user"));

		
		// return builder.build();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		// web.ignoring().antMatchers("/login", "/register");
	}
	
	
	
	//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer()
//	{
//		return web -> {
//
//		};
//	}
	
}
