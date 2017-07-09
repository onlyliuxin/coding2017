package test.java.org.coderising.liteaop;

import java.io.File;

public class ProductUtil {
	/* 
	获取促销产品
	*/
	
	// 存放产品信息文本
	
	Production product ;
	
	public Production getPromotionalProduct(){
		
	//	filepath 产品信息路径
		String filepath = "E:/product_promotion.txt";
		
		try{
			fileUtil fu = new fileUtil();
			
			product  = fu.readFile(filepath);
						
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return product;
		
	}

}
