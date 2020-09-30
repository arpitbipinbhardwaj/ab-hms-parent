package com.ab.hms.access.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.hms.access.dtos.AddressDto;
import com.ab.hms.access.dtos.UserRegisterDto;
import com.ab.hms.access.model.UserDetailModel;
import com.ab.hms.access.model.UserRoleModel;
import com.ab.hms.access.repository.IUserDetailRepository;
import com.ab.hms.access.repository.IUserRoleRepository;
import com.ab.hms.common.exception.customException.EmailExistException;
import com.ab.hms.common.exception.customException.RoleNotExistException;
import com.ab.hms.common.exception.customException.UsernameExistException;
import com.ab.hms.common.model.Address;
import com.ab.hms.common.model.AdminModel;
import com.ab.hms.common.model.DoctorModel;
import com.ab.hms.common.model.ReceptionistModel;
import com.ab.hms.common.repository.IAdminRepository;
import com.ab.hms.common.repository.IDocterRepository;
import com.ab.hms.common.repository.IReceptionistRepository;

@Service
public class UserRegisterService {

	private IUserDetailRepository iUserDetailRepository;
	private IUserRoleRepository iUserRoleRepository;
	private IAdminRepository iAdminRepository;
	private IDocterRepository iDocterRepository;
	private IReceptionistRepository iReceptionistRepository;
	
	private RequestBodyValidationService requestBodyValidationService;
	
	@Autowired
	public UserRegisterService(
			IUserDetailRepository iUserDetailRepository,
			IUserRoleRepository iUserRoleRepository,
			RequestBodyValidationService requestBodyValidationService,
			IAdminRepository iAdminRepository,
			IDocterRepository iDocterRepository,
			IReceptionistRepository iReceptionistRepository
			) {
		this.iUserDetailRepository = iUserDetailRepository;
		this.iUserRoleRepository = iUserRoleRepository;
		this.requestBodyValidationService = requestBodyValidationService;
		this.iAdminRepository = iAdminRepository;
		this.iDocterRepository = iDocterRepository;
		this.iReceptionistRepository = iReceptionistRepository;
		
	}
	
	public UserDetailModel registerNewUser(UserRegisterDto userRegisterDto)
			throws EmailExistException, UsernameExistException, RoleNotExistException, ParseException {
		UserDetailModel newUser = new UserDetailModel();
		
		requestBodyValidationService.verifyUserRegisrerRequestBody(userRegisterDto);
		validateUsernameEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());
		
		newUser.setUsername(userRegisterDto.getUsername());
		newUser.setEmail(userRegisterDto.getEmail());
		newUser.setIsActive(userRegisterDto.getIsActive());
		newUser.setIsNotLocked(userRegisterDto.getIsNotLocked());
		
		setRoleAndAuthority(userRegisterDto, newUser);
		setNewPassword(newUser);
		
		saveNewUserDetails(userRegisterDto);
		
		return iUserDetailRepository.save(newUser);				
	}
	
	public List<UserDetailModel> getAllUsers() {
		return iUserDetailRepository.findAll();
	}
	
	private void setRoleAndAuthority(UserRegisterDto userRegisterDto, UserDetailModel newUser) throws RoleNotExistException {
		List<UserRoleModel> roleList = iUserRoleRepository.findByRoleName("ROLE_"+userRegisterDto.getRole().toUpperCase());
		if(roleList.size()==1) {
			UserRoleModel role = roleList.get(0);
			newUser.setRole(role.getRoleName());
			newUser.setAuthorities(role.getAuthorities());
		}
		else {
			throw new RoleNotExistException();
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
	
	private void saveNewUserDetails(UserRegisterDto userRegisterDto) throws ParseException {
		if(userRegisterDto.getRole().equalsIgnoreCase("ADMIN") || userRegisterDto.getRole().equalsIgnoreCase("SUPERADMIN"))
			saveAdminData(userRegisterDto);
		else if(userRegisterDto.getRole().equalsIgnoreCase("DOCTOR"))
			saveDoctorData(userRegisterDto);
		else if(userRegisterDto.getRole().equalsIgnoreCase("RECEPTIONIST"))
			saveReceptionistData(userRegisterDto);
		else
			//TODO: throw no such role exception
			System.out.println("exception");
	}
	
	private void saveAdminData(UserRegisterDto userRegisterDto) throws ParseException {
		AdminModel newAdmin = new AdminModel();
		newAdmin.setUsername(userRegisterDto.getUsername());
		newAdmin.setFirstName(userRegisterDto.getFirstName());
		newAdmin.setLastName(userRegisterDto.getLastName());
		newAdmin.setAddress(setAddress(userRegisterDto.getAddress()));
		newAdmin.setContactNumber(userRegisterDto.getContactNumber());;
		newAdmin.setEmergencyContact(userRegisterDto.getEmergencyContact());
		newAdmin.setDob(saveDob(userRegisterDto.getDob()));
		newAdmin.setAdminid(generateUserId(userRegisterDto.getRole()));
		iAdminRepository.save(newAdmin);
	}
	
	private void saveDoctorData(UserRegisterDto userRegisterDto) throws ParseException {
		DoctorModel newDoctor = new DoctorModel();
		newDoctor.setUsername(userRegisterDto.getUsername());
		newDoctor.setFirstName(userRegisterDto.getFirstName());
		newDoctor.setLastName(userRegisterDto.getLastName());
		newDoctor.setAddress(setAddress(userRegisterDto.getAddress()));
		newDoctor.setContactNumber(userRegisterDto.getContactNumber());;
		newDoctor.setEmergencyContact(userRegisterDto.getEmergencyContact());
		newDoctor.setDob(saveDob(userRegisterDto.getDob()));
		newDoctor.setDoctorid(userRegisterDto.getRole());
		iDocterRepository.save(newDoctor);
	}
	
	private void saveReceptionistData(UserRegisterDto userRegisterDto) throws ParseException {
		ReceptionistModel newReceptionist = new ReceptionistModel();
		newReceptionist.setUsername(userRegisterDto.getUsername());
		newReceptionist.setFirstName(userRegisterDto.getFirstName());
		newReceptionist.setLastName(userRegisterDto.getLastName());
		newReceptionist.setAddress(setAddress(userRegisterDto.getAddress()));
		newReceptionist.setContactNumber(userRegisterDto.getContactNumber());;
		newReceptionist.setEmergencyContact(userRegisterDto.getEmergencyContact());
		newReceptionist.setDob(saveDob(userRegisterDto.getDob()));
		newReceptionist.setReceptionistid(userRegisterDto.getRole());
		iReceptionistRepository.save(newReceptionist);
	}
	
	private Address setAddress(AddressDto addressDto) {
		Address address = new Address();
		address.setAddressLine1(addressDto.getAddressLine1());
		address.setAddressLine2(addressDto.getAddressLine2());
		address.setAddressLine3(addressDto.getAddressLine3());
		address.setCity(addressDto.getCity());
		address.setPinCode(addressDto.getPinCode());
		address.setState(addressDto.getState());
		return address;
	}
	
	private Date saveDob(String dob) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateOfBirth = new Date();
		dateOfBirth = formatter.parse(dob);
		return dateOfBirth;
	}
	
	private String generateUserId(String role) {
		String generatedID = null;
		if(role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("SUPERADMIN")) {
			generatedID = "ADM"+RandomStringUtils.randomNumeric(6);
			while(iAdminRepository.findByAdminid(generatedID)!=null)
				generatedID = "ADM"+RandomStringUtils.randomNumeric(6);
		}
		else if(role.equalsIgnoreCase("DOCTOR")) {
			generatedID = "DOC"+RandomStringUtils.randomNumeric(6);
			while(iDocterRepository.findByDoctorid(generatedID)!=null)
				generatedID = "DOC"+RandomStringUtils.randomNumeric(6);
		}
		else if(role.equalsIgnoreCase("RECEPTIONIST")) {
			generatedID = "RCP"+RandomStringUtils.randomNumeric(6);
			while(iReceptionistRepository.findByReceptionistid(generatedID)!=null)
				generatedID = "RCP"+RandomStringUtils.randomNumeric(6);
		}
		else
			//TODO: throw no such role exception
			System.out.println("exception");
		return generatedID;
	}
	
}
