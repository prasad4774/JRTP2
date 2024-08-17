package com.jrtp.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jrtp.Entities.CourseEntity;
import com.jrtp.Entities.EnqStatusEntity;
import com.jrtp.Repositories.CourseRepo;
import com.jrtp.Repositories.EnqStatusRepo;

@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private CourseRepo courceRepo;
	
	@Autowired
	private EnqStatusRepo enquiryStatusRepo;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

//		List<String> courseList = Arrays.asList("Java","Python","DevOps","AWS");
		
//		List<String> statusList = Arrays.asList("New","Enrolled","Lost");
		
		courceRepo.deleteAll();
		
		CourseEntity c1 = new CourseEntity();
//		c1.setCourseId(1);
		c1.setCourseName("Java");
//		courceRepo.save(c1);

		CourseEntity c2 = new CourseEntity();
//		c1.setCourseId(2);
		c2.setCourseName("Python");
//		courceRepo.save(c2);

		CourseEntity c3 = new CourseEntity();
//		c1.setCourseId(3);
		c3.setCourseName("DevOps");
//		courceRepo.save(c3);

		CourseEntity c4 = new CourseEntity();
//		c1.setCourseId(4);
		c4.setCourseName("AWS");
//		courceRepo.save(c4);
		
		List<CourseEntity> list2 = Arrays.asList(c1,c2,c3,c4);
		courceRepo.saveAll(list2);
		
		//  //  //////// ///
		
		enquiryStatusRepo.deleteAll();
		
		EnqStatusEntity e1 = new EnqStatusEntity();
//		e1.setStatusId(1);
		e1.setStatusName("New");
//		enquiryStatusRepo.save(e1);

		EnqStatusEntity e2 = new EnqStatusEntity();
//		e1.setStatusId(2);
		e2.setStatusName("Enrolled");
//		enquiryStatusRepo.save(e2);

		EnqStatusEntity e3 = new EnqStatusEntity();
//		e1.setStatusId(3);
		e3.setStatusName("Lost");
//		enquiryStatusRepo.save(e3);
		
		List<EnqStatusEntity	> list = Arrays.asList(e1,e2,e3);
		
		enquiryStatusRepo.saveAll(list);
		
		
	}
}
