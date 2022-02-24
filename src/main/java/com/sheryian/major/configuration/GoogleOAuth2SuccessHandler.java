package com.sheryian.major.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sheryian.major.model.Role;
import com.sheryian.major.model.User;
import com.sheryian.major.repository.RoleRepository;
import com.sheryian.major.repository.UserRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	
	
	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	// this is used for redirect internally
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		OAuth2AuthenticationToken token=(OAuth2AuthenticationToken) authentication;  // token jo aya hai use type cast kiya 
		String email=token.getPrincipal().getAttributes().get("email").toString();
		
		if(userRepository.findUserByEmail(email).isPresent()) {
			
		}else {
			User user=new User();
			user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);       // password khail rahega
			List<Role> roles =new ArrayList<>();
			roles.add(roleRepository.findById(2).get());                // in role tabel 2 entries 1.User and 2.Admin
			user.setRoles(roles);
			userRepository.save(user);
		} 	
		redirectStrategy.sendRedirect(request, response, "/");
	}
	
	
	
	
}
