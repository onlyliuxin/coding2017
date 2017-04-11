package task0312.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import task0312.coderising.download.api.Connection;
import task0312.coderising.download.api.DownloadListener;
import task0312.coderising.download.impl.ConnectionManagerImpl;



public class DownloadThread extends Thread {
	private static int finishCount =0;
	String url;
	String localPath;
	int startPos;
	int endPos;
	private Object lock = new Object();
	DownloadListener listener;
	
	public DownloadThread(String url,String localPath,int startPos, int endPos,
			DownloadListener listener) {		
		this.url =url;
		this.startPos = startPos;
		this.endPos = endPos;	
		this.localPath = localPath;
		this.listener =listener;
	}
	public void run() {	
		RandomAccessFile ras = null;
		Connection conn= null;
		try {
			ConnectionManagerImpl cm = new ConnectionManagerImpl();
			conn = cm.open(url);			
			byte[] download = conn.read(startPos, endPos);					
			ras = new RandomAccessFile(localPath, "rwd");
			ras.seek(startPos);			
			ras.write(download);						
			synchronized(lock){
				finishCount++;
				if(finishCount == 6){
					listener.notifyFinished();
				}
			}			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ras!=null){
				try {
					ras.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
