/**
 * 版权 (c) 2017 palmshe.com
 * 保留所有权利。
 */
package com.coderising.ood.srp;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
  * @Description:
  * @author palmshe
  * @date 2017年6月12日 下午10:07:23
  */
public class Main {
	public static void main(String[] args) {
		try {
			File f = new File("C:\\Users\\Administrator.PC-20170125UBDJ\\Desktop\\coder2017\\coding2017\\students\\1132643730\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
			MailUtil maiUtil= new MailUtil(Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN), Configuration.getProperty(ConfigurationKeys.SMTP_SERVER), Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
			Map good = DataGenerator.generateGoods(f);
			List users= DataGenerator.loadMailingList(good);
			if (!users.isEmpty()) {
				Iterator it= users.iterator();
				while (it.hasNext()) {
					maiUtil.sendEmail(new PromotionMail((Map)it.next(), good), true);
				}
			}
		} catch (Exception e) {
			System.out.println("构造发送邮件数据失败："+ e.getMessage());
		}
	}
}
