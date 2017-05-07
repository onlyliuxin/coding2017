package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	 static final int BUFF_SIZE=1024;

	public byte[] readBinaryCode(String className) {
		byte[] barray = new byte[BUFF_SIZE];
		try {

			String pathname = clzPaths.get(0) + "\\" + className.replace('.', '\\')+".class";
			File file = new File(pathname);
			InputStream in = new FileInputStream(file);
			int byteread = 0;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((byteread = in.read(barray)) != -1) {
				baos.write(barray, 0, byteread);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	public String getClassPath() {
		int clzsize = clzPaths.size();
		String str = "";
		if (clzsize > 0) {
			for (int i = 0; i < clzsize; i++) {
				str += clzPaths.get(i);
				if (i < clzsize - 1) {
					str += ";";
				}
			}
		}
		return str;
	}

}
