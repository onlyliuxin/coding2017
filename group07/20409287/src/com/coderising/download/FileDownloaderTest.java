package com.coderising.download;



import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

import java.io.FileNotFoundException;

public class FileDownloaderTest {

    private static boolean downloadFinished = false;

    public static void main(String[] args) {

        String url = "http://qunying.jb51.net:8080/201702/books/Wiresharkshjb.rar";

        FileDownloader downloader = new FileDownloader(url);


        ConnectionManager cm = new ConnectionManagerImpl();
        downloader.setConnectionManager(cm);

        downloader.setListener(new DownloadListener() {
            @Override
            public void notifyFinished() {
                downloadFinished = true;
            }

        });

        try {
            downloader.execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 等待多线程下载程序执行完毕
        while (!downloadFinished) {
            try {
                System.out.println("还没有下载完成，休眠五秒");
                //休眠5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下载完成！");


    }

}
