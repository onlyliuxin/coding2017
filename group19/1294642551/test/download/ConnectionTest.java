package download;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;
import download.impl.ConnectionManagerImpl;

public class ConnectionTest {

	@Test
	public void testGetContentLength() throws ConnectionException{
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");
		Assert.assertEquals(35470, conn.getContentLength());
	}
	
	@Test
	public void testRead() throws ConnectionException, IOException{
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");
		byte[] data = conn.read(0, 35470);
		Assert.assertEquals(35470, data.length);
		
		data = conn.read(0, 1023);
		Assert.assertEquals(1024, data.length);
		
		data = conn.read(1024, 2023);
		Assert.assertEquals(1000, data.length);
	}
}
