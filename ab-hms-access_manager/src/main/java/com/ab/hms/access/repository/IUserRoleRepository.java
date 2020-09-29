package com.ab.hms.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.hms.access.model.UserRoleModel;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRoleModel, Integer>{

}
