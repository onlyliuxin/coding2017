package com.coderising.download;

import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileDownloaderTest {

    boolean downloadFinished = false;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDownload() {

        String url =
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489721424&di=1fda6467501ab1d5e5bff43e801d14ee&imgtype=jpg&er=1&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201507%2F30%2F20150730163204_A24MX.thumb.700_0.jpeg";
        //String url = "http://apache.fayea.com/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz";

        FileDownloader downloader = new FileDownloader(url);

        ConnectionManager cm = new ConnectionManagerImpl();
        downloader.setConnectionManager(cm);

        downloader.setListener(new DownloadListener() {
            @Override
            public void notifyFinished() {
                downloadFinished = true;
            }
        });

        downloader.execute();

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