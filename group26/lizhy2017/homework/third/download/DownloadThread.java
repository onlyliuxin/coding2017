package third.download;


import java.io.IOException;
import java.io.RandomAccessFile;

import third.download.api.Connection;
import third.download.api.ConnectionException;
import third.download.api.DownloadListener;

public class DownloadThread extends Thread {

    private RandomAccessFile accessFile;
    private DownloadListener listener;
    private Connection conn;
    private int startPos;
    private int endPos;

    public DownloadThread(Connection conn, int startPos, int endPos, DownloadListener listener) {
        this.listener = listener;
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;

    }

    public void run() {
        try {
            byte[] bytes = conn.read(startPos, endPos);
            accessFile = new RandomAccessFile("./" + conn.getFileName(), "rw");
            accessFile.seek(startPos);
            accessFile.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } finally {
            if (null != accessFile)
                try {
                    accessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (null != conn)
                conn.close();
            if (null != listener)
                listener.notifyFinished();
        }
    }
}
