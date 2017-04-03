package com.coderising.jvm.util;

public class ClassFileLoaderUtil {

	public static String generateClassFileFullPath(String classPath, String className) {
		String fileType = ".class";
		StringBuilder sb = new StringBuilder();
		sb.append(classPath);
		sb.append(className.replace(".", "\\"));
		sb.append(fileType);
		return sb.toString();
	}

}
