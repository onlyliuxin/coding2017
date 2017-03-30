package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.util.ClassFileLoaderUtil;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {
		String fileFullPath = ClassFileLoaderUtil.generateClassFileFullPath(getClassPath(), className);
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			is = new FileInputStream(fileFullPath);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = is.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return baos.toByteArray();

	}

	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	public String getClassPath() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < clzPaths.size(); i++) {
			if (i == clzPaths.size() - 1) {
				sb.append(clzPaths.get(i));
			} else {
				sb.append(clzPaths.get(i) + ";");
			}
		}
		return sb.toString();
	}

}