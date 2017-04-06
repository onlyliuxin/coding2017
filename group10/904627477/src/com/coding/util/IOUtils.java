package com.coding.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IOUtils {
	
	private final static int DEFAULT_SIZE = 1024;
	
	public static void writeFile(File file,int startPos,byte[] buff) throws IOException{
		if(buff==null){
			return;
		}
		RandomAccessFile out = new RandomAccessFile(file, "rw");
		out.seek(startPos); 
		out.write(buff);
		out.close();
	}
	
	public static void createFile(long length,String filePath){
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(filePath, "rw");
			file.setLength(length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(file!=null){
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static byte[] readFile(String filePath) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		FileInputStream in = null;
		try {
			in = new FileInputStream(filePath);
			byte[] buff = new byte[DEFAULT_SIZE];
			int len = 0;
			while((len=in.read(buff))!=-1){
				out.write(buff, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return out.toByteArray();
	}

}
