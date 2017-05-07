package dataStruct.com.coderising.download;

import dataStruct.com.coderising.download.api.Connection;
import dataStruct.com.coderising.download.api.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	File targetFile;
	DownloadListener listener;

	public DownloadThread(Connection conn, int startPos, int endPos, File targetFile, DownloadListener listener){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.targetFile = targetFile;
		this.listener = listener;
	}
	public void run(){
		try {
			System.out.println("线程" + this.getName() + "正在下载" + startPos + "--" + endPos + "的数据");

			byte[] content = conn.read(startPos, endPos);
			RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile, "rw");
			randomAccessFile.seek(startPos);
			randomAccessFile.write(content, 0, endPos - startPos + 1);
			randomAccessFile.close();

			System.out.println("线程" + this.getName() + "完成" + startPos + "--" + endPos + "数据的下载");

			if (FileDownloader.isDownLoadFinished()){
				listener.notifyFinished();
				System.out.println(">>>>>>>>>>>>>>线程" + this.getName() + "完成了最终的下载");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
}
