package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coderising.ood.bean.UserBean;

/**
 * <p>Title: PromotionMail</p>
 * <p>Description: 邮件发送处理</p>
 * <p>Company: smartisan</p>
 * @author Administrator
 * @date 2017年6月18日
 */
public class PromotionMail {

	public static void main(String[] args) throws Exception {
		
		//1、获取降价产品信息
		File f = new File("E:\\gitpro\\liuxin\\coding2017\\liuxin\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
		Set<String> proIds = readFile(f);
		
		//2、根据降价产品的ID获取关注该产品的用户信息
		List<UserBean> users = DBUtil.query(proIds);
		
		//3、向这些用户发送邮件
		MailUtil.sendEmail(users);
	}

	/**
	 * 读取文件，获取降价产品信息
	 * @param file
	 * @return 产品信息的ID
	 * @throws IOException
	 */
	private static Set<String> readFile(File file) throws IOException{
		Set<String> proIds = new HashSet<String>();
		BufferedReader br = null;
		boolean flag = true;
		try {
			br = new BufferedReader(new FileReader(file));
			while(flag){
				String temp = br.readLine();
				if(temp != null && !"".equals(temp)){
					String[] data = temp.split(" ");
					proIds.add(data[0]);
					System.out.println("产品ID = " + data[0]);
					System.out.println("产品描述 = " + data[1] + "\n");
				}else{
					flag = false;
				}
			}
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		return proIds;
	}
}
