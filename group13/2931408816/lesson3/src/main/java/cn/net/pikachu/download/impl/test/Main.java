package cn.net.pikachu.download.impl.test;

import cn.net.pikachu.download.api.ConnectionException;
import cn.net.pikachu.download.api.ConnectionManager;
import cn.net.pikachu.download.impl.ConnectionManagerImpl;

/**
 * Created by pikachu on 2017/3/12.
 */
public class Main {
    public static void main(String[] args) throws ConnectionException {
        ConnectionManager cm = new ConnectionManagerImpl();
//        cm.open("http://localhost:8080/mybatis-jpetstore-6.0.0/");
        cm.open("http://n1.itc.cn/img8/wb/recom/2016/07/26/146946506808699302.JPEG");
    }
}
