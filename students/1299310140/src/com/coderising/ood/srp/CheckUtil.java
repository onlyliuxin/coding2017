package com.coderising.ood.srp;

public class CheckUtil {
	protected static boolean checkEmail(String emailAddress){
		if(emailAddress.length() > 0){
			return true;
		}else{
			return false;
		}
	}
}
