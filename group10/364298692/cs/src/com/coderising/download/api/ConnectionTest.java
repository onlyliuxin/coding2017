package com.coderising.download.api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.impl.ConnectionManagerImpl;

import junit.framework.Assert;

public class ConnectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetContentLength() throws Exception {
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection conn = connMan.open("http://baidu.com");
		Assert.assertEquals(35470, conn.getContentLength());
	}
	
	@Test
	public void testRead() throws Exception{
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection conn = connMan.open("");
		byte[] data = conn.read(0, 3333);
		
		Assert.assertEquals(3334, data.length);
		
		data = conn.read(0, 1023);
		Assert.assertEquals(1024, data.length);
		
		data = conn.read(1024, 2023);
		Assert.assertEquals(1000, data.length);
		
		//以上测试没有断言内容是否正确
	}

}
