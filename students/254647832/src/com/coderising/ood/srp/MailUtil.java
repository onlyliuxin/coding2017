package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.bean.MailBean;
import com.coderising.ood.bean.ProductBean;
import com.coderising.ood.bean.UserBean;

/**
 * <p>Title: MailUtil</p>
 * <p>Description: 邮件工具类</p>
 * <p>Company: smartisan</p>
 * @author Administrator
 * @date 2017年6月18日
 */
public class MailUtil {

	/**
	 * 获取邮件正文信息
	 * @param user 用户信息
	 * @return 邮件正文
	 */
	private static String getMassege(UserBean user){
		List<ProductBean> list = user.getPros();
		StringBuilder s = new StringBuilder();
		s.append("尊敬的 ");
		s.append(user.getName());
		s.append(", 您关注的产品 ");
		for(ProductBean pro : list){
			s.append(pro.getProductDesc()).append("、");
		}
		s.delete(s.length()-1, s.length());
		s.append(" 降价了，欢迎购买!");
		return s.toString();
	}
	
	/**
	 * 获取待发送的邮件信息列表
	 * @param users 用户信息
	 * @return 待发送邮件列表
	 */
	private static List<MailBean> getMailInf(List<UserBean> users){
		List<MailBean> retList = new ArrayList<MailBean>();
		MailBean mailInf;
		//获取邮件服务器配置信息
		if (!users.isEmpty()) {
			for(UserBean bean : users){
				mailInf = new MailBean();
				mailInf.setToAddress(bean.getEmail());
				mailInf.setSubject("您关注的产品降价了");
				mailInf.setMessage(getMassege(bean));
				retList.add(mailInf);
			}
		}
		return retList;
	}
	
	/**
	 * 发送邮件
	 * @param users 待发送的用户
	 */
	public static void sendEmail(List<UserBean> users) {
		List<MailBean> mailList = getMailInf(users);
		if(!mailList.isEmpty()){
			//获取邮件服务器信息
			Configuration config = new Configuration();
			String server = config.getProperty(ConfigurationKeys.SMTP_SERVER);
			String altServer = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
			
			System.out.println("开始发送邮件...");
			System.out.println("发件箱：" + config.getProperty(ConfigurationKeys.EMAIL_ADMIN));
			
			for(MailBean mailInf : mailList){
				try{
					send(server, mailInf);
				}catch (Exception e){
					try {
						send(altServer, mailInf);
					} catch (Exception e2) {
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
					}
				}
			}
			System.out.println("邮件发送结束...");
		}else{
			System.out.println("没有邮件发送");
		}
		
	}
	
	/**
	 * 邮件发送主方法
	 * @param server 发送服务器
	 */
	public static void send(String server, MailBean mailInf){
		
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("To:").append(mailInf.getToAddress()).append("\n");
		buffer.append("Subject:").append(mailInf.getSubject()).append("\n");
		buffer.append("Content:").append(mailInf.getMessage()).append("\n");
		
		System.out.println(buffer.toString());
	}

}
