package com.coderising.jvm.engine;

public class Heap {
	
	/**
	 * û��ʵ���������գ� ���Զ��������´����Ķ��� ��û�м�¼��һ�����ݽṹ����
	 */
	
	private static Heap instance = new Heap();
	
	private Heap(){  }
	
	public static Heap getInstance(){
		return instance;
	}
	
	public JavaObject newObject(String clzName){
		
		JavaObject jo = new JavaObject(JavaObject.OBJECT);
		jo.setClassName(clzName);
		return jo;	
	}
	
	public JavaObject newString(String value){
		JavaObject jo = new JavaObject(JavaObject.STRING);
		jo.setStringValue(value);
		return jo;
	}
	
	public JavaObject newFloat(float value){
		JavaObject jo = new JavaObject(JavaObject.FLOAT);
		jo.setFloatValue(value);
		return jo;
	}
	
	public JavaObject newInt(int value){
		JavaObject jo = new JavaObject(JavaObject.INT);
		jo.setIntValue(value);
		return jo;
	}

}
