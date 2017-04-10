package com.example.download;


import com.example.download.api.ConnectionManager;
import com.example.download.api.DownloadListener;
import com.example.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by qilei on 17/3/14.
 */
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
    String url ="http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg";
    String file = "/Users/qilei/tmp/tmp.jpg";
    FileDownloader downloader = new FileDownloader(url,file);

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