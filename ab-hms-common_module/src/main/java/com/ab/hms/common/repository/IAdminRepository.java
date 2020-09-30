package com.ab.hms.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.hms.common.model.AdminModel;

public interface IAdminRepository extends JpaRepository<AdminModel, Integer>{
	AdminModel findByAdminid(String adminid);
	AdminModel findByUsername(String username);
}
