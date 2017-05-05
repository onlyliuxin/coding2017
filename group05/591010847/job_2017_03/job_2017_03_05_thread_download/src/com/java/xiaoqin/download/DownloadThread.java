package com.java.xiaoqin.download;


import com.java.xiaoqin.download.api.Connection;
import com.java.xiaoqin.download.api.DownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        if (null != conn) {
            try {
                byte[] read = conn.read(startPos, endPos);
                writeFile(read);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void writeFile(byte[] buff) {
        File file = null;
        RandomAccessFile raf = null;
        try {
            file = new File(FileDownloader.FILE_DIR);
            if (!file.exists()) {
                file.createNewFile();
            }
            raf = new RandomAccessFile(file, "rw");
            raf.seek(startPos);
            raf.write(buff);
            System.out.println("线程" + Thread.currentThread().getName() + "：下载完毕 ");
            if (null != listener){
                listener.notifyFinished();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != raf) {
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    DownloadListener listener;
    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }
}
