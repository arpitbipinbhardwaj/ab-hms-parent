package com.ab.hms.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.hms.common.model.DoctorModel;

public interface IDocterRepository extends JpaRepository<DoctorModel, Integer>{
	DoctorModel findByDoctorid(String doctorid);
	DoctorModel findByUsername(String username);
}
