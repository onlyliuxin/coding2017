package com.pan.download;




import com.pan.download.api.Connection;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread {

    Connection conn;
    int startPos;
    int endPos;
    CyclicBarrier barrier;
    String localFile;

    public DownloadThread(Connection conn, int startPos, int endPos, String localFile, CyclicBarrier barrier) {
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.localFile = localFile;
        this.barrier = barrier;
    }

    public void run() {
        try {
            System.out.println("Begin to read [" + startPos + "-" + endPos + "]");
            byte[] data = conn.read(startPos, endPos);
            RandomAccessFile file = new RandomAccessFile(localFile, "rw");
            file.seek(startPos);
            file.write(data);
            file.close();
            conn.close();
            System.out.println(this.currentThread().getName() + "once over");
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
