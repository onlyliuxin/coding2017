package com.coderising.ood.srp;

import java.io.File;

import com.coderising.ood.srp.bean.Product;
import com.coderising.ood.srp.service.MessageService;
import com.coderising.ood.srp.service.UserService;
import com.coderising.ood.srp.util.FileUtil;

/**
 * 模拟 商品促销通知系统
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月12日 下午11:43:57
*/
public class PromotionMail {
	
	/** 模拟注入userService */
	private UserService userService = new UserService();
	
	/** 模拟注入messageService */
	private MessageService messageService = new MessageService();

	public static void main(String[] args) throws Exception {
		File f = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		boolean emailDebug = false;
		new PromotionMail(f, emailDebug);
	}
	
	/**
	 * 商品促销通知系统
	 * <p>标题: </p>
	 * <p>描述: </p>
	 * @param file
	 * @param mailDebug
	 * @throws Exception
	*/
	public PromotionMail(File file, boolean mailDebug) throws Exception {
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		Product product = FileUtil.readFile(file);
		messageService.sendEMails(mailDebug, userService.queryUserInfo(product.getProductId()),product); 
	}
	
	
}
