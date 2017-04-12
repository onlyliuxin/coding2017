package com.github.chaoswang.learning.java.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.chaoswang.learning.java.jvm.clz.ClassFile;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {

		// String path = className.replaceAll("\\.", "/");
		// 更好的方法是下面这样
		className = className.replace('.', File.separatorChar) + ".class";
		for (String path : this.clzPaths) {

			String clzFileName = path + File.separatorChar + className;
			byte[] codes = loadClassFile(clzFileName);
			if (codes != null) {
				return codes;
			}
		}

		return null;
	}

	private byte[] loadClassFile(String clzFileName) {

		File f = new File(clzFileName);

		try {

			return IOUtils.toByteArray(new FileInputStream(f));

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getClassPath() {
		return StringUtils.join(this.clzPaths, ";");
	}

	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);

	}

	public void addClassPath(String path) {
		// 已经加过的不要再加
		if (clzPaths.contains(path)) {
			return;
		}
		clzPaths.add(path);
	}

	// ------------------------------backup------------------------
	public String getClassPath_V1() {
		StringBuffer sb = new StringBuffer();
		for (String clzPath : clzPaths) {
			sb.append(clzPath).append(";");
		}
		String classPath = sb.toString();
		return classPath.substring(0, classPath.length() - 1);
	}

	// 读取一个.class文件的前4个字节， 转换成十六进制字符，检查是不是：CAFEBABE
	private byte[] readClassFile_V1(String filePath) {
		BufferedInputStream bis = null;
		ByteArrayOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new ByteArrayOutputStream();
			byte[] bbuf = new byte[1024];// 缓冲的意思是，告诉输入流，请你把它填满，可能读1024，也可能不足1024
			int index = 0;

			while ((index = bis.read(bbuf)) != -1) {
				// 由于read方法在读到流的末尾前，此方法一直阻塞。所以循环体内实际只会打印一次
				System.out.println(bbuf.length);
				bos.write(bbuf, 0, index);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
