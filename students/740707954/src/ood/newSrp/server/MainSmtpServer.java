package ood.newSrp.server;

import ood.newSrp.conf.Configuration;
import ood.oldSrp.ConfigurationKeys;

/**
 * 主要服务器
 * Created by Administrator on 2017/6/15 0015.
 */
public class MainSmtpServer extends SmtpServer {

    public MainSmtpServer() {
        address = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
        host = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
    }

    /**
     * 设置服务器地址
     */
    public void setServerAddr() {
        address = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    /**
     * 设置服务器host
     */
    public void setServerHost() {
        host = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
    }
}
