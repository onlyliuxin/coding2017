package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.coderising.jvm.clz.ClassFile;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {
		String path = "";
		File file = null;
		Iterator<String> iterator = clzPaths.iterator();
		className = className.replace(".", File.separator);

		while (iterator.hasNext()) {
			path = iterator.next().replace("\\", "/");
			path += File.separator + className + ".class";
			file = new File(path);
			if (file.exists())
				break;
		}
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] tmp = new byte[1024];
		byte[] answer = new byte[(int) file.length()];
		int len = 0;
		int index = 0;
		try {
			while ((len = inputStream.read(tmp)) != -1) {
				System.arraycopy(tmp, 0, answer, index, len);
				index += len;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return answer;

	}

	public void addClassPath(String path) {
		if (clzPaths.contains(path)) {
			return;
		}
		clzPaths.add(path);
	}

	public String getClassPath() {
		String answer = "";
		Iterator<String> iterator = clzPaths.iterator();
		while (iterator.hasNext()) {
			answer += iterator.next() + ";";
		}
		int len = answer.length();
		if (len > 0)
			answer = answer.substring(0, len - 1);
		return answer;
	}

	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
	
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

}
