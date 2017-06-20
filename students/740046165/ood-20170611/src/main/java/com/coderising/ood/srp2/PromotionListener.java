package com.coderising.ood.srp2;

import java.io.IOException;
import java.util.List;

import com.coderising.ood.srp2.model.Product;
import com.coderising.ood.srp2.util.FileReaderUtil;
import com.coderising.ood.srp2.util.FileUtil;

/**
 * 降价促销信息
 * --> 监听器
 * @author mazan
 *
 */
public class PromotionListener {

	/**
	 * 获取促销信息
	 * --> TODO 监听器
	 * @return
	 */
	public static List<Product> getPromotionList() {
		String path = "src/main/java/com/coderising/ood/srp/product_promotion.txt";
		
		List<Product> list = null;
		
		try {
			list = FileReaderUtil.getTxt(FileUtil.getRelativePathFileInputStream(path));
		} catch (IOException e) {
			//促销不存在
			e.printStackTrace();
		}
		
		return list;
	}
}
