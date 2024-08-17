package com.jrtp.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
@Component
public class EmailUtils {
	
	@Autowired
	 private JavaMailSender javaMailSender;
	
	 public boolean sendEmail(String to , String  subject, String body)
	 {
		 
		 boolean isSent =false;
		 
		 
		 try {
			
			 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			 MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			 
			 helper.setTo(to);
			 helper.setSubject(subject);
			 helper.setText(body,true);
			 
			 javaMailSender.send(mimeMessage);
			 
			 
			 
			 isSent=true;
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		 
		 return isSent;
	 }
	

}
