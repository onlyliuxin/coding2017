package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread {

    Connection conn;
    int startPos;
    int endPos;
    File file;
    DownloadListener downloadListener;

    public DownloadThread(File file,Connection conn, int startPos, int endPos,DownloadListener listener) {
        this.file =file;
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.downloadListener = listener;
    }

    public void run() {
        Thread current = Thread.currentThread();
        System.out.println(current.getName()+"开始下载");
        RandomAccessFile randomAccessFile = null;
        try {
             randomAccessFile = new RandomAccessFile(file,"rw");
            byte[] bytes = conn.read(startPos,endPos);
            randomAccessFile.seek(startPos);
            randomAccessFile.write(bytes);


        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            conn.close();
            System.out.println(current.getName()+"下载完成");
            downloadListener.notifyFinished();
        }
    }
}