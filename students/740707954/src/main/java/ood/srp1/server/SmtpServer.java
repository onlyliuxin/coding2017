package ood.srp1.server;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
public abstract class SmtpServer {
    public String address = "";
    public String host = "";

    /**
     * 设置服务器地址
     */
    abstract void setServerAddr();

    /**
     * 设置服务器host
     */
    abstract void setServerHost();
}
