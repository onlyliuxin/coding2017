package mini_jvm.engine;

import mini_jvm.loader.ClassFileLoader;

import java.io.FileNotFoundException;
import java.io.IOException;



public class MiniJVM {
	
	public void run(String[]classPaths , String className) throws FileNotFoundException, IOException{

		//装载类
		ClassFileLoader loader = new ClassFileLoader();
		for(int i=0;i<classPaths.length ; i++){
			loader.addClassPath(classPaths[i]);
		}

		//生成单例的方法区，并将类信息装载到方法区
		MethodArea methodArea= MethodArea.getInstance();
		methodArea.setClassFileLoader(loader);
		
		ExecutorEngine engine = new ExecutorEngine();
		
		className = className.replace(".", "/");

		//根据className执行其Main方法
		engine.execute(methodArea.getMainMethod(className));
	}
	
}
