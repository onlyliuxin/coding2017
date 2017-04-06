package cn.xl.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {

		
		System.out.println("解析Class文件路径："+getClassPath()+"/"+className.replace('.', '/')+".class");
		
		File file = new File(getClassPath()+"/"+className.replace('.', '/')+".class");     
		// 如果不存在直接返回     
		if (!file.exists()) {     
			return null;     
		}     
		InputStream in = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				baos.write(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return baos.toByteArray();	
	}


	public void addClassPath(String path) {
		clzPaths.add(path);
	}



	public String getClassPath(){
		StringBuffer sbf = new StringBuffer();
		if(clzPaths.size() >= 1){
			sbf.append(clzPaths.get(0));
		}
		for(int i = 1; i < clzPaths.size(); i++){
			sbf.append(";");
			sbf.append(clzPaths.get(i));
		}

		return sbf.toString();

	}



}
