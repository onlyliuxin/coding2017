package com.github.eulerlcs.jmr.jvm.loader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassFileLoader {
	private final static Logger log = LoggerFactory.getLogger(ClassFileLoader.class);

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {
		File file = findClassFile(className);
		if (file == null) {
			return new byte[0];
		}

		byte[] ret = null;
		byte[] bytes = new byte[(int) file.length()];
		try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
			dis.readFully(bytes);
			ret = bytes;
		} catch (IOException e) {
			log.error("ClassFileLoader read error!", e);
		}

		return ret;
	}

	private File findClassFile(String className) {
		String sub = className.replace(".", File.separator) + ".class";
		for (String clzPath : clzPaths) {
			File file = new File(clzPath, sub);
			if (file.exists()) {
				return file;
			}
		}

		return null;
	}

	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	public String getClassPath() {
		if (clzPaths.size() == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (String clzPath : clzPaths) {
			sb.append(";");
			sb.append(clzPath);
		}

		String cat = sb.toString();
		return cat.length() > 0 ? cat.substring(1) : "";
	}
}
