package coding.coderising.download;

import coding.coderising.download.api.Connection;
import coding.coderising.download.api.ConnectionManager;
import coding.coderising.download.api.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiaxun
 */
public class FileDownloader {

    private static final int THREAD_COUNT = 3;

    private int threadCount;
    private String downloadUrl;
    private DownloadListener downloadListener;
    private ConnectionManager connectionManager;
    private String savePath;

    public FileDownloader(String downloadUrl, String savePath) {
        this.downloadUrl = downloadUrl;
        this.savePath = savePath;
        this.threadCount = THREAD_COUNT;
    }

    public void execute() {
        Connection connection = null;
        RandomAccessFile out = null;
        try {
            connection = connectionManager.open(downloadUrl);
            int length = connection.getContentLength();
            connection.close();

            int downloadOffset = 0;
            List<DownloadThread> threadList = new ArrayList<>();
            for (int i = 0; i < threadCount; i++) {
                DownloadThread thread = new DownloadThread(connectionManager.open(downloadUrl), downloadOffset, downloadOffset + (i + 1) * (length / threadCount));
                threadList.add(thread);
                thread.start();
                downloadOffset = (i + 1) * (length / threadCount) + 1;
            }
            if (downloadOffset < length) {
                DownloadThread thread = new DownloadThread(connectionManager.open(downloadUrl), downloadOffset, length - 1);
                threadList.add(thread);
                thread.start();
            }

            for (DownloadThread thread : threadList) {
                thread.join();
            }

            File file = new File(savePath);
            out = new RandomAccessFile(file, "rwd");
            for (DownloadThread thread : threadList) {
                out.seek(thread.getStartPos());
                out.write(thread.getDownloadByte());
            }

            if (downloadListener != null) {
                downloadListener.notifyFinished();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void setDownloadListener(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    public DownloadListener getDownloadListener() {
        return this.downloadListener;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

}
