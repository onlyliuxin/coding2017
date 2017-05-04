package week8.jvm.engine;

import java.util.HashMap;
import java.util.Map;

import week8.jvm.clz.ClassFile;
import week8.jvm.constant.MethodRefInfo;
import week8.jvm.loader.ClassFileLoader;
import week8.jvm.method.Method;

public class MethodArea {

	private static final MethodArea methodArea=new MethodArea();
	private ClassFileLoader loader;
	private Map<String,ClassFile> map=new HashMap<String,ClassFile>();

	/**
	 * 注意：做了极大的简化， ClassLoader 只有一个， 实际JVM中的ClassLoader,是一个双亲委托的模型
	 */
	
	private MethodArea(){};
		
	public static MethodArea getInstance() {
		return methodArea;
	}

	public void setClassFileLoader(ClassFileLoader loader) {
		this.loader=loader;
	}

	public Method getMainMethod(String className) {
		
		ClassFile clzFile=this.findClassFile(className);
		
		return clzFile.getMainMethod();
	}

	public Method getMethod(MethodRefInfo methodRef){
		String className=methodRef.getClassName();
		ClassFile clzFile=this.findClassFile(className);
		
		Method method=clzFile.getMethod(methodRef.getMethodName(), methodRef.getParamAndReturnType());
		
		if(method == null){
			throw new RuntimeException("method can't be found : " + methodRef.toString());
		}
		
		return method;
	}
	
	public Method getMethod(String className,String methodName,String paramAndReturnType){
		
		ClassFile clzFile = this.findClassFile(className);
		
		Method method = clzFile.getMethod(methodName, paramAndReturnType);
		
		if(method == null){
			
			throw new RuntimeException("method can't be found : \n" 
					+ "class: " + className
					+ "method: " + methodName
					+ "paramAndReturnType: " + paramAndReturnType);
		}
		
		return method;
	}
	
	
	public ClassFile findClassFile(String className) {
		
		if(map.get(className)!=null){
			return map.get(className);
		}
		
		ClassFile clzFile=loader.loadClass(className);
		map.put(className, clzFile);
		
		return clzFile;
	}

}








