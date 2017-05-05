package cn.net.pikachu.download.impl.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by pikachu on 2017/3/13.
 */
public class DownloadFileWithThreadPool {
    public void getFileWithThreadPool(String urlLocation, String filePath, int poolLength) throws IOException {
        Executor threadPool = Executors.newFixedThreadPool(poolLength);

        long len = getContentLength(urlLocation);
        for (int i = 0; i < poolLength; i++) {
            long start = i * len / poolLength;
            long end = (i + 1) * len / poolLength - 1;
            if (i == poolLength - 1) {
                end = len;
            }
            DownloadWithRange download = new DownloadWithRange(urlLocation, filePath, start, end);
            threadPool.execute(download);
        }

    }

    public static long getContentLength(String urlLocation) throws IOException {
        URL url = null;
        if (urlLocation != null) {
            url = new URL(urlLocation);
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        long len = conn.getContentLength();

        return len;
    }

    public static void main(String[] args) throws IOException {
//        String url = "http://localhost:8080/mybatis-jpetstore-6.0.0/actions/Catalog.action";
        String url = "http://yydl.duowan.com/4/setup/YYSetup-8.20.0.1-zh-CN.exe";
        new DownloadFileWithThreadPool().getFileWithThreadPool(url,"D:/Download/test/yy.exe",1000);
    }
}