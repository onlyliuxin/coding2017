package download.test;

import download.api.Connection;
import download.api.ConnectionManager;
import download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



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
		Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");
		Assert.assertEquals(35470, conn.getContentLength());
	}
	
	@Test
	public void testRead() throws Exception{
		
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");
		
		byte[] data = conn.read(0, 35469);
		
		Assert.assertEquals(35470, data.length);
		
		data = conn.read(0, 1023);
		
		Assert.assertEquals(1024, data.length);
		
		data = conn.read(1024, 2023);
		
		Assert.assertEquals(1000, data.length);
		
		
		// 测试不充分，没有断言内容是否正确
	}


}
