package com.coding.mini_jvm.src.com.coderising.jvm.loader;

import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private ClassLoader classLoader;

	private static final String CLASS_FILE_SUFFIX = ".class";
	private static List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {
		String classPath = getClassPath();
		String[] paths = classPath.split(File.pathSeparator);
		className = className.replace('.', File.separatorChar) ;
		for (String path : paths) {
			String clzFilename = path + File.separator + className + CLASS_FILE_SUFFIX;
			byte[] data = loadClassFile(clzFilename);
			if (data != null) {
				return data;
			}
		}
		return null;
	}

	private byte[] loadClassFile(String clzFileName) {
		File file = new File(clzFileName);
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			byte[] data = new byte[bis.available()];
			bis.read(data);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public void addClassPath(String path) {
		if (this.clzPaths.contains(path)) {
			return;
		}
		clzPaths.add(path);


	}

	
	
	public String getClassPath(){
		StringBuffer sb = new StringBuffer();
		for (String path : clzPaths) {
			sb.append(path);
			sb.append(";");
		}
		String path = sb.toString();
		return path.substring(0, path.lastIndexOf(";"));
	}


	public ClassFile loadClass(String className) {
		byte[] data = readBinaryCode(className);
		ClassFileParser classFileParser = new ClassFileParser();
		return classFileParser.parse(data);
	}

//	public static void main(String[] args) {
//
//
//		System.out.println(System.getProperty("java.library.path"));
//		System.out.println(System.getProperty("java.class.path"));
//		System.out.println(System.getProperty("java.home"));
////		System.getProperty("java.class.path");
////		System.getProperty("java.class.path");
////		System.getProperty("java.class.path");
////		System.getProperty("java.class.path");
//
//	}

	private class MyClassLoader extends ClassLoader {
		@Override
		public Class<?> loadClass(String name) throws ClassNotFoundException {
			String filename = name.substring(name.lastIndexOf(".")+1) + ".class";
			try {
				InputStream is = getClass().getResourceAsStream(filename);
				if (is == null) {
					return super.loadClass(name);
				}

				byte[] b = new byte[is.available()];
				is.read(b);
				return defineClass(name, b, 0, b.length);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return super.loadClass(name);
		}
	}
}
