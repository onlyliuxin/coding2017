/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.struts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

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
        String strutsFilePath = getProRealPath(Struts.class) + File.separator + "struts.xml";
        System.out.println("Reading File: " + strutsFilePath);

        BufferedReader br = new BufferedReader(new FileReader(strutsFilePath));
        String inline;
        String curActionName = null;
        String className = null;
        HashMap<String, String> status2action = new HashMap<String, String>();
        while ((inline = br.readLine()) != null) {
            if (inline.contains("<action")) {
                String[] info = inline.replaceAll("^.*<action\\s+name\\s*=\\s*\"(.*?)\".*class\\s*=\\s*\"(.*?)\".*$", "$1\t$2").split("\t");
//                System.err.println(info[0]);
//                System.err.println(info[1]);
                curActionName = info[0];
                className = info[1];
            } else if (inline.contains("<result")) {
                String[] info = inline.replaceAll("^.*<result name=\"(.*?)\">(.*?)</result>.*$", "$1\t$2").split("\t");
                status2action.put(info[0], info[1]);
            } else if (inline.contains("</action>")) {
                if (actionName.equals(curActionName)) {
                    Class c = Class.forName(className);
                    Object curInstance = c.newInstance();

                    Method setNameMethod = c.getMethod("setName", String.class);
                    setNameMethod.invoke(curInstance, parameters.get("name"));
                    Method setPasswordMethod = c.getMethod("setPassword", String.class);
                    setPasswordMethod.invoke(curInstance, parameters.get("password"));

                    Method excuteMethod = c.getMethod("execute");
                    // 返回 sucess 或者 fail
                    String status =(String) excuteMethod.invoke(curInstance);
                    
                    String curJsp = status2action.get(status);
                    
                    Method getMessageMethod = c.getMethod("getMessage");
                    
                    View curView = new View();
                    curView.setJsp(curJsp);
                    Map<String,String> curMap = new HashMap<String,String>();
                    curMap.put("message",(String) getMessageMethod.invoke(curInstance));
                    curView.setParameters(curMap);
                    return curView;
                    
                }
            }
        }
        System.err.println("No Match Aciton");
        return null;
    }

    public static String getProRealPath(Class inClass) {
        URL url = inClass.getProtectionDomain().getCodeSource().getLocation();

        String filePath = null;
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar")) {
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        File file = new File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;
    }

}
