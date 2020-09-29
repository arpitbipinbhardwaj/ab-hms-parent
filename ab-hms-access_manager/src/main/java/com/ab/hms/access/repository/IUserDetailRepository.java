package com.ab.hms.access.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.hms.access.model.UserDetailModel;

@Repository
public interface IUserDetailRepository extends JpaRepository<UserDetailModel, BigInteger>{

}
