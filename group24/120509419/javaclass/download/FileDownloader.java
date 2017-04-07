/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaclass.download.api.Connection;
import javaclass.download.api.ConnectionException;
import javaclass.download.api.ConnectionManager;
import javaclass.download.api.DownloadListener;

public class FileDownloader {

    String url;

    DownloadListener listener;

    ConnectionManager cm;

    private int numOfThreads = 2;

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    private File outputFile;
    
    
    public FileDownloader(String _url) {
        this.url = _url;
        

    }

    public void execute() throws IOException {
        // 在这里实现你的代码， 注意： 需要用多线程实现下载
        // 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
        // (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
        // (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
        //     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
        // 具体的实现思路：
        // 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
        // 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
        // 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
        // 3. 把byte数组写入到文件中
        // 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法

        // 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
        Connection conn = null;
        try {
            // 针对每个线程，重新打开一个连接
            conn = cm.open(this.url);

            int length = conn.getContentLength();
            System.err.println("Total Length:"+length);
            int threadSize = length / numOfThreads;
//            int remainSize = length % numOfThreads;
            int threadCount = 0;
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(numOfThreads);
            DownloadThread[] dtArr = new DownloadThread[numOfThreads];
            for (int i = 0; i < numOfThreads-1; i++) {
                conn = cm.open(this.url);
                System.err.println("Thread "+threadCount++);
                dtArr[i] = new DownloadThread(conn, i * threadSize, (i + 1) * threadSize - 1);
                fixedThreadPool.submit(dtArr[i]);
            }
            conn = cm.open(this.url);
            dtArr[numOfThreads-1] = new DownloadThread(conn,(numOfThreads-1) * threadSize, length - 1);
            fixedThreadPool.submit(dtArr[numOfThreads-1]);
            fixedThreadPool.shutdown();
            // 提交之后，等到 365天 
            fixedThreadPool.awaitTermination(365, TimeUnit.DAYS);
            
            // 合并所有文件
            FileOutputStream fos = new FileOutputStream(outputFile);
            for(DownloadThread curDt:dtArr){
                FileInputStream fis = new FileInputStream(curDt.getCurTmpFile());
                byte[] bufferedByteArr = new byte[1024*8];
                int readSize;
                while((readSize=fis.read(bufferedByteArr))!=-1){
                    fos.write(bufferedByteArr, 0, readSize);
                }
                fis.close();
            }
            fos.close();
            
            listener.notifyFinished();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, ex);
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
