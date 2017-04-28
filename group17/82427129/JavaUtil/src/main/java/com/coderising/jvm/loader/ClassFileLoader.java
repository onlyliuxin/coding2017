package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;

public class ClassFileLoader {
	private static final int BUFFERSIZE = 1024;
	private List<String> clzPaths = new ArrayList<String>();

	/**
	 * 装载class文件
	 * 读取二进制字节流，并解析成ClassFile对象
	 * @param className
	 * @return ClassFile
	 */
	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
	}
	
	/**
	 * 从.class文件读取二进制字节流
	 * @param className
	 * @return
	 */
	public byte[] readBinaryCode(String className) {
		if (clzPaths.size()<=0){
			return null;
		}
		for (int i = 0; i < clzPaths.size(); i++) {
			String path = clzPaths.get(i) + convertName(className);
			File f = new File(path);
			if(f.exists()){
				try {
					return readFile(f);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}else{
				f = null;
				continue;
			}
		}
		System.err.println("classpath:" +getClassPath()+ "class:" + convertName(className)+" not found.");
		return null;
	}
	/**
	 * 文件读取二进制字节流
	 * @param f
	 * @return byte[]
	 * @throws FileNotFoundException
	 */
	private byte[] readFile(File f) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[BUFFERSIZE];
		try {
			int len = 0;
			while((len = fis.read(b))!=-1){
				baos.write(b,0,len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fis.close();
				baos.flush();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return baos.toByteArray();
	}

	/**
	 * 增加类加载路径，加载时按增加时的先后顺序加载
	 * @param path
	 */
	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	/**
	 * 获取类加载路径
	 * @return
	 */
	public String getClassPath() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < clzPaths.size(); i++) {
			sb.append(clzPaths.get(i)).append(";");
		}
		int len = sb.length();
		if(len!=0){
			sb.deleteCharAt(len-1);
		}
		return sb.toString();
	}

	/**
	 * convert className to FilePath style className <br/>
	 * For example:com.sun.lang to \com\sun\lang
	 * 
	 * @param className
	 * @return FilePath style className
	 */
	private String convertName(String className) {
		StringBuilder sb = new StringBuilder();
		String[] pack = className.split("\\.");
		for (int i = 0; i < pack.length; i++) {
			sb.append("\\").append(pack[i]);
		}
		sb.append(".class");
		return sb.toString();
	}

}
