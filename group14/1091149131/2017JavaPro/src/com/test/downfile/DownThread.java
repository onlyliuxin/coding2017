package com.test.downfile;

import java.io.InputStream;
import java.io.RandomAccessFile;

public class DownThread extends Thread {  
    
    // 定义字节数组（取水的竹筒）的长度  
    private final int BUFF_LEN = 32;  
      
    // 定义下载的起始点  
    private long start;  
      
    // 定义下载的结束点  
    private long end;  
      
    // 下载资源对应的输入流  
    private InputStream is;  
      
    // 将下载到的字节输出到raf中  
    private RandomAccessFile raf;  
  
      
    // 构造器，传入输入流，输出流和下载起始点、结束点  
    public DownThread(long start, long end, InputStream is, RandomAccessFile raf) {  
        // 输出该线程负责下载的字节位置  
        System.out.println(start + "---->" + end);  
        this.start = start;  
        this.end = end;  
        this.is = is;  
        this.raf = raf;  
    }  
  
    public void run() {  
        try {  
            is.skip(start);  
            raf.seek(start);  
            // 定义读取输入流内容的的缓存数组（竹筒）  
            byte[] buff = new byte[BUFF_LEN];  
            // 本线程负责下载资源的大小  
            long contentLen = end - start;  
            // 定义最多需要读取几次就可以完成本线程的下载  
            long times = contentLen / BUFF_LEN + 4;  
            // 实际读取的字节数  
            int hasRead = 0;  
            for (int i = 0; i < times; i++) {  
                hasRead = is.read(buff);  
                // 如果读取的字节数小于0，则退出循环！  
                if (hasRead < 0) {  
                    break;  
                }  
                raf.write(buff, 0, hasRead);  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        // 使用finally块来关闭当前线程的输入流、输出流  
        finally {  
            try {  
                if (is != null) {  
                    is.close();  
                }  
                if (raf != null) {  
                    raf.close();  
                }  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
    }  
}  
