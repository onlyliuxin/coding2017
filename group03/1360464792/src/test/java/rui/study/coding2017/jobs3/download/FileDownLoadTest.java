package rui.study.coding2017.jobs3.download;

import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownLoadTest {
    @Test
    public void test() throws IOException {
//        String path="https://download.jetbrains.8686c.com/idea/ideaIU-2016.3.5.exe";
        String path="http://sw.bos.baidu.com/sw-search-sp/software/952c9d6e73f50/QQ_8.9.20029.0_setup.exe";

        URL url=new URL(path);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        System.out.println(httpURLConnection.getContentLength());

        httpURLConnection.setConnectTimeout(100000);

        InputStream inputStream=httpURLConnection.getInputStream();

        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);

        byte[] bytes=new byte[1024*1024];

        int tmp=0;

        String fileName=url.getPath();
        fileName=fileName.substring(fileName.lastIndexOf("/")+1);

        File file=new File("D:\\360downloads\\"+fileName);

        FileOutputStream outputStream=new FileOutputStream(file);

        while((tmp=bufferedInputStream.read(bytes))!=-1){
            outputStream.write(bytes);
        }

        outputStream.close();
        inputStream.close();
    }
}
