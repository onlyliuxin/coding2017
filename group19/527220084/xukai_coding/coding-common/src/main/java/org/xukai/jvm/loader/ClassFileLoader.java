package org.xukai.jvm.loader;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.xukai.coderising.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws IOException, ClassNotFoundException {
		for(String classPath : clzPaths){
			File classFile = findClassFile(classPath, className);
			if (classFile.exists()) {
				return FileUtil.toByteArray(classFile);
			}
		}
		throw new ClassNotFoundException();
	}
	
	
	public void addClassPath(String path) {
		if (path != null && !path.trim().equals("")) {
			if (!path.endsWith("\\")) {
				path = path + "\\";
			}
			clzPaths.add(path);
		}
	}
	
	
	
	public String getClassPath(){
		Joiner joiner = Joiner.on(";");
		return joiner.join(clzPaths);
	}

	private File findClassFile(String classPath, String className){
		className = className.replaceAll("\\.", "/");
		return new File(classPath + "\\" + className + ".class");
	}
	

	

}
