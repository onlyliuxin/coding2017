package com.coderising.download;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zj on 2017/3/12.
 */
public class Test {
    public static void main(String[] args){
        try {
            URL url=new URL("http://qunying.jb51.net:81/201611/books/Sparkgjsjfx_jb51.rar");
//            URL url=new URL("http://pic44.nipic.com/20140717/2531170_194615292000_2.jpg");

            InputStream inputStream=url.openStream();

            byte[] in=read(inputStream);
            byte2file(in,"d:\\t2.rar");

            System.out.println("");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static byte[] read(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=-1;
        while((len=inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        inputStream.close();

        return outputStream.toByteArray();
    }

    //byte数组到图片
    public static void byte2file(byte[] data, String path){
//        if(data.length<3||path.equals("")) return;
//        try{
//            FileOutputStream outputStream = new FileOutputStream(path);
//            outputStream.write(data,0,data.length);
//            outputStream.close();
//        } catch(Exception ex) {
//            System.out.println("Exception: " + ex);
//            ex.printStackTrace();
//        }
    }

}
