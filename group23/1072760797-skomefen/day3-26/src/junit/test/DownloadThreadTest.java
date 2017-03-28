package junit.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.DownloadThread;
import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.impl.ConnectionManagerImpl;

public class DownloadThreadTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRun() throws ConnectionException, IOException {
		ConnectionManager cm = new ConnectionManagerImpl();
		String url = "http://localhost:8080/TimeCapsule/readme.txt";
		Connection conn = cm.open(url);
		DownloadThread dt = new DownloadThread(conn, 0, conn.getContentLength());
		dt.start();
		
		File file = new File("e://readme.txt");
		FileInputStream in  = new FileInputStream(file);
		byte[] b = new byte[10];
		int len = 0;
		ArrayList<byte[]> a = new ArrayList<byte[]>();
		while((len=in.read(b))>0){
			a.add(b);
		}

		int length = conn.getContentLength();
		if(length%3==0){
			for(int i=1;i<=3;i++){
				int startPos = length/3*i-1;
				int endPos = length/3*(i-1);
				new DownloadThread(conn, endPos, startPos).start();
			}
		}else{
			for(int i=1;i<=2;i++){
				
				int startPos = length/3*(i-1);
				int endPos = length/3*i-1;
				new DownloadThread(conn, startPos, endPos).start();
			}
			int startPos = length/3*2;
			new DownloadThread(conn, startPos, length).start();
		}
		
		file = new File("e://readme.txt");
		in  = new FileInputStream(file);
		b = new byte[10];
		len = 0;
		ArrayList<byte[]> a1 = new ArrayList<byte[]>();
		while((len=in.read(b))>0){
			a1.add(b);
		}
		for(int i = 0;i<a.size()||i<a1.size();i++){
			Assert.assertArrayEquals(a.get(i), a1.get(i));
		}
		
	}

}
