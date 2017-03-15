package struts_Reflect;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;




public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) throws Exception {

        /*
         
		0. ��ȡ�����ļ�struts.xml
 		
 		1. ����actionName�ҵ����Ӧ��class �� ����LoginAction,   ͨ������ʵ��������������
		��parameters�е����ݣ����ö����setter������ ����parameters�е������� 
		("name"="test" ,  "password"="1234") ,     	
		�Ǿ�Ӧ�õ��� setName��setPassword����
		
		2. ͨ��������ö����exectue ������ ����÷���ֵ������"success"
		
		3. ͨ�������ҵ����������getter���������� getMessage��,  
		ͨ�����������ã� ��ֵ�������γ�һ��HashMap , ���� {"message":  "��¼�ɹ�"} ,  
		�ŵ�View�����parameters
		
		4. ����struts.xml�е� <result> ����,�Լ�execute�ķ���ֵ��  ȷ����һ��jsp��  
		�ŵ�View�����jsp�ֶ��С�
        
        *
        */
    	
    	//�������������������className���ַ���
    	String className = null;
    	
    	//---------����XML�ļ�---------------------------------------------------------------------------
    	
    	 // ����saxReader����  
        SAXReader reader = new SAXReader();  
        // ͨ��read������ȡһ���ļ� ת����Document����  
        Document document = reader.read(new File("src/struts_Reflect/Struts.xml"));  
        //��ȡ���ڵ�Ԫ�ض���  
        Element root = document.getRootElement();  
        //System.out.println("Root: " + root.getName());
           
        // ����������ȡԪ��name����ֵΪ��login����class���Ե�ֵ�����浽����className�С�
        for (Iterator iter = root.elementIterator(); iter.hasNext();)
        {
            Element e = (Element) iter.next();
            String name = e.attributeValue("name");
            
            if(name.equals("login"))
            {
            	className = e.attributeValue("class");
            }
                        
        }
        
        //-----------�����ȡLoginAction���ʵ���������䷽��---------------------------------------------------------
        
        //��ȡʵ��
        Class clazz = Class.forName("struts_Reflect.LoginAction");
        Object obj = clazz.newInstance();
        
        //����setName �� setPassword����
        Method mSetName = clazz.getMethod("setName", String.class);
        Method mSetPassWord = clazz.getMethod("setPassword", String.class);
        mSetName.invoke(obj, parameters.get("name"));
        mSetPassWord.invoke(obj, parameters.get("password"));
        
        //����excute����
        Method mExecute = clazz.getMethod("execute", null);
        String result = (String) mExecute.invoke(obj, null);      
        System.out.println(result);
        
        //�ҵ�����getter���������Ժ�ֵ�ŵ�parameterMap�С�
        Method[] methods = clazz.getDeclaredMethods();
//        HashMap<String, String> paraMap = new HashMap<String, String>();
        
    	ArrayList<String> al = new ArrayList<String>();
    	al.add("name");
    	al.add("password");
    	al.add("message");
    	String key = null;
    	String value = null;
    	Field field = null;
    	
    	for(int i = 0; i < al.size(); i++)
    	{
    		key = al.get(i);
    		field = clazz.getDeclaredField(key);
    		field.setAccessible(true);
    		value = (String) field.get(obj);
    		parameters.put(key, value);
    		System.out.println(key+"---"+value);
    	}
    	
    	
    	
    	View view = new View();
    	view.setParameters(parameters);
    	
    	//----------��JSP�ֶηŵ�View������-------------------------------------
    	
    	//��ȡ����Ϊaction��Ԫ�ؽڵ�
    	Element actionE = root.element("action");
    	
    	// ����������ȡԪ��result�е�����
    	String rValue = null;
    	
        for (Iterator iter = actionE.elementIterator(); iter.hasNext();)
        {
            Element e = (Element) iter.next();
            String name = e.attributeValue("name");
            
            if(name.equals(result))
            {
            	rValue = e.getText();
            	view.setJsp(rValue);
            	System.out.println(rValue);
            }
                        
        }
   
    	return view;
 
    }  

}