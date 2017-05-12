package com.coderising.litestruts;

import java.util.HashMap;
import java.util.Map;

public class xmlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String actionName = "login";

		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
        
        View view  = Struts.runAction(actionName,params);  
        String str = view.getJsp();
        System.out.println(str);
	}

}
