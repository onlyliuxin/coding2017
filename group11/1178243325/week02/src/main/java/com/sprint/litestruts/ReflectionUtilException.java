package com.sprint.litestruts;

import java.lang.reflect.InvocationTargetException;
public class ReflectionUtilException extends RuntimeException {
	
	public ReflectionUtilException(String msg) {
		super(msg);
	}
	public ReflectionUtilException(IllegalAccessException e) {
		super(e);
	}

	public ReflectionUtilException(IllegalArgumentException e) {
		super(e);
	}

	public ReflectionUtilException(InvocationTargetException e) {
		super(e);
	}
}
