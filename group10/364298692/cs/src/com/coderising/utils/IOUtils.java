package com.coderising.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IOUtils {
	private final static int DEFAULT_SIZE = 1024;
	
	public static void writeFile(File file, int startPos, byte[] buff) throws IOException{
		if(buff == null){
			return;
		}
		/*
		 * RandomAccessFile
		 * 1)可通过RandomAccessFile对象完成对文件的读写操作
		 * 2)在产生一个对象时，可指明要打开的文件的性质：r，只读；w，只写；rw可读写
		 * 3)可以直接跳到文件中指定的位置
		 */
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.seek(startPos);
		raf.write(buff);
		raf.close();
	}
	
	public static void createFile(long length,String filePath){
		RandomAccessFile raf = null;
		
		try {
			raf = new RandomAccessFile(filePath, "rw");
			raf.setLength(length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(raf != null){
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static byte[] readFile(String filePath){
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(filePath);
			byte[] buffer = new byte[DEFAULT_SIZE];
			int len = 0;
			while((len = fis.read(buffer)) != -1){
				bout.write(buffer, 0, len-1);
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return bout.toByteArray();
	}
}
