package com.coderising.download;

import com.coderising.download.api.Connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread {

    Connection conn;
    int startPos;
    int endPos;

    public DownloadThread(Connection conn, int startPos, int endPos) {

        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public void run() {
        Thread current = Thread.currentThread();
        System.out.println(current.getName());
        File file = new File("F:\\down.jpg");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            byte[] bytes = conn.read(startPos,endPos);
            randomAccessFile.seek(startPos);
            randomAccessFile.write(bytes);
            conn.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}