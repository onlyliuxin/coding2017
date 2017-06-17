package com.coderising.ood.srp;

import com.coderising.ood.srp.utils.DBUtil;
import com.coderising.ood.srp.utils.MailUtil;
import com.coderising.ood.srp.utils.ArgsUtil;

import java.io.IOException;
import java.util.*;

/**
 * PromotionMail
 *
 * @author Chenpz
 * @package com.coderising.ood.srp
 * @date 2017/6/12/23:33
 */
public class PromotionMail {

    private ProductInfo productInfo;
    private List<MailInfo> mailInfoList = new ArrayList<>();

    private static final String NAME_KEY    = "NAME";
    private static final String EMAIL_KEY   = "EMAIL";

    public PromotionMail(){}

    public PromotionMail(ProductInfo productInfo) throws Exception {
        this.productInfo = productInfo;
        initMailInfoList(loadMailingList());
    }

    /**
     * 获取每个型号的手机关注的人员信息列表
     * @return
     * @throws Exception
     */
    private List<Map<String, String>> loadMailingList() throws Exception {
        String sql = "select name from subscriptions "
                        + "where product_id= '" + productInfo.getProductID() +"' "
                        + "and send_mail=1 ";
        return DBUtil.query(sql);
    }

    /**
     * 组装促销邮件的内容信息
     * @param mailingList
     */
    private void initMailInfoList(List<Map<String, String>> mailingList) {
        if (ArgsUtil.isNotEmpty(mailingList)){
            for (Map<String, String> map : mailingList){
                // 初始化 mailInfoList
                mailInfoList.add(buildMailInfo(map));
            }
        }
    }

    /**
     * 组装邮件内容信息
     * @param userInfo
     * @return
     */
    private MailInfo buildMailInfo(Map<String, String> userInfo){
        String name = userInfo.get(NAME_KEY);
        String subject = "您关注的产品降价了";
        String message = "尊敬的 "+name+", 您关注的产品 " + productInfo.getProductDesc() + " 降价了，欢迎购买!" ;
        String toAddress = userInfo.get(EMAIL_KEY);
        return new MailInfo(toAddress, subject, message);
    }

    /**
     * 发送促销邮件
     * @param debug
     * @throws IOException
     */
    public void sendEMails(boolean debug) throws IOException {
        System.out.println("开始发送邮件... ...");
        if (ArgsUtil.isNotEmpty(mailInfoList)) {
            for (MailInfo mailInfo : mailInfoList){
                MailUtil.sendEmail(mailInfo.toAddress, mailInfo.subject, mailInfo.message, debug);
            }
        }else {
            System.out.println("没有邮件发送... ...");
        }
    }

    class MailInfo{

        private String toAddress = null;
        private String subject = null;
        private String message = null;

        MailInfo(String toAddress, String subject, String message){
            this.toAddress = toAddress;
            this.subject = subject;
            this.message = message;
        }
    }

}
