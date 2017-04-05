package com.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

import com.coderising.download.api.Connection;

public class DownloadCallable implements Callable<Boolean> {

    private File downFile;

    private Connection conn;

    private int startPos;

    private int endPos;

    public DownloadCallable(File downFile, Connection conn, int startPos, int endPos) {
        this.downFile = downFile;
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public Boolean call() throws Exception {
        RandomAccessFile out = null;
        try {
            downFile.setWritable(true);
            out = new RandomAccessFile(downFile, "rwd");
            out.seek(startPos);
            System.out.println(startPos);
            byte[] buffer = conn.read(startPos, endPos);
            out.write(buffer, 0, buffer.length);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
