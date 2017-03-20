package com.coderising.download.test;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.impl.ConnectionManagerImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rubydd on 2017/3/16.
 */
public class ConnectionImplTest {
    @Test
    public void read() throws Exception {
        ConnectionManager connMan = new ConnectionManagerImpl();
        Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");
        byte[] data = conn.read(0, 35469);

        Assert.assertEquals(35470, data.length);

        data = conn.read(0, 1023);
        Assert.assertEquals(1024, data.length);

        data = conn.read(1024, 2023);
        Assert.assertEquals(1000, data.length);

    }

    @Test
    public void getContentLength() throws Exception {
        ConnectionManager connMan = new ConnectionManagerImpl();
        Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");
        Assert.assertEquals(35470, conn.getContentLength());

    }

}