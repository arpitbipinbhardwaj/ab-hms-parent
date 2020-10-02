package com.ab.hms.access.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ab.hms.access.dtos.LoginRequestBodyDto;
import com.ab.hms.access.dtos.LoginResponseDto;
import com.ab.hms.access.dtos.UserRegisterDto;
import com.ab.hms.access.model.UserDetailModel;
import com.ab.hms.access.service.UserLoginService;
import com.ab.hms.access.service.UserRegisterService;
import com.ab.hms.common.exception.customException.EmailExistException;
import com.ab.hms.common.exception.customException.RoleNotExistException;
import com.ab.hms.common.exception.customException.UsernameExistException;

@RestController
public class AccessController {
	
	private UserRegisterService userRegisterService;
	private UserLoginService userLoginService;
	
	public AccessController(
			UserRegisterService userRegisterService,
			UserLoginService userLoginService
			) {
		
		this.userRegisterService = userRegisterService;
		this.userLoginService = userLoginService;
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<UserDetailModel> registerNewUser(
			@RequestBody(required = true) UserRegisterDto newUser
			) throws EmailExistException, UsernameExistException, RoleNotExistException, ParseException {
		ResponseEntity<UserDetailModel> response = new ResponseEntity<UserDetailModel>(HttpStatus.BAD_REQUEST);
		UserDetailModel registeredUser = userRegisterService.registerNewUser(newUser);
		if(registeredUser!=null)
			response = new ResponseEntity<UserDetailModel>(registeredUser, HttpStatus.OK);
		return response;
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponseDto> loginUser(
			@RequestBody(required = true) LoginRequestBodyDto logindto
		) {
		ResponseEntity<LoginResponseDto> response = new ResponseEntity<LoginResponseDto>(HttpStatus.BAD_REQUEST);
		LoginResponseDto userLoginResponse = userLoginService.UserLogin(logindto);
		if(userLoginResponse !=null) {
			response = new ResponseEntity<LoginResponseDto>(userLoginResponse, HttpStatus.OK);
		}
		return response;
		
	}
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<UserDetailModel>> listAllUser() {
		ResponseEntity<List<UserDetailModel>> response = new ResponseEntity<List<UserDetailModel>>(HttpStatus.BAD_REQUEST);
		List<UserDetailModel> userList = userRegisterService.getAllUsers();
		if(userList!=null && !userList.isEmpty())
			response = new ResponseEntity<List<UserDetailModel>>(userList, HttpStatus.OK);
		return response;
	}
	
}
