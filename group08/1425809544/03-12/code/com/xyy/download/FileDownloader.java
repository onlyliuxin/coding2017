package xyy.download;

import vvv.download.api.ConnectionException;
import xyy.download.api.Connection;
import xyy.download.api.ConnectionManager;
import xyy.download.api.DownloadListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by 14258 on 2017/3/14.
 */
public class FileDownloader {

    private static  final int threadNumber = 3;//下载线程数
    private String url;//传入的url地址
    public String fileName = "D://download";

    private DownloadListener listener;//下载监听器;
    private ConnectionManager connectionManager;//下载管理器;
    //设置url
    public FileDownloader(String url) {
        this.url = url;
    }
    //设置下载链接管理
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    //设置下载监听器
    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }


    //执行下载
    public void execute() {

        Connection conn = null;
        try {
            try {
                conn = connectionManager.open(this.url);//又连接管理器打开根据url打开来连接
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
            int length = conn.getContentLength();//获取conn长度
            this.fileName = fileName + "//" + conn.getFileName();//获取文件名字
            conn.close();
            startDownload(length, threadNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }


    }

    private void startDownload(int length, int i)   {
        if (length <= 0) {
            listener.notifyFinished();
            return;
        }

        //设置一个和将要下载的文件一个同样大小的临时文件
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(this.fileName, "rw");
            randomAccessFile.setLength(length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int block = length / threadNumber;
        block = block == 0 ? block : block + 1;
        System.out.println("length"+length+"block"+block);
        for (i=0;i<threadNumber;i++){
            int start = i*block;
            int end = (i+1)*block-1;
            try {
                new DownloadThread(connectionManager.open(url),start,end,"下载线程"+1,this).start();
            } catch (ConnectionException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    public int downNum;
    public synchronized void addDownNumber() {
        downNum++;
        System.out.println("下载完成..."+downNum);
        if (downNum>=threadNumber){
            if (listener!=null){
                listener.notifyFinished();
            }
        }




    }
}
