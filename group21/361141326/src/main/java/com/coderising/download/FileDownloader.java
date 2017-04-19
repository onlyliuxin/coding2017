package com.coderising.download;

import com.coderising.download.api.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


public class FileDownloader {

    private String url;

    private DownloadListener listener;

    private ConnectionManager connectionManager;

    public FileDownloader(String _url) {
        this.url = _url;
    }

    public void execute() {
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

            conn = connectionManager.open(this.url);

            int length = conn.getContentLength();

            int threadNum = 1;
            int averageLength = length / threadNum;
            String fileName = getFileName();
            File destinationFile = generateFile(fileName);

            System.out.println(destinationFile.getAbsolutePath());

            List<File> tempFiles = new ArrayList<>(threadNum);
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);

            for (int i = 0; i < threadNum; i++) {
                File file = generateFile(fileName + "_" + i);
                tempFiles.add(file);

                int startPosition = i * averageLength;
                int endPosition = (i + 1) * averageLength > length ? length : (i + 1) * averageLength;
                new DownloadThread(file, countDownLatch, conn, startPosition, endPosition - 1).start();
            }

            countDownLatch.await();

            mergeFiles(tempFiles, destinationFile);

            listener.notifyFinished();
        } catch (ConnectionException | InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    private File generateFile(String name) throws IOException {
        String path = "/Users/mortimer/tmp/download/" + name;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
//            throw new FileAlreadyExistsException(path);
        }

        file.createNewFile();
        return file;
    }

    private void mergeFiles(List<File> tempFiles, File destinationFile) {
        if (tempFiles != null && tempFiles.size() > 0) {
            if (tempFiles.size() == 1) {
                tempFiles.get(0).renameTo(destinationFile);
            } else {
                FileOutputStream output = null;
                FileInputStream input = null;
                try {
                    output = new FileOutputStream(destinationFile);
                    for (File tempFile : tempFiles) {
                        input =  new FileInputStream(tempFile);
                        byte[] buff = new byte[100 * 1024];
                        int length ;
                        while ((length = input.read(buff)) > 0) {
                            output.write(buff, 0, length);
                            output.flush();
                        }
                        input.close();
                        tempFile.delete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (input != null) input.close();
                        if (output != null) output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String getFileName() {
        return getFileNameFromUrl(this.url);
    }

    private String getFileNameFromUrl(String url) {
        int endIndex = url.lastIndexOf("?");
        endIndex = endIndex < 0 ? url.length() : endIndex;

        int startIndex = url.lastIndexOf("/");
        startIndex = startIndex < 0 ? 0 : startIndex;

        return url.substring(startIndex + 1, endIndex);
    }

    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }

    public void setConnectionManager(ConnectionManager ucm) {
        this.connectionManager = ucm;
    }

    public DownloadListener getListener() {
        return this.listener;
    }

}
