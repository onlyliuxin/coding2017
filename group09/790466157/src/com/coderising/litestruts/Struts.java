package com.coderising.litestruts;

import java.util.Map;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

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
        
        */
    	
    	
    	return null;
    }    

}
