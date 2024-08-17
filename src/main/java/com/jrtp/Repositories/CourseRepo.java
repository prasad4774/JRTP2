package com.jrtp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jrtp.Entities.CourseEntity;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Integer> {
	

}
