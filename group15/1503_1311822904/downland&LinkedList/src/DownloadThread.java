import api.Connection;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class DownloadThread extends Thread{
	private CountDownLatch threadsSignal;
	private Connection conn;
	private int startPos;
	private int endPos;
	static Map<Integer,byte[]> partMap = (Map<Integer,byte[]>) Collections.synchronizedMap(new TreeMap<Integer, byte[]>(
			new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	}));



	public DownloadThread(Connection conn, int startPos, int endPos, CountDownLatch threadSignal){
		this.threadsSignal = threadSignal;
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	//TODO 具体下载的线程

		try {
			partMap.put(startPos,conn.read(startPos,endPos));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(conn != null){
				conn.close();
			}
			threadsSignal.countDown();//线程结束时计数器减1
		}
	}
}
