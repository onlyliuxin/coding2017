package com.sprint.download.api;

import com.sprint.download.impl.ConnectionManagerImpl;
import org.junit.Assert;
import org.junit.Test;
public class ConnectionTest {

	@Test 
	public void testGetContentLength() throws Exception {
		ConnectionManager connMan = new ConnectionManagerImpl();	
		Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");
		Assert.assertEquals(35470, conn.getContentLength());
	}

	@Test
	public void testRead() throws Exception {
		ConnectionManager connMan = new ConnectionManagerImpl();	
		Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");

	}
}
