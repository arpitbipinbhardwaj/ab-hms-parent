package com.ab.hms.access.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.ab.hms.access.dtos.LoginRequestBodyDto;
import com.ab.hms.access.dtos.LoginResponseDto;
import com.ab.hms.access.model.UserDetailModel;
import com.ab.hms.access.model.UserDetailsPrincipal;
import com.ab.hms.access.repository.IUserDetailRepository;
import com.ab.hms.access.token.JwtTokenGenerationService;

@Service
public class UserLoginService {

	private AuthenticationManager authenticationManager;
	private IUserDetailRepository iUserDetailRepository;
	private JwtTokenGenerationService jwtTokenGenerationService;
	
	public UserLoginService(
			AuthenticationManager authenticationManager,
			IUserDetailRepository iUserDetailRepository,
			JwtTokenGenerationService jwtTokenGenerationService
			) {
		this.authenticationManager = authenticationManager;
		this.iUserDetailRepository = iUserDetailRepository;
		this.jwtTokenGenerationService = jwtTokenGenerationService;
	}

	public LoginResponseDto UserLogin(LoginRequestBodyDto loginDto) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		UserDetailModel user = iUserDetailRepository.findByUsername(loginDto.getUsername());
		UserDetailsPrincipal userPrincipal = new UserDetailsPrincipal(user);
		String token = jwtTokenGenerationService.generateJwtToken(userPrincipal);
		
		LoginResponseDto response = new LoginResponseDto();
		response.setUsername(loginDto.getUsername());
		response.setToken(token);
		return response;
	}
}
