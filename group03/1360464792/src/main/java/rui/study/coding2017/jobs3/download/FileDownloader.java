package rui.study.coding2017.jobs3.download;


import rui.study.coding2017.jobs3.download.api.Connection;
import rui.study.coding2017.jobs3.download.api.ConnectionException;
import rui.study.coding2017.jobs3.download.api.ConnectionManager;
import rui.study.coding2017.jobs3.download.api.DownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FileDownloader {

	public static int threadNum=5;
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;

	private static String filePath;

    static String path="D:\\360downloads";

    File pathFile;

	public static String getFilePath() {
		return filePath;
	}

	public FileDownloader(String _url) {
		this.url = _url;
		filePath=path+"/"+url.substring(url.lastIndexOf("/")+1);

        pathFile=new File(path);
        if(!pathFile.exists()||!pathFile.isDirectory()){
            pathFile.mkdir();
        }
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}

	public void execute(){
        Connection conn = null;
		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();
            setRandomAccessFile(length);
            List<DownloadThread> list = getDownloadThreads(conn, length);
            waitForDownLoad(list);
            listener.notifyFinished();
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(conn != null){
				conn.close();
			}
		}
	}

    /**
     * 配置 随机访问文件
     * 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件；
     * @param length
     * @throws IOException
     */
    private void setRandomAccessFile(int length) throws IOException {
        try {
            RandomAccessFile randomAccessFile=new RandomAccessFile(filePath,"rw");
            randomAccessFile.setLength(length);
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得下载线程，为下一步阻塞做准备
     * @param conn 连接
     * @param length 长度
     * @return
     */
    private List<DownloadThread> getDownloadThreads(Connection conn, int length) {
        int startPos;
        int endPos;
        List<DownloadThread> list=new ArrayList<DownloadThread>();
        for (int i = 0; i < threadNum; i++) {
            //从0开始
            startPos=i*(length/threadNum);
            //从length/threadNum开始，最后为所有长度
            endPos=(i==threadNum-1)?length-1:(i+1)*(length/threadNum);

            DownloadThread downloadThread=new DownloadThread(conn,startPos,endPos);
            downloadThread.start();
            list.add(downloadThread);
        }
        return list;
    }

    /**
     * 阻塞线程列表，等待线程列表全部执行完成
     * @param list 线程列表
     */
    private void waitForDownLoad(List<DownloadThread> list) {
        for (DownloadThread aList : list) {
            try {
                aList.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
