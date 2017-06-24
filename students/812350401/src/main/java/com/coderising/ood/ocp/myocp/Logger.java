package com.coderising.ood.ocp.myocp;

public class Logger {

	Method method;
	Msg msg;
			
	public Logger(Msg aMsg, Method aMethod){
		msg= aMsg;
		method = aMethod;
	}
	public void log(String message){
        method.action(msg.msg(message));
	}
}

