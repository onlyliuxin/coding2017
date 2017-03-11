package com.coderising.litestruts;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.coderising.parser.XmlParser;
import com.coderising.parser.XmlParser.eNode;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
    	String path = "src/com/coderising/litestruts/struts.xml";
    	File rootFile = new File("");
    	File rootDir = new File(rootFile.getAbsolutePath());
    	File configFile = findFile(rootDir, "struts.xml");
    	if(configFile != null){
    		//System.out.println("find: " + configFile.getAbsolutePath());
    		path = configFile.getAbsolutePath();
    	}
    	View retView = new View();
    	XmlParser parser = new XmlParser(path);
    	//parser.dump();
    	List<eNode> branchNodesList = parser.getActionNodeList();
    	try {
	    	for(eNode node : branchNodesList){
	    		//System.out.println(node);
	    		String classPath = node.attributeMap.get("class");
	    		Class<?> actionObjectClass = getClassObj(classPath);
	    		if(actionObjectClass != null){
		    		Constructor<?> actionConstructorObject = createClass(actionObjectClass);
		    		Object newInstance = null;
					newInstance = actionConstructorObject.newInstance();

		    		Method[] methods = actionObjectClass.getDeclaredMethods();
//		    		for(Method m : methods){
//		    			System.out.println("method: " + m.getName());
//		    		}
		    		Iterator<Entry<String, String>> itr = parameters.entrySet().iterator();
		    		while(itr.hasNext()){
		    			Entry<String, String> call = itr.next();
		    			//System.out.println(call.getKey() + " " + call.getValue());
		    			String key = call.getKey();
		    			String val = call.getValue();
		    			String setMethodName = "set" + Character.toUpperCase(key.charAt(0)) + key.substring(1);
		    			Method setMethod = getMethod(actionObjectClass, setMethodName, String.class);
		    			if(newInstance != null){
		    				setMethod.invoke(newInstance, val);
		    			}			
		    		}
		    		Method exeMethod = actionObjectClass.getDeclaredMethod("execute", null);
		    		Class<?> returnType = exeMethod.getReturnType();
		    		Object returnValue = exeMethod.invoke(newInstance, null);
		    		HashMap<Object, Object> viewHashParam = new HashMap<>();
		    		for(Method m:methods){
		    			if(m.getName().startsWith("get")){
		    				Object v = m.invoke(newInstance, null);
		    				String getMethodName = m.getName().substring(3);
		    				getMethodName = Character.toLowerCase(getMethodName.charAt(0)) + getMethodName.substring(1);
		    				//System.out.println("get method: " + getMethodName); 
		    				viewHashParam.put(getMethodName, v);
		    			}
		    		}
		    		String viewStr = null;
		    		for(eNode resultNode : node.subNodes){
		    			String nameAttr = resultNode.attributeMap.get("name") ;
		    			if(nameAttr.equals(returnValue)){
		    				//System.out.println("node: " + resultNode.element_name + " " + resultNode.attributeMap.get("name") + " matches");
		    				for(eNode jspNode : resultNode.subNodes){
		    					if(jspNode.isLeaf){
		    						viewStr = jspNode.element_raw_content;
		    						//System.out.println("jsp str: " + viewStr);
		    						break;
		    					}
		    				}
		    			}
		    		}
		    		
		    		retView.setJsp(viewStr);
		    		retView.setParameters(viewHashParam);
					
	    		}
	    	}
    	} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return retView;
    }    
    
    public static Class<?> getClassObj(String classPath){
    	Class<?> classObject = null;
    	try {
			classObject = Class.forName(classPath);
		} catch (ClassNotFoundException e) {
			//System.err.println(e.getMessage());
		}
    	return classObject;
    }
    
    public static Constructor<?> createClass(Class<?> classObject){
    	Constructor<?> constructor = null;
		try {
			constructor = classObject.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
    	return constructor;
    }
    
    public static Method getMethod(Class<?> clazz, String methodName, Class<?> paramClass){
    	Method ret = null;
    	try {
			ret = clazz.getMethod(methodName, paramClass);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
    	return ret;
    }
    
    public static void main(String[] args){


    	//System.out.println("absolute path: " + absPath);
    	Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
        String actionName = "login"; 
        
        View view  = Struts.runAction(actionName,params);
    }
    
    public static File findFile(File targetDir, String targetFileName){
    	//System.out.println("targetDir: " + targetDir.getAbsolutePath() + " " + targetDir.isDirectory());
    	File configFile = null;
    	File[] files = targetDir.listFiles();
    	
    	if(files != null){
    		for(File f: files){
    			//System.out.println("test: " + f.getName());
    			if(f.isDirectory()){
    				//System.out.println("Dir: " + f.getName());
    				configFile = findFile(f, targetFileName);
    				if(configFile != null){
    					break;
    				}
    			}
    			else{
    				//System.out.println("File: " + f.getName());
    				if(f.getAbsolutePath().endsWith(targetFileName)){
    					configFile = f;
    					break;
    				}
    			}
    		}
    	}
    	return configFile;
    }
    
    public static FilenameFilter createFilter(String targetName){
    	return new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				System.out.println("testing: " + dir + " name: " + name);
				return name.endsWith(targetName);
			}
    		
    	};
    }
    
    public static List<File> getDirectories(File targetFile){
    	List<File> childDirs = new LinkedList<>();
    	if(targetFile.isDirectory()){
    		File[] files = targetFile.listFiles();
    		for(File f: files){
    			if(f.isDirectory()) childDirs.add(f);
    		}
    	}
    	
    	return childDirs;
    }

}
