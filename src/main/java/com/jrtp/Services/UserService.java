package com.jrtp.Services;

import com.jrtp.BindingClasses.LoginForm;
import com.jrtp.BindingClasses.SignUpForm;
import com.jrtp.BindingClasses.UnlockForm;

public interface UserService {

	
	public String login(LoginForm form);
	public boolean signup(SignUpForm form);
	public boolean unlockAccount(UnlockForm form);
	public boolean forgotPwd(String email);
}
