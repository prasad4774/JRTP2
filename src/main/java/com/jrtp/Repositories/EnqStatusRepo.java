package com.jrtp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jrtp.Entities.EnqStatusEntity;
import com.jrtp.Entities.StudentEnqEntity;

@Repository
public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity, Integer> {

	

}
