package com.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws IOException {
		File f = new File(clzPaths.get(0)+File.separatorChar+className+".class");
		if(!f.exists()){
			throw new FileNotFoundException("File not found");
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while(-1 != (len = in.read(buffer, 0,  buf_size))){
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			bos.close();
		}
		return null;
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		String results = "";
		for (int i = 0; i < clzPaths.size(); i++) {
			results += clzPaths.get(i);
			if(i!=clzPaths.size()-1){
				results += ";";
			}
		}
		return results;
	}

}
