package com.jrtp.Services;

import java.util.List;

import com.jrtp.BindingClasses.DashboardResponse;
import com.jrtp.BindingClasses.EnquiryForm;
import com.jrtp.BindingClasses.EnquirySearchCriteria;
import com.jrtp.Entities.StudentEnqEntity;

public interface EnquiryService {

	
	public List<String> getcourseName();
	
	public List<String> getEnqStatus();
	
	public DashboardResponse getDashboardData(Integer userId);
	
	public boolean saveEnquiry(EnquiryForm form);
	
	public List<EnquiryForm> getEnquiries(Integer userId, EnquirySearchCriteria criteria);
	
	public EnquiryForm getEnquiry(Integer enId);
	
	public List<StudentEnqEntity> getAll(Integer userId);
}
