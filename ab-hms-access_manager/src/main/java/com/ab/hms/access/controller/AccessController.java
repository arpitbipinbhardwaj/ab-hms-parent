package com.ab.hms.access.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ab.hms.access.model.UserDetailModel;
import com.ab.hms.access.model.UserRoleModel;
import com.ab.hms.access.repository.IUserDetailRepository;
import com.ab.hms.access.repository.IUserRoleRepository;

@RestController
public class AccessController {

	@Autowired
	private IUserDetailRepository userrepo;
	
	@Autowired
	private IUserRoleRepository rolerepo;
	
	
	@PostMapping(value = "/login")
	public UserDetailModel userLogin(
			@RequestBody UserDetailModel user
			) {
		user.setPassword("dfghjk");
		return userrepo.save(user);
	}
	
	@GetMapping(value = "/list")
	public List<UserDetailModel> listUser() {
		
		String role11 =
				"admin:create,"+
				"admin:update,"+
				"admin:delete,"+
				"admin:read,"+
				"doctor:create,"+
				"doctor:update,"+
				"doctor:delete,"+
				"doctor:read,"+
				"receptionist:create,"+
				"receptionist:update,"+
				"receptionist:delete,"+
				"receptionist:read,"+
				"patient:create,"+
				"patient:update,"+
				"patient:delete,"+
				"patient:read";
		
		String role12 = 
				"doctor:create,"+
				"doctor:update,"+
				"doctor:delete,"+
				"doctor:read,"+
				"receptionist:create,"+
				"receptionist:update,"+
				"receptionist:delete,"+
				"receptionist:read,"+
				"patient:create,"+
				"patient:update,"+
				"patient:delete,"+
				"patient:read";
		
		String role13 = 
				"patient:read";
		
		String role14 = 
				"patient:create,"+
				"patient:update,"+
				"patient:delete,"+
				"patient:read";
		
		List<UserRoleModel> userRList = new ArrayList<UserRoleModel>();
		UserRoleModel role1 = new UserRoleModel(1,"ROLE_SUPERADMIN", role11);
		UserRoleModel role2 = new UserRoleModel(2,"ROLE_ADMIN", role12);
		UserRoleModel role3 = new UserRoleModel(3,"ROLE_DOCTOR", role13);
		UserRoleModel role4 = new UserRoleModel(4,"ROLE_RECEPTIONIST", role14);
		
		userRList.add(role1);
		userRList.add(role2);
		userRList.add(role3);
		userRList.add(role4);
		
		rolerepo.saveAll(userRList);

		return userrepo.findAll();
	}
	
	@GetMapping("/role")
	public List<UserRoleModel> roleList() {
		return rolerepo.findAll();
	}
}
