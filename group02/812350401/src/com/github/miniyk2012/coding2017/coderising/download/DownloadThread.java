package com.github.miniyk2012.coding2017.coderising.download;

import com.github.miniyk2012.coding2017.coderising.download.api.Connection;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	String fileName;

	public DownloadThread(String name, Connection conn, int startPos, int endPos, String fileName){
		super(name);
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.fileName = fileName;
	}
	public void run(){
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rwd")) {
            int length = endPos - startPos+1;
            raf.seek(startPos);
            byte[] buf = conn.read(startPos, endPos);
            String desc = Thread.currentThread().getName()+"startPos:"+startPos+",length:"+length + "buf size:"+buf.length;
            System.out.println(desc);
            raf.write(buf, 0, buf.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
