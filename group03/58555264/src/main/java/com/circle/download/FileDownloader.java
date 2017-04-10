package com.circle.download;

import com.circle.download.api.Connection;
import com.circle.download.api.ConnectionException;
import com.circle.download.api.ConnectionManager;
import com.circle.download.api.DownloadListener;
import com.circle.download.impl.ConnectionManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by keweiyang on 2017/3/10.
 */
public class FileDownloader {
    private String url;
    private DownloadListener listener;
    private ConnectionManager cm;
    private int threadNum;

    public FileDownloader(String url, int threadNum) {
        this.threadNum = threadNum;
        this.url = url;
    }

    public DownloadListener getListener() {
        return listener;
    }

    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }

    /**
     * 具体的实现思路：
     * 1、需要调用ConnectionManager的open方法打开连接，然后通过Connection.getConnection.getContentLength方法获得文件的长度
     * 2、至少启动3个线程下载，注意每个线程需要调用ConnectionManager的open方法
     * 然后调用read方法，read方法中有读取文件的开始位置和结束位置的参数，返回值是byte[]数组
     * 3、把byte数组写入到文件中
     * 4、所有的线程都下载完成以后，需要调用listener的notifiedFinished方法
     */
    public void execute() {
        Connection conn = null;
        int[] startPos = new int[threadNum];
        int[] endPos = new int[threadNum];
        RandomAccessFile raf = null;

        try {
            String[] ss = url.split("/");
            Thread[] threads = new Thread[threadNum];

            File file = new File(ss[ss.length - 1]);


            cm = ConnectionManagerFactory.getManager(file);
            conn = cm.open(this.url);
            int length = conn.getContentLength();
            System.out.println("length:" + length);


            raf = new RandomAccessFile(file, "rwd");
            raf.setLength(length);

            for (int i = 0; i < threadNum; i++) {
                int size = i * (length / threadNum);
                startPos[i] = size;

                if (i == threadNum - 1) {
                    endPos[i] = length;
                } else {
                    size = (i + 1) * (length / threadNum);
                    endPos[i] = size - 1;
                }

                threads[i] = new DownloadThread(cm.open(this.url), startPos[i], endPos[i],listener,threadNum);
                threads[i].start();
            }
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        String url2 = "http://hiphotos.baidu.com/240728057/pic/item/6a50e38242aad8f60cf4d2b3.jpg";
        String url = "http://bcbang.oss-cn-qingdao.aliyuncs.com/TLAB-in-Eden-memory.png";
        String url3 = "http://www.cnblogs.com/iwideal/p/6045118.html";
        FileDownloader downloader = new FileDownloader(url2, 2);
        downloader.execute();

    }

}
