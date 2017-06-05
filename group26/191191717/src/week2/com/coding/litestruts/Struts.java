package week2.com.coding.litestruts;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts
{
    
    public static View runAction(String actionName, Map<String, String> parameters)
    {
        
        /*
         * 
         * 0. 璇诲彇閰嶇疆鏂囦欢struts.xml
         * 
         * 1. 鏍规嵁actionName鎵惧埌鐩稿搴旂殑class 锛� 渚嬪LoginAction, 閫氳繃鍙嶅皠瀹炰緥鍖栵紙鍒涘缓瀵硅薄锛� 鎹畃arameters涓殑鏁版嵁锛岃皟鐢ㄥ璞＄殑setter鏂规硶锛� 渚嬪parameters涓殑鏁版嵁鏄�
         * ("name"="test" , "password"="1234") , 閭ｅ氨搴旇璋冪敤 setName鍜宻etPassword鏂规硶
         * 
         * 2. 閫氳繃鍙嶅皠璋冪敤瀵硅薄鐨別xectue 鏂规硶锛� 骞惰幏寰楄繑鍥炲�硷紝渚嬪"success"
         * 
         * 3. 閫氳繃鍙嶅皠鎵惧埌瀵硅薄鐨勬墍鏈塯etter鏂规硶锛堜緥濡� getMessage锛�, 閫氳繃鍙嶅皠鏉ヨ皟鐢紝 鎶婂�煎拰灞炴�у舰鎴愪竴涓狧ashMap , 渚嬪 {"message": "鐧诲綍鎴愬姛"} ,
         * 鏀惧埌View瀵硅薄鐨刾arameters
         * 
         * 4. 鏍规嵁struts.xml涓殑 <result> 閰嶇疆,浠ュ強execute鐨勮繑鍥炲�硷紝 纭畾鍝竴涓猨sp锛� 鏀惧埌View瀵硅薄鐨刯sp瀛楁涓��
         * 
         */
        File file = new File("src/week2/com/coding/litestruts/struts.xml");
        resolveXml(file);
        Object object = actions.get(actionName);
        Set<String> set = parameters.keySet();
        Iterator<String> it = set.iterator();
        View view = new View();
        try
        {
            while (it.hasNext())
            {
                String keyName = it.next();
                String setMethodName = "set" + keyName.substring(0, 1).toUpperCase() + keyName.substring(1);// 缁勫悎鏂规硶鍚�
                
                Method method = object.getClass().getMethod(setMethodName, String.class);
                if (method == null)
                {
                    continue;
                }
                method.invoke(object, parameters.get(keyName));// 鎵цset鏂规硶
            }
            
            Method exeMethod = object.getClass().getMethod("execute");
            String result = (String)exeMethod.invoke(object);// 鑾峰彇execute鏂规硶杩斿洖鍊�
            Field[] fs = object.getClass().getDeclaredFields();
            HashMap<String, String> resultMap = new HashMap<String, String>();
            for (Field f : fs)
            {
                String fieldName = f.getName();
                Method m2 = object.getClass()
                    .getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                String rs2 = (String)m2.invoke(object);
                resultMap.put(fieldName, rs2);
            }
            view.setParameters(resultMap);
            if (null != result)
            {
                String viewURL = (String)actions.get(actionName + "_" + result);
                view.setJsp(viewURL);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return view;
    }
    
    static Map<String, Object> actions = new HashMap<String, Object>();
    
    /**
     * 瑙ｆ瀽XML,灏唜ml鏄犲皠瀵硅薄锛屼互鍙婅繑鍥炲�肩殑灞炴�у瓨鍏ctions
     * 
     * @param file
     */
    @SuppressWarnings("unchecked")
    public static void resolveXml(File file)
    {
        SAXReader read = new SAXReader();
        Element rootElement = null;
        try
        {
            rootElement = read.read(file).getRootElement();
            List<Element> actionList = rootElement.elements("action");
            for (Element ele : actionList)
            {
                String name = ele.attributeValue("name");
                String clz = ele.attributeValue("class");// 鎵惧埌绫诲悕
                Object obj = Class.forName(clz).newInstance();
                actions.put(name, obj);
                if (ele.hasContent())// 濡傛灉杩樻湁鑺傜偣
                {
                    List<Element> list = ele.elements("result");
                    for (Element e : list)
                    {
                        String cName = e.attributeValue("name");
                        String cValue = e.getTextTrim();
                        actions.put(name + "_" + cName, cValue);// 绀轰緥key:login_success
                    }
                }
            }
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
        throws Exception
    {
        File file = new File("src/week2/com/coding/litestruts/struts.xml");
        resolveXml(file);
        System.out.println(actions.toString());
        // Map<String, String> parameters = new HashMap<String, String>();
        // parameters.put("name", "luojunyi");
        // runAction("login", parameters);
        // Class clazz = Class.forName("week2.com.coding.litestruts.LoginAction");
        // Object obj = clz.newInstance();
        // System.out.println(obj.toString());
        // Method m1 = clz.getMethod("setName", java.lang.String.class);
        // System.out.println(m1.getName());
        // m1.invoke(obj, "hello");
        // Method m2 = clz.getMethod("getName");
        // System.out.println(m2.getName());
        // String s = (String)m2.invoke(obj);
        // System.out.println(s);
        // Field[] f = clazz.getDeclaredFields();
        // for (int i = 0; i < f.length; i++)
        // {
        // System.out.println(f[i].getName());
        // }
    }
}
