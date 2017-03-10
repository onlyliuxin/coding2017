import api.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DownloadThread extends Thread{

	private Connection conn;
	private int startPos;
	private int endPos;
	static Map<Integer,byte[]> partMap = (Map<Integer,byte[]>) Collections.synchronizedMap(new HashMap<Integer,byte[]>());

	public DownloadThread(Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	//TODO 具体下载的线程

		try {
			partMap.put(startPos,conn.read(startPos,endPos));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
