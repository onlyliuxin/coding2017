package main.coding_170309;

import main.coding_170309.api.Connection;
import main.coding_170309.api.ConnectionException;
import main.coding_170309.api.ConnectionManager;
import main.coding_170309.api.DownloadListener;
import main.coding_170309.impl.ConnectionManagerImpl;
import main.coding_170309.impl.DownloadListenerImpl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by peter on 2017/3/9.
 */
public class FileDownloader {
    String url;
    ConnectionManager cm;
    DownloadListener listener;
    public FileDownloader(String url){
        this.url = url;
    }

    public void execute() throws ConnectionException, IOException {
        // 在这里实现你的代码， 注意： 需要用多线程实现下载
        // 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
        // (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
        // (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
        //     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
        // 具体的实现思路：
        // 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
        cm = new ConnectionManagerImpl();
        Connection conn = cm.open(url);
        HttpURLConnection urlConnection =(HttpURLConnection) new URL(url).openConnection();
        int length = urlConnection.getContentLength();
        // 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
        ExecutorService executor = Executors.newFixedThreadPool(3);
        if(length>0){
            executor.execute(new DownloadThread(conn,0,length/3-1));
            executor.execute(new DownloadThread(conn,length/3,length/3*2-1));
            executor.execute(new DownloadThread(conn,length/3*2,length));
        }else{
            executor.execute(new DownloadThread(conn,0,1024*150));
            executor.execute(new DownloadThread(conn,1024*150,1024*300));
            executor.execute(new DownloadThread(conn,1024*300,1024*450));
        }


        // 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
        // 3. 把byte数组写入到文件中
        // 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
        setListener(new DownloadListenerImpl(executor));
        listener.notifyFinshed();

    }
    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }
    public void setConnectionManager(ConnectionManager ucm){
        this.cm = ucm;
    }

    public DownloadListener getListener() {
        return listener;
    }
}
