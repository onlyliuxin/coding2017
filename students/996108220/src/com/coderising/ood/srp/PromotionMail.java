 package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PromotionMail {

	//读取用户信息
	
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
		

	public static void main(String[] args) throws Exception {

		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail();
		List userInfo = DBUtil.loadMailingList();
		List mailingList = pe.filterUserInfo(userInfo);
		MailUtil.sendEMails(emailDebug, mailingList);	
	}

	


	protected List filterUserInfo(List userList) throws IOException 
	{
	
		if (userList != null) {
			Iterator iter = userList.iterator();
			while (iter.hasNext()) {
				HashMap<String, String> userInfo = (HashMap<String, String>)iter.next();
				String userName = userInfo.get(NAME_KEY);
				String toAddress = userInfo.get(EMAIL_KEY);
				if (toAddress.length() <= 0) 
					userInfo.remove(userInfo);
			}	
		}
		return userList;
		
	}


	
	
}
