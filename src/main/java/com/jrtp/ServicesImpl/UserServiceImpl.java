package com.jrtp.ServicesImpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.BindingClasses.LoginForm;
import com.jrtp.BindingClasses.SignUpForm;
import com.jrtp.BindingClasses.UnlockForm;
import com.jrtp.Entities.UserDtlsEntity;
import com.jrtp.Repositories.UserDtlsRepo;
import com.jrtp.Services.UserService;
import com.jrtp.Utility.EmailUtils;
import com.jrtp.Utility.PwdUtils;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserDtlsRepo dtlsRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public String login(LoginForm form) {
		
		UserDtlsEntity entity = dtlsRepo.findByEmailAndPwd(form.getEmail(), form.getPwd());
		
		if(entity==null)
		{
			return "Email Address or Password Not Match";
		}
		if(entity.getAccstatus().equals("LOCKED"))
		{
			return"Your Account Is Locked";
		}
		
		httpSession.setAttribute("userId", entity.getUserId());
		
		
		return "success";
	}

	@Override
	public boolean signup(SignUpForm form) {
		
		System.out.println(form.getName());
		System.out.println(form.getPhno());
		
		UserDtlsEntity byemail = dtlsRepo.findByemail(form.getEmail());
		
		if(byemail!=null)
		{
			return false;
		}
		
		
		UserDtlsEntity entity= new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);
		
		
		
		
		String tempwd = PwdUtils.generateRandomPs();
		entity.setPwd(tempwd);
		entity.setAccstatus("LOCKED");
		
		dtlsRepo.save(entity);
		
		String email = form.getEmail();
		
		String subject= "UNLOCK YOUR A/C";
		StringBuffer sb= new StringBuffer();
		
		sb.append(" <h1>Use Temporery Password Access your Account</h1>");
		sb.append("Temprory Password :"+ tempwd);
		sb.append("<br>");
		sb.append("<a href=\" http://localhost:9096/unlock?email=" +email+ "\">Click Here to Unlock Account</a>");
		
		emailUtils.sendEmail(email, subject, sb.toString());
		
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
		
		UserDtlsEntity entity = dtlsRepo.findByemail(form.getEmail());
		
		if(entity.getPwd().equals(form.getTempPwd()))
		{
			entity.setPwd(form.getNewPwd());
			entity.setAccstatus("UNLOCKED");
			
			dtlsRepo.save(entity);
			return true;
			
		}
		
		
		return false;
	}

	@Override
	public boolean forgotPwd(String email) {
		
		UserDtlsEntity entity = dtlsRepo.findByemail(email);
		if(entity==null)
		{
			return false;
			
		}
		
		String subject="Forgot Password ";
		String body= "your Passeord is :- "+entity.getPwd();
		
		emailUtils.sendEmail(email, subject, body);
		
		return true;
	}

}
