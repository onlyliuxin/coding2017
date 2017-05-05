package com.coderising.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.google.common.collect.Lists;

public class FileDownloader {

    private static final int DEFAULT_THREAD_NUM = 3;

    private File downFile = null;

    private List<DownloadListener> listeners = Lists.newArrayList();

    private CompletionService<Boolean> completionService = new ExecutorCompletionService<Boolean>(
            Executors.newFixedThreadPool(5));

    private ConnectionManager cm;

    private String url;

    public FileDownloader(String url) {
        this.url = url;

    }

    public void execute() {
        Connection conn = null;
        try {
            conn = cm.open(this.url);
            createFile(conn);
            Long length = conn.getContentLength();
            int startIndex = 0;
            int size = Double.valueOf(Math.ceil(length.intValue()) / DEFAULT_THREAD_NUM).intValue()  ;
            for (int i = 0; i < DEFAULT_THREAD_NUM; i++) {
                conn = cm.open(this.url, startIndex, startIndex + size);
                completionService.submit(new DownloadCallable(downFile, conn, startIndex, startIndex + size));
                startIndex += size;
            }
            for (int i = 0; i < DEFAULT_THREAD_NUM; i++) {
                try {
                    if (completionService.take().get()) {
                        System.out.println("下载成功");
                    } else {
                        System.out.println("下载失败");
                        throw new RuntimeException("下载失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (DownloadListener listener : listeners) {
                listener.notifyFinished();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    private boolean createFile(Connection conn) {
        RandomAccessFile out = null;
        try {
            if (downFile == null) {
                downFile = new File("/Users/haibo/Code/github/coding2017-1/" + ""
                        + "group08/286060098/3-12/src/com/coderising/" + conn.downLoadFileName());
                if (downFile.exists()) {
                    return true;
                }
                if (downFile.isDirectory()) {
                    System.out.println("目标文件不能为目录！");
                    return false;
                }
                if (!downFile.getParentFile().exists()) {
                    if (!downFile.getParentFile().mkdirs()) {
                        System.out.println("创建目标文件所在的目录失败！");
                        return false;
                    }
                }
                try {
                    if (downFile.createNewFile()) {
                        System.out.println("创建文件成功");
                        out = new RandomAccessFile(downFile, "rwd");
                        out.setLength(conn.getContentLength());
                        System.out.println(downFile.length());
                        return true;
                    } else {
                        System.out.println("创建文件失败");
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("创建文件失败" + e.getMessage());
                    return false;
                }
            }
            return true;
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

    public void addListener(DownloadListener listener) {
        this.listeners.add(listener);
    }

    public void setConnectionManager(ConnectionManager ucm) {
        this.cm = ucm;
    }

    public List<DownloadListener> getListener() {
        return this.listeners;
    }

}
