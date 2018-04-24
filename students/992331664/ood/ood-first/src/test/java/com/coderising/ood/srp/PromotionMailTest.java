package com.coderising.ood.srp;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.coderising.ood.srp.service.ProductService;
import com.coderising.ood.srp.service.SubscriptionsService;
import com.coderising.ood.srp.service.impl.ProductServiceImpl;
import com.coderising.ood.srp.service.impl.SubscriptionsServiceImpl;

public class PromotionMailTest {

	PromotionMail promotionMail;

	@Before
	public void before() {
		SubscriptionsService subscriptionsService = new SubscriptionsServiceImpl();
		ProductService productService = new ProductServiceImpl();
		promotionMail = new PromotionMail(subscriptionsService, productService);
	}

	@Test
	public void testSendPromotionMail() throws Exception {
		String path = System.getProperty("user.dir");

		path += "\\bin\\com\\coderising\\ood\\srp\\product_promotion.txt";

		File file = new File(path);

		promotionMail.sendPromotionMail(file, false);
	}
}
