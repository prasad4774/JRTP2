package com.jrtp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrtp.BindingClasses.LoginForm;
import com.jrtp.BindingClasses.SignUpForm;
import com.jrtp.BindingClasses.UnlockForm;
import com.jrtp.Services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService UserService;
	

	
	
	@PostMapping("/signup")
	public String handlesign(@ModelAttribute("user")SignUpForm form, Model model)
	{
		
		  boolean status = UserService.signup(form);
		  
		  if(status)
		  {
			  model.addAttribute("successMsg", "check Your Email");
			  
		  }
		  else {
			  model.addAttribute("errMsg", "Fill Valid Email Address");
		  }
		  
		  return "signup";
	}
	
	
	
	@GetMapping("/login")
	public String loginpage(Model model) {
		
		model.addAttribute("login", new LoginForm());
		

		return "login";
	}
	
	
	@PostMapping("/login")
	public String loginAccount(@ModelAttribute ("login")  LoginForm form, Model model)
	{
		
		String status = UserService.login(form);
		
		if(status.contains("success"))
		{
			
			
			return "redirect:/dashboard";
		}
		 model.addAttribute("errorMSG", status);
		
		return "login";
		
		
		
	}

	@GetMapping("/signup")
	public String signuppage( Model model) {

		model.addAttribute("user", new SignUpForm());
		return "signup";
	}
	
	
	
	

	@GetMapping("/unlock")
	public String unlockpage(@RequestParam String email, Model model) {

		UnlockForm unlockform = new UnlockForm();
		unlockform.setEmail(email);
		
		model.addAttribute("unlock", unlockform);
		
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute ("unlock") UnlockForm form, Model model)
	{
		
		System.err.println(form);
		
		if(form.getNewPwd().equals(form.getConfirmPwd()))
		{
			System.out.println(form.getNewPwd());
			System.out.println(form.getConfirmPwd());
			boolean status = UserService.unlockAccount(form);
			
			if(status)
			{
			   model.addAttribute("successMSG", "Your Account Unlocked");	
			}
			else {
				
				model.addAttribute("ErrorMSG", "Fill Valid credentials");
			}
		}
		else {
			model.addAttribute("ErrorMSGG", "Temporary PWD and is Incorrect ");
		}
		
		return "unlock";
		
	}
	
	
	

	@GetMapping("/forgot")
	public String forgotpage() {

		return "forgotPwd";
	}
	
	
	
	@PostMapping("/forgotPwd")
	public String forgotPwd(@RequestParam("email") String email, Model model)
	{
		System.out.println(email);
		
		boolean status = UserService.forgotPwd(email);
		
		if(status)
		{
			System.out.println(status);
			model.addAttribute("successMSG", "Recover Your Password Successfully");
		}
		else {
		model.addAttribute("ErrorMSG", "Wrong Credentials");
		}
		
		return"forgotPwd";
	}
	
	
	
	
	
	
	
}
