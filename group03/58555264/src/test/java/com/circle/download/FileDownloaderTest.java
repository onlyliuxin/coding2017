package com.circle.download;

import com.circle.download.api.ConnectionManager;
import com.circle.download.api.DownloadListener;
import com.circle.download.impl.ConnectionManagerFactory;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by keweiyang on 2017/3/13.
 */
public class FileDownloaderTest {

    private boolean downloadFinished = false;
    private int threadNum = 3;


    @Test
    public void execute() throws Exception {
        String url = "http://hiphotos.baidu.com/240728057/pic/item/6a50e38242aad8f60cf4d2b3.jpg";
        FileDownloader downloader = new FileDownloader(url, threadNum);

        downloader.setListener(new DownloadListener() {
            @Override
            public void notifyFinished() {
                downloadFinished = true;

            }
        });

        downloader.execute();


        while (!downloadFinished) {
            System.out.println("还没有下载完成，休眠5秒");

            Thread.sleep(5000);

        }

        System.out.println("下载完成！");


    }

}