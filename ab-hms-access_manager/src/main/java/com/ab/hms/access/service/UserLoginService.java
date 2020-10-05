package com.ab.hms.access.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.ab.hms.access.dtos.LoginRequestBodyDto;
import com.ab.hms.access.dtos.LoginResponseDto;
import com.ab.hms.access.enums.RoleEnum;
import com.ab.hms.access.model.UserDetailModel;
import com.ab.hms.access.model.UserDetailsPrincipal;
import com.ab.hms.access.repository.IUserDetailRepository;
import com.ab.hms.access.token.JwtTokenGenerationService;
import com.ab.hms.common.exception.customException.RoleNotExistException;
import com.ab.hms.common.model.AdminModel;
import com.ab.hms.common.model.DoctorModel;
import com.ab.hms.common.model.ReceptionistModel;
import com.ab.hms.common.repository.IAdminRepository;
import com.ab.hms.common.repository.IDocterRepository;
import com.ab.hms.common.repository.IReceptionistRepository;

@Service
public class UserLoginService {

	private AuthenticationManager authenticationManager;
	private IUserDetailRepository iUserDetailRepository;
	private IAdminRepository iAdminRepository;
	private IDocterRepository iDocterRepository;
	private IReceptionistRepository iReceptionistRepository;
	private JwtTokenGenerationService jwtTokenGenerationService;
	
	public UserLoginService(
			AuthenticationManager authenticationManager,
			IUserDetailRepository iUserDetailRepository,
			IAdminRepository iAdminRepository,
			IDocterRepository iDocterRepository,
			IReceptionistRepository iReceptionistRepository,
			JwtTokenGenerationService jwtTokenGenerationService
			) {
		this.authenticationManager = authenticationManager;
		this.iUserDetailRepository = iUserDetailRepository;
		this.jwtTokenGenerationService = jwtTokenGenerationService;
		this.iAdminRepository = iAdminRepository;
		this.iDocterRepository = iDocterRepository;
		this.iReceptionistRepository = iReceptionistRepository;
	}

	public LoginResponseDto UserLogin(LoginRequestBodyDto loginDto) throws RoleNotExistException {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		UserDetailModel user = iUserDetailRepository.findByUsername(loginDto.getUsername());
		UserDetailsPrincipal userPrincipal = new UserDetailsPrincipal(user);
		String token = jwtTokenGenerationService.generateJwtToken(userPrincipal);
		
		LoginResponseDto response = new LoginResponseDto();
		getUserDetails(loginDto.getUsername(), user.getRole(), response);
		
		response.setEmail(user.getEmail());
		response.setIsActive(user.getIsActive());
		response.setPasswordExpiry(user.getPasswordExpiry());
		response.setRole(user.getRole().substring(5));
		response.setUsername(loginDto.getUsername());
		response.setToken(token);
		return response;
	}
	
	private void getUserDetails(String username,String role, LoginResponseDto response) throws RoleNotExistException {
		if(role.equalsIgnoreCase(RoleEnum.ROLE_ADMIN.toString()) || role.equalsIgnoreCase(RoleEnum.ROLE_SUPERADMIN.toString())) {
			addAdminDetail(username, response);
		}
		else if(role.equalsIgnoreCase(RoleEnum.ROLE_DOCTOR.toString())){
			addDoctorDetail(username, response);
		}
		else if(role.equalsIgnoreCase(RoleEnum.ROLE_RECEPTIONIST.toString())){
			addReceptionistDetail(username, response);
		}
		else {
			throw new RoleNotExistException("No role found while generating token");
		}
	}

	private void addAdminDetail(String username, LoginResponseDto response) {
		AdminModel founduser = iAdminRepository.findByUsername(username);
		response.setId(founduser.getAdminid());
		response.setFirstName(founduser.getFirstName());
		response.setLastName(founduser.getLastName());
		response.setContact(founduser.getContactNumber());
		response.setAddress(founduser.getAddress());
		response.setActivityLog(founduser.getActivityLog());
		
		
	}

	private void addDoctorDetail(String username, LoginResponseDto response) {
		DoctorModel founduser = iDocterRepository.findByUsername(username);
		response.setId(founduser.getDoctorid());
		response.setFirstName(founduser.getFirstName());
		response.setLastName(founduser.getLastName());
		response.setContact(founduser.getContactNumber());
		response.setAddress(founduser.getAddress());
		response.setActivityLog(founduser.getActivityLog());
		
	}

	private void addReceptionistDetail(String username, LoginResponseDto response) {
		ReceptionistModel founduser = iReceptionistRepository.findByUsername(username);
		response.setId(founduser.getReceptionistid());
		response.setFirstName(founduser.getFirstName());
		response.setLastName(founduser.getLastName());
		response.setContact(founduser.getContactNumber());
		response.setAddress(founduser.getAddress());
		response.setActivityLog(founduser.getActivityLog());
		
	}
	
}
