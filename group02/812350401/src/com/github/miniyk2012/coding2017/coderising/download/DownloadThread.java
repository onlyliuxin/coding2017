package com.github.miniyk2012.coding2017.coderising.download;

import com.github.miniyk2012.coding2017.coderising.download.api.Connection;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	String fileName;
    CyclicBarrier barrier;

	public DownloadThread(String name, Connection conn, int startPos, int endPos, String fileName, CyclicBarrier barrier){
		super(name);
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.fileName = fileName;
		this.barrier = barrier;
	}
	public void run(){
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rwd")) {
            raf.seek(startPos);
            byte[] buf = conn.read(startPos, endPos);
//            String desc = Thread.currentThread().getName()+"startPos:"+startPos+",length:"+length + "buf size:"+buf.length;
//            System.out.println(desc);
            raf.write(buf, 0, buf.length);
            if (null != barrier) {
                barrier.await();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
