package me.lzb.download;

import me.lzb.download.api.Connection;
import me.lzb.download.api.ConnectionManager;
import me.lzb.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by LZB on 2017/3/27.
 */
public class ConnectionTest {

    private static final String imageUrl = "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-499994.png";


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testContentLength() throws Exception{
        ConnectionManager connMan = new ConnectionManagerImpl();
        Connection conn = connMan.open(imageUrl);
        Assert.assertEquals(3440179, conn.getContentLength());
        conn.close();
    }

    @Test
    public void testRead() throws Exception{

        ConnectionManager connMan = new ConnectionManagerImpl();
        Connection conn = connMan.open(imageUrl);

        byte[] data = conn.read(0, 35469);

        Assert.assertEquals(35470, data.length);

        data = conn.read(0, 1023);

        Assert.assertEquals(1024, data.length);

        //TODO  这个地方会一直卡住不执行
        data = conn.read(1024, 2023);

        Assert.assertEquals(1000, data.length);


        // 测试不充分，没有断言内容是否正确
    }
}
