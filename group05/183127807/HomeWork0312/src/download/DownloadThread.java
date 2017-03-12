package download;

import download.api.Connection;
import download.api.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
			URL fileURL = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) fileURL.openConnection();
			httpURLConnection.setReadTimeout(5000);
			httpURLConnection.setRequestMethod("GET");

			httpURLConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

			File file = new File(savePath);

			if (file != null) {
				raf = new RandomAccessFile(file, "rwd");
			}
			raf.seek(startPos);

			InputStream inputStream = httpURLConnection.getInputStream();
			byte[] bytes = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(bytes)) != -1) {
				raf.write(bytes,0,length);
			}
			inputStream.close();
			raf.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
