package com.coderising.ood.ocp;

public class SmsLogger extends Logger{

	
   public void log(String msg){
		
		SMSUtil.send(msg);
	}

}
