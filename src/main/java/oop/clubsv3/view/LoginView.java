package oop.clubsv3.view;

import oop.clubsv3.models.LoginInfo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
public class LoginView
{
	private final AuthenticationManager authman;
	private final UserDetailsManager userman;
	
	
	public LoginView(AuthenticationManager authman,
					 UserDetailsService userDetailsService)
	{
		this.authman = authman;
		
		assert userDetailsService instanceof UserDetailsManager;
		this.userman = (UserDetailsManager) userDetailsService;
	}
	
	@RequestMapping("/login2")
	public String login2()
	{
		return "/login2";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(@ModelAttribute LoginInfo login)
	{
		Authentication authresult;
		try
		{
			var authtoken = new UsernamePasswordAuthenticationToken(
					login.getUserName(), login.getPassword()
			);
			
			authresult = authman.authenticate(authtoken);
		}
		catch (UsernameNotFoundException e)
		{
			return "register";
		}
		catch (AuthenticationException e)
		{
			return "login2";
		}
		
		SecurityContextHolder.getContext().setAuthentication(authresult);
		// detail.
		return "index";
	}
	
	@RequestMapping("/register")
	public String register(@ModelAttribute LoginInfo info)
	{
		if (userman.userExists(info.getUserName()))
		{
			return "login2";
		}
		userman.createUser(User.withUsername(info.getUserName())
								   .password(info.getPassword())
								   .roles("user")
								   .build());
		
		return "index";
	}
}
