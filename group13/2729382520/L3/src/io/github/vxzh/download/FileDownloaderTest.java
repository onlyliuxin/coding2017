package io.github.vxzh.download;

import io.github.vxzh.download.api.ConnectionManager;
import io.github.vxzh.download.api.DownloadListener;
import io.github.vxzh.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileDownloaderTest {
    private boolean downloadFinished = false;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDownload() {

        String url = "http://c.hiphotos.baidu.com/zhidao/pic/item/29381f30e924b8999bfaab046d061d950b7bf6cc.jpg";

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