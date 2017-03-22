package com.github.miniyk2012.coding2017.coderising.download;

import com.github.miniyk2012.coding2017.coderising.download.api.Connection;
import com.github.miniyk2012.coding2017.coderising.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
* ConnectionImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>Mar 12, 2017</pre> 
* @version 1.0 
*/ 
public class ConnectionImplTest {
    String url = "http://inews.gtimg.com/newsapp_bt/0/1209438116/1000";
    ConnectionManagerImpl connectionManager = null;
    Connection connection = null;

    @Before
    public void before() throws Exception {
        connectionManager = new ConnectionManagerImpl();
        connection = connectionManager.open(url);

    }

    @After
    public void after() {
        connection.close();
    }
    /**
    *
    * Method: read(int startPos, int endPos)
    *
    */
    @Test
    public void testRead() throws Exception {
        int length = connection.getContentLength();
        byte[] biz = connection.read(0, length-1);
        System.out.println(biz.length);
    }

    /**
    *
    * Method: getContentLength()
    *
    */
    @Test
    public void testGetContentLength() throws Exception {
        int length = connection.getContentLength();
        System.out.println(length);
    }

} 
