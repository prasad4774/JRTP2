package com.jrtp.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.BindingClasses.DashboardResponse;
import com.jrtp.BindingClasses.EnquiryForm;
import com.jrtp.BindingClasses.EnquirySearchCriteria;
import com.jrtp.Entities.CourseEntity;
import com.jrtp.Entities.EnqStatusEntity;
import com.jrtp.Entities.StudentEnqEntity;
import com.jrtp.Entities.UserDtlsEntity;
import com.jrtp.Repositories.CourseRepo;
import com.jrtp.Repositories.EnqStatusRepo;
import com.jrtp.Repositories.StudentEnqRepo;
import com.jrtp.Repositories.UserDtlsRepo;
import com.jrtp.Services.EnquiryService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private UserDtlsRepo  userDtlsRepo;
	
	@Autowired
	private HttpSession  httpSession;
	

	@Autowired
	private StudentEnqRepo enqRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo enqStatusRepo;
	
	@Override
	public List<String> getcourseName() {
		
		List<CourseEntity> courses = courseRepo.findAll();
		
		List<String> course= new ArrayList<>();
		
		
		for( CourseEntity name : courses)
		{
			course.add(name.getCourseName());
		}
		
		return course;
	}

	@Override
	public List<String> getEnqStatus() {
		
		
		List<EnqStatusEntity> statuses = enqStatusRepo.findAll();
		
		List<String> statuslist = new ArrayList<>();
		for(EnqStatusEntity status : statuses)
		{
			statuslist.add(status.getStatusName());  
		}
		
		
		return statuslist;
	}

	@Override
	public DashboardResponse getDashboardData(Integer userId) {

		Optional<UserDtlsEntity> user = userDtlsRepo.findById(userId);
		
		DashboardResponse respons= new DashboardResponse();
		
		if(user.isPresent())
		{
			
			    UserDtlsEntity userDtlsEntity = user.get();
			    
			    List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			    
			    int totalCnt = enquiries.size();  
			    
			    int enrolledCnt = enquiries.stream()
			             .filter(i-> i.getEnqStatus().equals("Enrolled"))
			             .collect(Collectors.toList()).size();
			    int lostCnt = enquiries.stream()
			             .filter(i-> i.getEnqStatus().equals("Lost"))
			             .collect(Collectors.toList()).size();
			    
			    respons.setTotalenquiriesCnt(totalCnt);
			    respons.setEnrolledCnt(enrolledCnt);
			    respons.setLostCnt(lostCnt);
			    
			    
			    
			
		}
		
		
		
		
		return respons;
	}

	@Override
	public boolean saveEnquiry(EnquiryForm form) {
		
		
		StudentEnqEntity studentEnqEntity = new StudentEnqEntity();
		BeanUtils.copyProperties(form, studentEnqEntity);
		
		Integer userId = (Integer) httpSession.getAttribute("userId");
		
		 UserDtlsEntity userDtlsEntity = userDtlsRepo.findById(userId).get();
		 
		studentEnqEntity.setUser(userDtlsEntity);
		
		
		enqRepo.save(studentEnqEntity);
		
		return true;
	}

	@Override
	public List<EnquiryForm> getEnquiries(Integer userId, EnquirySearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnquiryForm getEnquiry(Integer enId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentEnqEntity> getAll(Integer userId) {
		
		 List<StudentEnqEntity> list = enqRepo.findAll();
		
		
		return list;
		
		
		
	}

}
