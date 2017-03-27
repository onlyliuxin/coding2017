package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.coderising.download.api.DownloadListener;

public class DiyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			int size;
		      URL url = new URL("https://www.tutorialspoint.com/javaexamples/net_multisoc.htm");
		      URLConnection conn1 = url.openConnection();
		      size = conn1.getContentLength();
		      if (size < 0) System.out.println("file size is empty.");
		      else System.out.println("File size is = " + size + "bytes");
		      byte[] data = new byte[size];
		      URLConnection connection = url.openConnection();
				HttpURLConnection conn = (HttpURLConnection)connection;
				conn.setRequestProperty("Range", "bytes="+0+"-" + (size -1));
				conn.connect();
				InputStream is = conn.getInputStream();
				int count = 0;
				int readSum =0;
				while(readSum<size && count++ <5){
					readSum += is.read(data, readSum, size-readSum);
					//System.out.println("read " + readSum); 
				}
			  writeToFile(data, 0);
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/*
	 *  fc = fos.getChannel();
		  System.out.println("allocated position " + pos  + " data size: "+ data.length);
		  ByteBuffer bb = ByteBuffer.wrap(data);
	      int writeLength = fc.write(bb, 1000);
	      System.out.println("Write length = " + writeLength);
	 */
	public static void writeToFile(byte[] data, int pos){
		
		FileOutputStream fos = null;
		FileChannel fc = null;
		RandomAccessFile targetFile = null;
		try{
	      String currentPath = new File("").getAbsolutePath();
	      File rawFile = new File(currentPath + "/1282579502/src/com/coderising/download/test.cr2017");
		  targetFile = new RandomAccessFile(rawFile, "rw");
		  byte[] data1 = new byte[2048];
		  byte[] data2 = new byte[2048];
		  System.arraycopy(data, 0, data1, 0, 2048);
		  System.arraycopy(data, 2048, data2, 0, 2048);
		  System.out.println("data1: " + data1.length + " data2: " + data2.length);
		  printBytes(data1,100);
		  printBytes(data2,100);
		  //targetFile.seek(0);
		  targetFile.write(data2, 0, data2.length);
		 
		  targetFile.seek(2048);
		  targetFile.write(data1, 0, data1.length);
		  
		 
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				if(fc != null){
					fc.close();
				}
				if(fos != null){
					fos.close();
				}
				if(targetFile != null){
					targetFile.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static void printBytes(byte[] b, int length){
		for(int i = 0; i<length; i++){
			System.out.print(b[i]);
		}
		System.out.println();
	}

}
