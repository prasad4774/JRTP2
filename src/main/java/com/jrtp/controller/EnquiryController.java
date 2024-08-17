package com.jrtp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrtp.BindingClasses.DashboardResponse;
import com.jrtp.BindingClasses.EnquiryForm;
import com.jrtp.Entities.StudentEnqEntity;
import com.jrtp.Services.EnquiryService;

@Controller
public class EnquiryController {

	@Autowired
	private HttpSession httpSession;
	
	
	@Autowired
	private EnquiryService enquiryService;
	
	@GetMapping("/logout")
	public String logout()
	{
		
		httpSession.invalidate();
		return"index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model  )
	{
		
		Integer userId = (Integer) httpSession.getAttribute("userId");
		DashboardResponse dashboardData = enquiryService.getDashboardData(userId);
		
		model.addAttribute("dashboardData", dashboardData);
		
		
		return "dashboard";
		
	}
	@GetMapping("/enquiry")
	public String addenquiryPage(Model model)
	{
		
		List<String> course = enquiryService.getcourseName();
		List<String> enqStatus = enquiryService.getEnqStatus();
		
		EnquiryForm formObj = new EnquiryForm();
		
		model.addAttribute("coures", course);
		model.addAttribute("enqStatus", enqStatus);
		model.addAttribute("formObj", formObj);
		                     
		
		
		return "add-enquiry";
	}
	
	
	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model  model)
	{
		
		
		boolean enquiry = enquiryService.saveEnquiry(formObj);
		
		if (enquiry) {
			model.addAttribute("successMSG", "Student Equiry Added");
			
		}else {
		
			model.addAttribute("errorMSG", "Student Equiry Not Added");
		}
		
		return"add-enquiry";
	}
	
	
	@GetMapping("/enquires")
	public String viewEnquiryPage(Model model)
	{
	
		Integer userId = (Integer) httpSession.getAttribute("userId");
		
		List<StudentEnqEntity> list = enquiryService.getAll(userId);
		
		model.addAttribute("enquiry", list);
		
		return"view-enquiries";
	}
	
	
}
