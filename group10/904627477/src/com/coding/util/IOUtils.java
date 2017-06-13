package com.coding.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IOUtils {
	
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

}
