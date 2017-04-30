package com.github.HarryHook.coding2017.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.github.HarryHook.coding2017.jvm.clz.ClassFile;



public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
	className = className.replace('.', File.separatorChar) + ".class";
	for(String path : this.clzPaths) {
	    String clzFileName = path + File.separatorChar + className;
	    byte[] codes = loadClassFile(clzFileName);
	    if(codes != null) {
		return codes;
	    }
	}
	return null;
    }
    private byte[] loadClassFile(String clzFileName) {
	BufferedInputStream bis = null;
	try {
	    File f = new File(clzFileName);
	    bis = new BufferedInputStream(new FileInputStream(f));
	    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

	    byte[] buffer = new byte[1024];
	    int length = 0;
	    while ((length = bis.read(buffer)) != -1) {
		out.write(buffer, 0, length);
	    }
	    return out.toByteArray();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (bis != null) {
		try {
		    bis.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	return null;
    }
    public void addClassPath(String path) {
	if (this.clzPaths.contains(path)) {
	    return ;
	}
	this.clzPaths.add(path);
    }

    public String getClassPath() {

	StringBuilder buffer = new StringBuilder();

	for (int i = 0; i < clzPaths.size(); i++) {

	    buffer.append(clzPaths.get(i));
	    if (i < clzPaths.size() - 1) {
		buffer.append(";");
	    }
	}
	return buffer.toString();
    }

    public ClassFile loadClass(String className) {
	byte[] codes = this.readBinaryCode(className);
	ClassFileParser parser = new ClassFileParser();
	return parser.parse(codes);
	
    }
    public static void main(String[] args) {
	ClassFile clzFile = null;
	ClassFileLoader loader = new ClassFileLoader();
	String path1 = "F:\\Coding2017\\group02\\727171008\\bin";
	loader.addClassPath(path1);
	String className = "com.github.HarryHook.coding2017.jvm.test.EmployeeV1";
	clzFile = loader.loadClass(className);
	clzFile.print();
    }
   
}
