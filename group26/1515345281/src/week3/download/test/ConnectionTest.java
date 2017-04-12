package week3.download.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week3.download.api.Connection;
import week3.download.api.ConnectionManager;
import week3.download.api.impl.ConnectionManagerImpl;


public class ConnectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContentLength() throws Exception{
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection conn = connMan.open("http://pic.sc.chinaz.com/files/pic/pic9/201508/apic14052.jpg");		
		Assert.assertEquals(112504, conn.getContentLength());
	}
	
	@Test
	public void testRead() throws Exception{
		
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection conn = connMan.open("http://pic.sc.chinaz.com/files/pic/pic9/201508/apic14052.jpg");
		
		byte[] data = conn.read(0, 35469);
		
		Assert.assertEquals(35470, data.length);
		
		data = conn.read(0, 1023);
		
		Assert.assertEquals(1024, data.length);
		
		data = conn.read(1024, 2023);
		
		Assert.assertEquals(1000, data.length);
		
		
		// 测试不充分，没有断言内容是否正确
	}

}
