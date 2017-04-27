package week3_fileDownloader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import week3_fileDownloader.api.Connection;

public class DownloadThread extends Thread{
	Connection conn;
	CyclicBarrier barrier;
	int startPos;
	int endPos;
	String path = "";
	int step = 1024*200; // ÿ200kдһ���ļ�
	public DownloadThread(CyclicBarrier _barrier, Connection conn, int startPos, int endPos,String filepath){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.path = filepath;
		this.barrier = _barrier;
	}
	public void run(){	
		// ��ȡ���ص��ֽ����飬д���ļ���ע������һ�����̳߳���
		// �������ֻ��дһ���ֵ��ļ�
		
		// ��connect�����ȡ�ֽ����飬���û���ֽ������ˣ��ͱ�ʾ�ⲿ���������
		// ��filepathд�ļ�
		if(conn == null) return;
		int curEndPos = startPos;
		while(curEndPos<endPos){
			startPos = curEndPos;
			curEndPos += step;
			if (curEndPos > endPos) 
				curEndPos = endPos;
			try {
				byte[] data = conn.read(startPos, curEndPos);
				RandomAccessFile files = new RandomAccessFile(path,"rw");
				files.seek(startPos);
				files.write(data);
				files.close();
				System.out.println("startPos"+startPos + ", length:"+data.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		conn.close();
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
