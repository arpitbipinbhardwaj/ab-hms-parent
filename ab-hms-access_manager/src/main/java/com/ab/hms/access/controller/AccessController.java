package com.ab.hms.access.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ab.hms.access.dtos.UserRegisterDto;
import com.ab.hms.access.model.UserDetailModel;
import com.ab.hms.access.service.UserRegisterService;
import com.ab.hms.common.exception.customException.EmailExistException;
import com.ab.hms.common.exception.customException.UsernameExistException;

@RestController
public class AccessController {
	
	private UserRegisterService userRegisterService;
	
	public AccessController(
			UserRegisterService userRegisterService
			) {
		
		this.userRegisterService = userRegisterService;
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<UserDetailModel> registerNewUser(
			@RequestBody UserRegisterDto newUser
			) throws EmailExistException, UsernameExistException {
		ResponseEntity<UserDetailModel> response = new ResponseEntity<UserDetailModel>(HttpStatus.BAD_REQUEST);
		UserDetailModel registeredUser = userRegisterService.registerNewUser(newUser);
		if(registeredUser!=null)
			response = new ResponseEntity<UserDetailModel>(registeredUser, HttpStatus.OK);
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
