package com.coderising.download;

import org.junit.Assert;
import org.junit.Test;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.impl.ConnectionManagerImpl;

public class ConnectionTest {

	@Test
	public void testContentLength() throws ConnectionException{
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection con = connMan.open("https://imgsa.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=befb30b3a344ad343ab28fd5b1cb6791/1ad5ad6eddc451daae139eb5b4fd5266d1163282.jpg");
		Assert.assertEquals(90441, con.getContentLength());
		
	}
}
