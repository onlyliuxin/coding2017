package com.bruce.homework0312.mydownload;

import org.junit.Test;

public class DownloadTest {

    @Test
    public void test() {
        String path = "http://mirrors.tuna.tsinghua.edu.cn/apache/logging/log4j/2.8.1/apache-log4j-2.8.1-bin.zip";
        DownloadFileMultiThread dfmt = new DownloadFileMultiThread(path, 3);
        try {
            dfmt.download();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
