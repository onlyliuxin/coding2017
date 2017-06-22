package com.mimieye.odd.ocp.logger.Impl;

import com.mimieye.odd.ocp.config.LoggerConstant;
import com.mimieye.odd.ocp.logger.LoggerInterface;
import com.mimieye.odd.ocp.method.MethodInterface;
import com.mimieye.odd.ocp.type.TypeInterface;
import com.mimieye.odd.ocp.util.MailUtil;
import com.mimieye.odd.ocp.util.SMSUtil;

public class LoggerImpl implements LoggerInterface{
	
	private TypeInterface type;
	private MethodInterface method;
	private Integer typeInt;
	private Integer methodInt;

	public LoggerImpl(int typeInt, int methodInt) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		this.typeInt = typeInt;
		this.methodInt = methodInt;
		init();
	}

	private void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		String typeClass = LoggerConstant.TPYE_MAP.get(typeInt);
		String methodClass = LoggerConstant.METHOD_MAP.get(methodInt);
		TypeInterface typeInterface = (TypeInterface)Class.forName(typeClass).newInstance();
		MethodInterface methodInterface = (MethodInterface)Class.forName(methodClass).newInstance();
		this.type = typeInterface;
		this.method = methodInterface;
	}

	public void log(String msg){
		method.execute(type.getMsg(msg));
	}
}

