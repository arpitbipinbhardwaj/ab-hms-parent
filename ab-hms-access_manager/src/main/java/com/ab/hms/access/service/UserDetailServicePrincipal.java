package com.ab.hms.access.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ab.hms.access.model.UserDetailModel;
import com.ab.hms.access.model.UserDetailsPrincipal;
import com.ab.hms.access.repository.IUserDetailRepository;
import com.ab.hms.common.model.ActivityLog;
import com.ab.hms.common.model.AdminModel;
import com.ab.hms.common.model.DoctorModel;
import com.ab.hms.common.model.ReceptionistModel;
import com.ab.hms.common.repository.IAdminRepository;
import com.ab.hms.common.repository.IDocterRepository;
import com.ab.hms.common.repository.IReceptionistRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@Qualifier(value = "UserDetailService")
public class UserDetailServicePrincipal implements UserDetailsService{
	
	private IUserDetailRepository iUserDetailRepository;
	private IAdminRepository iAdminRepository;
	private IDocterRepository iDocterRepository;
	private IReceptionistRepository iReceptionistRepository;
	
	@Autowired
	public UserDetailServicePrincipal(
			IUserDetailRepository iUserDetailRepository,
			IAdminRepository iAdminRepository,
			IDocterRepository iDocterRepository,
			IReceptionistRepository iReceptionistRepository
			) {
		this.iUserDetailRepository = iUserDetailRepository;
		this.iAdminRepository = iAdminRepository;
		this.iDocterRepository = iDocterRepository;
		this.iReceptionistRepository = iReceptionistRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailModel user = iUserDetailRepository.findByUsername(username);
		if(user==null) {
			log.error("No user found for usernmae: "+username);
			throw new UsernameNotFoundException("No such user exists");
		}
		else {
			
			validateUser(user);
//			updatelogin(user);
//			iUserDetailRepository.save(user);
			
			UserDetailsPrincipal userPrincipal = new UserDetailsPrincipal(user);
			log.info("User found with username: "+userPrincipal.getUsername());
			return userPrincipal;
		}
	}

	private void updatelogin(UserDetailModel user){
		ActivityLog activityLog = new ActivityLog();
		activityLog.setLastLoginDate(new Date());
		System.out.println(user.getUsername());
		if(user.getRole().equalsIgnoreCase("ROLE_ADMIN") || user.getRole().equalsIgnoreCase("ROLE_SUPERADMIN"))
		{
			log.info("role found");
			AdminModel currUser = iAdminRepository.findByUsername(user.getUsername());
			if(currUser.getActivityLog().getLastLoginDate()!=null)
				activityLog.setLastLoginDisplayDate(currUser.getActivityLog().getLastLoginDate());
			currUser.setActivityLog(activityLog);
			iAdminRepository.save(currUser);
		}
		else if(user.getRole().equalsIgnoreCase("ROLE_DOCTOR")) {
			DoctorModel currUser = iDocterRepository.findByUsername(user.getUsername());
			activityLog.setLastLoginDisplayDate(currUser.getActivityLog().getLastLoginDate());
			currUser.setActivityLog(activityLog);
			iDocterRepository.save(currUser);
		}
		else if(user.getRole().equalsIgnoreCase("ROLE_RECEPTIONIST")) {
			ReceptionistModel currUser = iReceptionistRepository.findByUsername(user.getUsername());
			
			currUser.setActivityLog(activityLog);
			iReceptionistRepository.save(currUser);
		}
		else {
			log.error("No role exist for this user. Hence account locked");
			user.setIsNotLocked(false);
			user.setIsActive(false);
		}
	}

	private void validateUser(UserDetailModel user){
		
	}

}
