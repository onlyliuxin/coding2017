package com.coderising.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** 
 * @author greenhills
 * @version 创建时间：2017年2月27日 下午11:48:56 
 * 
 */
public class DefaultAction {
	private ActionMapping actionMapping;
	private  Object targetAction;   //由DefaultAction反射调用的目标对象
	
	//构造方法，实例化对象
	public DefaultAction(ActionMapping actionMapping) {
		this.actionMapping = actionMapping;
		//实例化对象
		try {
			this.targetAction = Class.forName(this.actionMapping.getClassName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化参数
	 * DefaultAction.java
	 * @param 
	 * @author greenhills
	 * 2017年2月27日 下午11:58:05
	 */
	public void initParam(Map<String,String> parameters){
		Class clazz=this.targetAction.getClass();
		Set<String> keys=parameters.keySet();
		try {
			for(String key:keys){
				String _key = getFirstUpper(key);
				//调用set方法赋值
				Method method=clazz.getDeclaredMethod("set"+_key,clazz.getDeclaredField(key).getType());
				method.invoke(this.targetAction, parameters.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 调用实例方法
	 * DefaultAction.java
	 * @param @return
	 * @author greenhills
	 * 2017年2月27日 下午11:53:56
	 */
	public String runMethod(){
		String methodName=this.actionMapping.getMethod();
		Class clazz=this.targetAction.getClass();
		String result="success";
		//调用set方法赋值
		try {
			Method method=clazz.getDeclaredMethod(methodName);
			result = (String) method.invoke(this.targetAction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取action字段的值
	 * DefaultAction.java
	 * @param @param fields
	 * @param @return
	 * @author greenhills
	 * 2017年2月28日 上午12:39:01
	 */
	public Map<String,Object> getFieldValue(String[] fields){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			Class clazz=this.targetAction.getClass();
			for(String field:fields){
				String _field = getFirstUpper(field);
				//调用get方法获取值
				Method method=clazz.getDeclaredMethod("get"+_field);
				result.put(field,method.invoke(this.targetAction));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 将首字母改为大写
	 * DefaultAction.java
	 * @param @param val
	 * @param @return
	 * @author greenhills
	 * 2017年2月28日 上午12:24:34
	 */
	private String getFirstUpper(String val){
		return val.substring(0, 1).toUpperCase()+val.substring(1);
	}
	
	
	public ActionMapping getActionMapping() {
		return actionMapping;
	}
	public void setActionMapping(ActionMapping actionMapping) {
		this.actionMapping = actionMapping;
	}
	public Object getTargetAction() {
		return targetAction;
	}
	public void setTargetAction(Object targetAction) {
		this.targetAction = targetAction;
	}
}
