package com.ab.hms.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.hms.access.model.UserAuthorityModel;

@Repository
public interface IUserAuthorityRepository extends JpaRepository<UserAuthorityModel, Integer>{

}
