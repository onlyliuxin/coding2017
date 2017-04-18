package dataStruct.com.coderising.download.impl;

import dataStruct.com.coderising.download.api.Connection;
import dataStruct.com.coderising.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songbao.yang on 2017/3/11.
 */
public class ConnectionImplTest {

    private Connection connection;
    private final String URL = "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=8e07ecabb7fb4316051f7d7a10a54642/5882b2b7d0a20cf482c772bf73094b36acaf997f.jpg";

    @Before
    public void setUp() throws Exception {
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
        this.connection = connectionManager.open(URL);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void read() throws Exception {
        byte[] read = connection.read(0, 100);
        System.out.println(read);
    }

    @Test
    public void getContentLength() throws Exception {
        int contentLength = connection.getContentLength();
        System.out.println(contentLength);
    }

    @Test
    public void close() throws Exception {

    }

}