package com.ab.hms.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.hms.common.model.ReceptionistModel;

public interface IReceptionistRepository extends JpaRepository<ReceptionistModel, Integer>{
	ReceptionistModel findByReceptionistid(String receptionistid);
	
	
}
