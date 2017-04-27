package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {

		String clzFileName = "//" + className.replaceAll("\\.", "//") + ".class";
		return loadClassFile(clzFileName);
	}

	public ClassFile loadClass(String className) throws UnsupportedEncodingException {

		ClassFileParser clzParser = new ClassFileParser();
		byte[] codes = readBinaryCode(className);
		ClassFile clzFile = clzParser.parse(codes);
		return clzFile;
	}

	@SuppressWarnings("resource")
	private byte[] loadClassFile(String clzFileName) {
		File classFile = getClassFile(clzFileName);
		if (null == classFile) {
			try {
				throw new ClassNotFoundException(clzFileName + " does not exist.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		RandomAccessFile raf = null;
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			raf = new RandomAccessFile(classFile, "r");
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = raf.read(b)) != -1) {
				out.write(b, 0, len);
			}
			int totalLen = (int) classFile.length();

			if (out.size() > totalLen) {
				byte[] data = out.toByteArray();
				return data;
			}

			return out.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private File getClassFile(String clzFileName) {

		for (String path : clzPaths) {
			File file = new File(path + "//" + clzFileName);
			if (file.exists()) {
				return file;
			}
		}
		return null;

	}

	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	public String getClassPath_V1() {

		return null;
	}

	public String getClassPath() {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < clzPaths.size(); i++) {

			sb.append(clzPaths.get(i));
			if (i < clzPaths.size() - 1) {
				sb.append(";");
			}

		}

		return sb.toString();
	}

}
