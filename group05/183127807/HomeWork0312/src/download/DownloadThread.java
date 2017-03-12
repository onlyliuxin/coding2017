package download;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.DownloadListener;
import download.impl.ConnectionManagerImpl;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{


	int startPos;
	int endPos;
	int threadID;
	String url;
	String savePath;
	DownloadListener listener;


	public DownloadThread( int threadID ,String url,int startPos, int endPos,
						   String savePath,DownloadListener listener){

		this.threadID=threadID;
		this.url = url;
		this.startPos = startPos;
		this.endPos = endPos;
		this.savePath = savePath;
		this.listener = listener;
	}
	public void run(){
		Connection conn=null;
		RandomAccessFile raf = null;


		try {
			ConnectionManagerImpl cmi = new ConnectionManagerImpl();
			conn = cmi.open(url,startPos,endPos);
			byte[] download = conn.read(startPos, endPos);
			raf = new RandomAccessFile(savePath, "rwd");
			raf.seek(startPos);
			raf.write(download);
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.close();
			}
			if (listener != null) {
				listener.notifyFinished();
			}
		}
	}
}
