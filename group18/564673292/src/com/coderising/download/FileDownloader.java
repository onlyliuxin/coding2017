package com.coderising.download;

import com.coderising.download.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class FileDownloader {
	String url;
	DownloadListener listener;
	ConnectionManager cm;
    RandomAccessFile raf;

	final int NUM_OF_THREADS = 3;
	final String DESTINATION = "downloaded.jpg";
    static int completed = 0;

	public FileDownloader(String _url) {
		this.url = _url;
	}
	
	public void execute(){
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		//     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
		
		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
//		Connection conn = null;
//		try {
//
//			conn = cm.open(this.url);
//
//			int length = conn.getContentLength();
//
//			new DownloadThread(conn,0,length-1).start();
//
//		} catch (ConnectionException e) {
//			e.printStackTrace();
//		}finally{
//			if(conn != null){
//				conn.close();
//			}
//		}
//
		// multi-thread download
        String absolutePath = getAbsolutePath(DESTINATION);
        try {
            raf = new RandomAccessFile(absolutePath, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        try{
            conn = cm.open(this.url);
            int length = conn.getContentLength();
            int[][] partitions = FileDownloader.getPartitions(NUM_OF_THREADS, length);
            for (int i = 0; i < NUM_OF_THREADS; i++) {
                int startPos = partitions[i][0];
                int endPos = partitions[i][1];
                DownloadThread dt = new DownloadThread(conn, startPos, endPos);
                dt.setListener(new DownloadThreadListener(){
                    @Override
                    public void onThreadComplete(byte[] data) {
                        try {
                            writeFile(data, startPos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        completed++;
                        if(completed == NUM_OF_THREADS){
                            listener.notifyFinished();
                        }
                    }
                });
                dt.start();
            }
        }catch( ConnectionException e){
            e.printStackTrace();
        }
	}

    private void writeFile(byte[] data, int startPos) throws IOException {
	    raf.seek(startPos);
	    raf.write(data);
    }

    private static String getAbsolutePath(String path){
        return FileDownloader.class.getResource("").getPath() + path;
    }

	private static int[][] getPartitions(int numOfThreads, int length){
	    int[][] partitions = new int[numOfThreads][];
	    int partitionLength = (int) Math.floor(numOfThreads / length);
        for (int i = 0; i < numOfThreads; i++) {
            int startPos = i * partitionLength;
            int endPos;
            if(i == numOfThreads - 1){
                endPos = length - 1;
            }else{
                endPos = (i + 1) * partitionLength - 1;
            }
            int[] partition = new int[] {startPos, endPos};
            partitions[i] = partition;
        }
        return partitions;
    }

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	
	
	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	
	public DownloadListener getListener(){
		return this.listener;
	}
	
}
