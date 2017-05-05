package rui.study.coding2017.jobs3.download.impl;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * 测试链接实现
 * Created by 赵睿 on 2017/3/15.
 */
public class ConnectionImplTest {
    private String url="http://sw.bos.baidu.com/sw-search-sp/software/952c9d6e73f50/QQ_8.9.20029.0_setup.exe";

    private ConnectionImpl connection=new ConnectionImpl(new URL(url));

    public ConnectionImplTest() throws IOException {
    }

    @Test
    public void read() throws Exception {
        byte[] bs=connection.read(0,connection.getContentLength());
        System.out.println(bs.length);
        FileOutputStream fileOutputStream=new FileOutputStream(new File("D://eee.exe"));
        fileOutputStream.write(bs);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Test
    public void getContentLength() throws Exception {
        System.out.println(connection.getContentLength());
    }

}