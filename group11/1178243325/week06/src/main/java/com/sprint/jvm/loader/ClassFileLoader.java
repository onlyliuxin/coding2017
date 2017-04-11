package com.sprint.jvm.loader;

import com.sprint.jvm.clz.ClassFile;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class ClassFileLoader {
	List<String> clzPaths = new ArrayList<>();
	
	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
	}

	public void addClassPath(String clzPath) {
		if (this.clzPaths.contains(clzPath)) {
			return;
		}
		this.clzPaths.add(clzPath);
	}

	private byte[] readBinaryCode(String className) {
		className = className.replace('.', File.separatorChar) + ".class";
		for (String path : clzPaths) {
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
}
