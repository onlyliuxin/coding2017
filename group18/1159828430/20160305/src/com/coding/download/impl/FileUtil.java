package com.coding.download.impl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Scholar
 * @Time：2017年3月11日 上午12:32:15
 * @version 1.0
 */
public class FileUtil {
	
	private File file;
	
	private RandomAccessFile itemFile;
	
	public FileUtil(String fileLocation) {
		if (fileLocation != null && !"".equals(fileLocation)) {
			file = new File(fileLocation);
		}
		
		try {
			itemFile = new RandomAccessFile(file, "rw");
			
		} catch (IOException e) {
			System.out.println("创建随机读写实例失败");
		}
		
	}
	
	public void writeFile(byte[] data, int startPos, int length) {
		try {
			itemFile = new RandomAccessFile(file, "rw");
			itemFile.seek(startPos);
			itemFile.write(data, 0, length); 
		} catch (IOException e) {
			System.out.println("文件写入失败");
		}
	}
	
	public void close() {
        if (itemFile != null) {
            try {
				itemFile.close();
			} catch (IOException e) {
				System.out.println("文件流关闭失败");
			}
        }
    }
	
	public void setSize(long size) {
		try {
			itemFile.setLength(size);
		} catch (IOException e) {
			System.out.println("创建指定文件失败");
		}
	}
}
