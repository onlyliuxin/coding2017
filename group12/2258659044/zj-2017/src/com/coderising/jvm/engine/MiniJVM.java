package com.coderising.jvm.engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.coderising.jvm.exception.NotAClassFileException;
import com.coderising.jvm.loader.ClassFileLoader;

public class MiniJVM {
	
	public void run(String[]classPaths , String className) throws FileNotFoundException, IOException, NotAClassFileException{
		
		ClassFileLoader loader = new ClassFileLoader();
		for(int i=0;i<classPaths.length ; i++){
			loader.addClassPath(classPaths[i]);
		}
		
		MethodArea methodArea= MethodArea.getInstance();
		
		methodArea.setClassFileLoader(loader);
		
		ExecutorEngine engine = new ExecutorEngine();
		
		className = className.replace(".", "/");
		
		engine.execute(methodArea.getMainMethod(className));
	}
	
	public static void main(String[] args) {
		
		try {
			MiniJVM jvm = new MiniJVM();
			String[] classPaths = {"E:\\githubRepository\\coding2017\\group12\\2258659044\\zj-2017\\bin"};
			String clzName = "test.com.coderising.jvm.EmployeeV1";
			
			jvm.run(classPaths, clzName);
		} catch (IOException | NotAClassFileException e) {
			e.printStackTrace();
		}
	}
}
