import api.Connection;
import api.ConnectionException;
import api.ConnectionManager;
import api.DownloadListener;

import java.util.concurrent.CyclicBarrier;


public class FileDownloader {

    String url;

    DownloadListener listener;

    ConnectionManager cm;

    private static final int THREAD_NUM = 10;


    public FileDownloader(String _url) {
        this.url = _url;

    }

    public void execute() {
        // (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
        //     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
        CyclicBarrier barrier = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                listener.notifyFinished();
            }
        });
        Connection conn = null;
        try {
            //(1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
            conn = cm.open(this.url);

            // 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
            int length = conn.getContentLength();

            // 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
            // 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
            int start = 0;
            int endPos = 0;
            for (int i = 0; i < THREAD_NUM; i++) {
                endPos = start + length / THREAD_NUM;
                System.out.println(start + "=====" + endPos);
                new DownloadThread(conn, start, endPos > (length - 1) ? length - 1 : endPos, barrier).start();
                start = endPos + 1;
            }
        } catch (ConnectionException e) {
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
