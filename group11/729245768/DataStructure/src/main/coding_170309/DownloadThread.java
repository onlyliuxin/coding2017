package main.coding_170309;

import main.coding_170309.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by peter on 2017/3/9.
 */
public class DownloadThread implements Runnable {
    Connection conn;
    int startPos;
    int endPos;
    public DownloadThread(Connection conn,int startPos,int endPos){
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public void run() {
        try {
            try {
                String fileName = "d://liying"+conn.getURL().substring(conn.getURL().lastIndexOf('.'));
                byte[] data= conn.read(startPos,endPos);
                RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");
                randomAccessFile.seek(startPos);
                randomAccessFile.write(data,0,data.length);
                randomAccessFile.close();
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
