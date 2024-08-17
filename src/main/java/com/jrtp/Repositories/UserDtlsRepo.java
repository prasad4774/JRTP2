package com.jrtp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jrtp.Entities.UserDtlsEntity;

@Repository
public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer>  {

	
	public UserDtlsEntity findByemail(String email);
	
	public UserDtlsEntity findByEmailAndPwd(String email, String pwd);
}
