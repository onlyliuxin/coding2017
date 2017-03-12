package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
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
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            byte[] bytes = conn.read(startPos,endPos);
            randomAccessFile.seek(startPos);
            randomAccessFile.write(bytes);
            conn.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println(current.getName()+"下载完成");
            downloadListener.notifyFinished();
        }
    }
}