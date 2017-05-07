/**
 * 
 */
package junit.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coderising.download.api.Connection;
import com.coderising.download.impl.ConnectionImpl;

/**
 * @author Oliver
 *
 */
public class ConnectionImplTest {
	
	Connection conn = null;
	InputStream in = null;
	
	@Before
	public void setUp() throws Exception {
		String url = "http://localhost:8080/TimeCapsule/readme.txt";
		conn = new ConnectionImpl(url);
		
		URL u = new URL(url);
		in =  u.openStream();
	}

	
	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testRead() throws IOException {
		
		byte[] connbuffer = conn.read(0, 99);
		Assert.assertEquals(100, connbuffer.length);
		byte[] connbuffer2 = conn.read(0, 99);
		Assert.assertArrayEquals(connbuffer,connbuffer2);
		
		
		byte[] b = new byte[100];
		in.read(b);

		Assert.assertArrayEquals(b, conn.read(0, 99));
		
		byte[] b1 = new byte[50];
		for(int i=0;i<50;i++){
			b1[i]=b[50+i];
		}
		
		Assert.assertArrayEquals(b1, conn.read(50, 99));
		
		

	}


	@Test
	public void testGetContentLength() throws Exception {
		
		byte[] b = new byte[1024];
		int len = 0;
		int count = 0;
		while((len=in.read(b))>0){
			count+=len;
		}
		Assert.assertEquals(count, conn.getContentLength());
		
	}


	@Test
	public void testClose() {
		conn.close();
	}

}
