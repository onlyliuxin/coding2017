package com.coderising.download;

import com.coderising.download.api.Connection;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
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
    }

    public void run() {
        System.out.println("Begin to read [" + startPos + "-" + endPos + "]");

        try {
			byte[] data= conn.read(startPos,endPos);
            RandomAccessFile file = new RandomAccessFile(localFile, "rw");
            file.seek(startPos);
            file.write(data);
            file.close();
            conn.close();
            barrier.await();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
