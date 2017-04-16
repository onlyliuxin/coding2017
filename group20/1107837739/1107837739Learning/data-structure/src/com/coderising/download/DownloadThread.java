package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread {

    private int endPos;
    private int startPos;
    private String url;
    private String destFilePath;
    private ConnectionManager connManager;
    private DownloadListener downloadListener;

    public DownloadThread(ConnectionManager connManager, String url, int startPos, int endPos, String destFilePath,
            DownloadListener downloadListener) {

        this.url = url;
        this.endPos = endPos;
        this.startPos = startPos;
        this.connManager = connManager;
        this.destFilePath = destFilePath;
        this.downloadListener = downloadListener;
    }

    @Override
    public void run() {
        Connection conn = null;
        RandomAccessFile randomAccessFile = null;
        try {
            doLog("BIN");
            conn = connManager.open(url, startPos, endPos);
            byte[] read = conn.read(startPos, endPos);
            String _filePath = destFilePath;
            if (_filePath == null || _filePath.length() == 0) {
                _filePath = conn.getFileName();
            }
            randomAccessFile = new RandomAccessFile(_filePath, "rw");
            randomAccessFile.seek(startPos);
            randomAccessFile.write(read);
            doLog("END");
        } catch (IOException e) {
            doLog("EXP");
            e.printStackTrace();
        } catch (ConnectionException e) {
            doLog("EXP");
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.close();
            }
            if (downloadListener != null) {
                downloadListener.notifyFinished();
            }
        }
    }

    private void doLog(String action) {
        System.out.println(
                "*********** " + action
                        + " ["
                        + startPos
                        + "-"
                        + endPos
                        + "]"
                        + " ***********");
    }
}