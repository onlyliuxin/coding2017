package com.github.orajavac.coding2017.jvm.loader;

import com.github.orajavac.coding2017.jvm.clz.ClassFile;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path1 = "bin";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "com.github.orajavac.coding2017.jvm.test.EmployeeV1";
		ClassFile clzFile = loader.loadClass(className);
		
		System.out.println("getMinorVersion="+clzFile.getMinorVersion());
		System.out.println("getMajorVersion="+clzFile.getMajorVersion());
	}

}
