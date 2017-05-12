package com.github.eulerlcs.jmr.challenge.classloader.core;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class NetworkClassLoader extends ClassLoader {

	private String rootUrl;

	public NetworkClassLoader(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = getClassData(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name, classData, 0, classData.length);
		}
	}

	private byte[] getClassData(String className) {
		String path = classNameToPath(className);
		try {
			URL url = new URL(path);
			InputStream ins = url.openStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			while ((bytesNumRead = ins.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesNumRead);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String classNameToPath(String className) {
		return rootUrl + "/" + className.replace('.', '/') + ".class";
	}
}
