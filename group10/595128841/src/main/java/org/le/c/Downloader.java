/**
 * 
 */
package org.le.c;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 多线程图片下载
 * @author yue
 * @time 2017年3月11日
 */
public class Downloader {
	
	private RandomAccessFile raf;
	private String url;
	
	public Downloader(String url,String path){
		//获取文件名
		String filePath = path+File.separator+url.substring(url.lastIndexOf("/")+1);
		System.out.println("保存路径："+filePath);
		this.url = url;
		try {
			this.raf = new RandomAccessFile(filePath,"rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public void downFile() throws IOException{
		URL uurl = new URL(url);
		URLConnection conn = uurl.openConnection();
		int cLen = conn.getContentLength();//最大可表示4G
		if(cLen < 0){
			System.out.println("无法获取文件大小");
			return;
		}
		this.raf.setLength(cLen);
		//根据文件大小选择合适线程
		int size = getByteArrayLength(cLen);
		byte[] buff = new byte[size];
		System.out.println("下载文件："+url+"，文件大小："+cLen+"字节，单个线程大小："+size);
		int len = 0;
		int offset = 0;
		int index = 0;
		try (InputStream in = conn.getInputStream()){
			while((len = in.read(buff)) != -1){
				byte[] desc = getNewByte(buff,len);
				Thread thread = new DownLoadThread(desc,offset,this);
				offset += len;
				thread.setName("线程"+(++index));
				thread.start();
			}
		} catch (MalformedURLException e) { 
			e.printStackTrace();
		}
	}
	
	private byte[] getNewByte(byte[] src,int len){
		byte[] desc = new byte[len];
		System.arraycopy(src, 0, desc, 0, len);
		return desc;
	}
	
	private int getByteArrayLength(int cLen) {
		int m = 1024 * 1024;
		int s = cLen/m;
		if(s == 0){
			return 1024 *100;
		}
		return m;
	}
	
	public synchronized void writeToFile(byte[] buff,int offset) {
		try {
			raf.seek(offset);
			raf.write(buff);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws Exception {
		String url = "http://7xq43s.com1.z0.glb.clouddn.com/yunanding-6.jpg";
		String path = "C:/work/workspace/coding2017/coding2017/group10/595128841";
		Downloader d = new Downloader(url,path);
		long st = System.currentTimeMillis();
		d.downFile();
		System.out.println("耗时："+(System.currentTimeMillis() - st)+" 毫秒");
	}
	
	static class DownLoadThread extends Thread{
		private byte[] buff;
		private int offset;
		private Downloader downloader;
		
		public DownLoadThread(byte[] buff, int offset,Downloader downloader) {
			this.buff = buff;
			this.downloader = downloader;
			this.offset = offset;
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"，length："+buff.length+"，offset："+offset);
			downloader.writeToFile(buff,offset);
		}
		
	}


}
