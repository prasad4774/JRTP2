package com.jrtp.Utility;

import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtils {
	
	
	public static String generateRandomPs()
	{
		
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 6, characters );
		
		return pwd;
	}

}
