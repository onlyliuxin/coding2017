package cn.net.pikachu.download;

import cn.net.pikachu.download.api.ConnectionManager;
import cn.net.pikachu.download.api.DownloadListener;
import cn.net.pikachu.download.impl.ConnectionManagerImpl;
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

//        String url = "http://localhost:8080/test.jpg";

//        String url = "http://n1.itc.cn/img8/wb/recom/2016/07/26/146946506808699302.JPEG";

//        String url = "http://localhost:8080/mybatis-jpetstore-6.0.0/";
//        String url = "http://www.cnblogs.com/iwideal/p/6045118.html";
//        String url = "http://localhost:8080/mybatis-jpetstore-6.0.0/actions/Catalog.action";
//        String url = "http://blog.csdn.net/cnhk1225/article/details/34429317";
//        String url = "http://rel.huya.com/apk/live.apk";
//        String url = "http://yydl.duowan.com/4/setup/YYSetup-8.20.0.1-zh-CN.exe";
//        String url = "https://discuss.kotlinlang.org/t/kotlin-1-1-language-reference-as-anki-https-apps-ankiweb-net-deck/2324";
        String url = "http://qtdream.com/";
        FileDownloader downloader = new FileDownloader(url);


        ConnectionManager cm = new ConnectionManagerImpl();
        downloader.setConnectionManager(cm);

        downloader.setListener(() -> downloadFinished = true);


        downloader.execute();

        // 等待多线程下载程序执行完毕
        while (!downloadFinished) {
            try {
                System.out.println("");
                System.out.println("None");
                //休眠5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Done!");


    }

}
