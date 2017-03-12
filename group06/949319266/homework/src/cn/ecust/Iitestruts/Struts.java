package cn.ecust.Iitestruts;

import java.util.Map;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

        /*
         
		0. 璇诲彇閰嶇疆鏂囦欢struts.xml
 		
 		1. 鏍规嵁actionName鎵惧埌鐩稿搴旂殑class 锛� 渚嬪LoginAction,   閫氳繃鍙嶅皠瀹炰緥鍖栵紙鍒涘缓瀵硅薄锛�
		鎹畃arameters涓殑鏁版嵁锛岃皟鐢ㄥ璞＄殑setter鏂规硶锛� 渚嬪parameters涓殑鏁版嵁鏄� 
		("name"="test" ,  "password"="1234") ,     	
		閭ｅ氨搴旇璋冪敤 setName鍜宻etPassword鏂规硶
		
		2. 閫氳繃鍙嶅皠璋冪敤瀵硅薄鐨別xectue 鏂规硶锛� 骞惰幏寰楄繑鍥炲�硷紝渚嬪"success"
		
		3. 閫氳繃鍙嶅皠鎵惧埌瀵硅薄鐨勬墍鏈塯etter鏂规硶锛堜緥濡� getMessage锛�,  
		閫氳繃鍙嶅皠鏉ヨ皟鐢紝 鎶婂�煎拰灞炴�у舰鎴愪竴涓狧ashMap , 渚嬪 {"message":  "鐧诲綍鎴愬姛"} ,  
		鏀惧埌View瀵硅薄鐨刾arameters
		
		4. 鏍规嵁struts.xml涓殑 <result> 閰嶇疆,浠ュ強execute鐨勮繑鍥炲�硷紝  纭畾鍝竴涓猨sp锛�  
		鏀惧埌View瀵硅薄鐨刯sp瀛楁涓��
        
        */
    	
    	return null;
    }    

}
