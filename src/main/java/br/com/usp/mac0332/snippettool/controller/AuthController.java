package br.com.usp.mac0332.snippettool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.mac0332.snippettool.dto.auth.AuthLoginDto;
import br.com.usp.mac0332.snippettool.dto.auth.AuthRegisterDto;
import br.com.usp.mac0332.snippettool.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

	private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
			.getContextHolderStrategy();

	@PostMapping("/login")
	public void login(@RequestBody AuthLoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
		UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
				.unauthenticated(loginDto.username(), loginDto.password());
		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContext context = securityContextHolderStrategy.createEmptyContext();
		context.setAuthentication(authentication);
		securityContextHolderStrategy.setContext(context);
		securityContextRepository.saveContext(context, request, response);
	}

	@PostMapping("/register")
	public void register(@RequestBody AuthRegisterDto registerDto) {
		userService.create(registerDto);
	}

}
