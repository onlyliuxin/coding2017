package io.github.vxzh.download;

import io.github.vxzh.download.api.Connection;
import io.github.vxzh.download.api.ConnectionManager;
import io.github.vxzh.download.api.DownloadListener;

import java.util.concurrent.CyclicBarrier;

public class FileDownloader {

    private String path;

    private DownloadListener listener;

    private ConnectionManager cm;

    private static final int THREAD_NUM = 3;

    public FileDownloader(String path) {
        this.path = path;

    }

    public void execute() {

        CyclicBarrier barrier = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                listener.notifyFinished();
            }
        });

        Connection conn = null;
        try {

            conn = cm.open(this.path);
            //实际的文件长度
            int length = conn.getContentLength();
            //平均每一个线程下载的文件大小.
            int blockSize = length / THREAD_NUM;
            for (int threadId = 1; threadId <= THREAD_NUM; threadId++) {
                int startIndex = (threadId - 1) * blockSize;
                int endIndex = threadId * blockSize - 1;
                //最后一个线程下载的长度
                if (threadId == THREAD_NUM) {
                    endIndex = length - 1;
                }

                System.out.println("线程：" + threadId + "下载:---" + startIndex + "--->" + endIndex);
                new DownloadThread(conn, startIndex, endIndex, barrier).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }


    }

    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }


    public void setConnectionManager(ConnectionManager ucm) {
        this.cm = ucm;
    }

    public DownloadListener getListener() {
        return this.listener;
    }

}
