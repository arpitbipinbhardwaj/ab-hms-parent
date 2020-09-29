package com.ab.hms.access.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.hms.access.dtos.UserRegisterDto;
import com.ab.hms.access.model.UserDetailModel;
import com.ab.hms.access.model.UserRoleModel;
import com.ab.hms.access.repository.IUserDetailRepository;
import com.ab.hms.access.repository.IUserRoleRepository;
import com.ab.hms.common.exception.customException.EmailExistException;
import com.ab.hms.common.exception.customException.UsernameExistException;

@Service
public class UserRegisterService {

	private IUserDetailRepository iUserDetailRepository;
	private IUserRoleRepository iUserRoleRepository;
	
	@Autowired
	public UserRegisterService(
			IUserDetailRepository iUserDetailRepository,
			IUserRoleRepository iUserRoleRepository
			) {
		this.iUserDetailRepository = iUserDetailRepository;
		this.iUserRoleRepository = iUserRoleRepository;
	}
	
	public UserDetailModel registerNewUser(UserRegisterDto userRegisterDto) throws EmailExistException, UsernameExistException {
		UserDetailModel newUser = new UserDetailModel();
		
		validateUsernameEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());
		
		newUser.setUsername(userRegisterDto.getUsername());
		newUser.setEmail(userRegisterDto.getEmail());
		newUser.setIsActive(userRegisterDto.getIsActive());
		newUser.setIsNotLocked(userRegisterDto.getIsNotLocked());
		
		setRoleAndAuthority(userRegisterDto, newUser);
		setNewPassword(newUser);
		
		return iUserDetailRepository.save(newUser);				
	}
	
	public List<UserDetailModel> getAllUsers() {
		return iUserDetailRepository.findAll();
	}
	
	private void setRoleAndAuthority(UserRegisterDto userRegisterDto, UserDetailModel newUser) {
		List<UserRoleModel> roleList = iUserRoleRepository.findByRoleName("ROLE_"+userRegisterDto.getRole().toUpperCase());
		if(roleList.size()==1) {
			UserRoleModel role = roleList.get(0);
			newUser.setRole(role.getRoleName());
			newUser.setAuthorities(role.getAuthorities());
		}
		else {
			//TODO: throw role not found exception
		}
	}
	
	private void setNewPassword(UserDetailModel newUser) {
		String pass = RandomStringUtils.randomAlphanumeric(10);
		Instant instant = Instant.now().plus(60, ChronoUnit.DAYS);
		
		newUser.setPassword(pass);
		newUser.setDefaultPassword(true);
		newUser.setPasswordExpiry(Date.from(instant));
	}
	
	private void validateUsernameEmail(String username, String email) throws EmailExistException, UsernameExistException {
		UserDetailModel userByUsername = iUserDetailRepository.findByUsername(username);
		UserDetailModel userByEmail = iUserDetailRepository.findByEmail(email);
		if(userByEmail!=null)
			throw new EmailExistException();
		if(userByUsername!=null)
			throw new UsernameExistException();
	}
	
}
