/**
 * 版权 (c) 2017 palmshe.com
 * 保留所有权利。
 */
package com.coderising.ood.ocp;

import com.coderising.ood.ocp.formatter.DateUtil;
import com.coderising.ood.ocp.formatter.LogFormatter;
import com.coderising.ood.ocp.handler.LogHandler;
import com.coderising.ood.ocp.handler.MailUtil;
import com.coderising.ood.ocp.handler.SMSUtil;

/**
  * @Description:
  * @author palmshe
  * @date 2017年6月19日 下午9:36:38
  */
public class Main {
	
	public static void main(String[] args) {
		LogHandler sms= new SMSUtil();
		LogHandler mail= new MailUtil();
		LogFormatter date= new DateUtil();
		Logger log= new Logger(sms, date);
		log.log("hello world");
		log= new Logger(mail, date);
		log.log("hello coder");
	}
}
