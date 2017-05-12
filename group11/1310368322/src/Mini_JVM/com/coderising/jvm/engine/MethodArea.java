package com.coderising.jvm.engine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.loader.ClassFileLoader;
import com.coderising.jvm.method.Method;

public class MethodArea {
public static final MethodArea instance = new MethodArea();
	
	/**
	 * ע�⣺�������˼���ļ򻯣� ClassLoader ֻ��һ���� ʵ��JVM�е�ClassLoader,��һ��˫��ί�е�ģ��
	 */
	
	private ClassFileLoader clzLoader = null;
	
	Map<String,ClassFile> map = new HashMap<String,ClassFile>();
	
	private MethodArea(){		
	}
	
	public static MethodArea getInstance(){
		return instance;
	}
	
	public void setClassFileLoader(ClassFileLoader clzLoader){
		this.clzLoader = clzLoader;
	}
	
	public Method getMainMethod(String className) throws IOException{
		
		ClassFile clzFile = this.findClassFile(className);
		
		return clzFile.getMainMethod();
	}
	
	
	public  ClassFile findClassFile(String className) throws IOException{
		
		if(map.get(className) != null){
			return map.get(className);
		}
		// ������class �ļ���û��load��
		ClassFile clzFile = this.clzLoader.loadClass(className);
		
		map.put(className, clzFile);
		
		return clzFile;
		
	}
	
	
	public Method getMethod(String className, String methodName, String paramAndReturnType) throws IOException{
		
		ClassFile clz = this.findClassFile(className);
		
		Method m = clz.getMethod(methodName, paramAndReturnType);
		
		if(m == null){
			
			throw new RuntimeException("method can't be found : \n" 
					+ "class: " + className
					+ "method: " + methodName
					+ "paramAndReturnType: " + paramAndReturnType);
		}
		
		return m;
	}
	
	
	public Method getMethod(MethodRefInfo methodRef) throws IOException{		
		
		ClassFile clz = this.findClassFile(methodRef.getClassName());
		
		Method m = clz.getMethod(methodRef.getMethodName(), methodRef.getParamAndReturnType());
		
		if(m == null){
			throw new RuntimeException("method can't be found : " + methodRef.toString());
		}
		
		return m;
			
	}
}
