package com.github.eulerlcs.jmr.challenge.classloader.driver;

import java.io.File;
import java.lang.reflect.Method;

import com.github.eulerlcs.jmr.challenge.classloader.core.FileSystemClassLoader;

public class ClassIdentity {

	public static void main(String[] args) {
		new ClassIdentity().testClassIdentity();
	}

	public void testClassIdentity() {
		String userDir = System.getProperty("user.dir");
		String classDataRootPath = userDir + File.separator + "data" + File.separator + "classloader";

		FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
		FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
		String className = "com.github.eulerlcs.jmr.challenge.classloader.sample.Sample";

		try {
			Class<?> class1 = fscl1.loadClass(className);
			Object obj1 = class1.newInstance();

			Class<?> class2 = fscl2.loadClass(className);
			Object obj2 = class2.newInstance();

			Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);

			setSampleMethod.invoke(obj1, obj2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
