package com.coderising.jvm.engine;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.loader.ClassFileLoader;
import com.coderising.jvm.method.Method;


public class MiniJVM {
	
	public void run(String[]classPaths , String className) throws FileNotFoundException, IOException{
		/*
		 * 一.模拟Java命令的输入：
		 * 1.加载：classpath
		 * 2.找到：classname，系统需要找到这个类的main方法，执行其字节码
		 * 
		 * 二.执行过程：
		 * 1.加载类，将类加载到方法区
		 * -工具：ClassFileLoader
		 * -目的地：方法区（MethodArea）
		 * --1.存储类的数据，如字符串常量，数字常量，字段引用，方法引用，类引用及名称和类型
		 * --2.存储方法字节码
		 * 2.获取类的main方法
		 * -从方法区找
		 * 3.执行main方法的字节码
		 * -字节码命令（Command命令）
		 * -栈帧（StackFrame）：Command对象要访问栈帧中的操作数栈，局部变量表
		 * -堆（Heap）：存放对象
		 * 
		 */
		ClassFileLoader loader = new ClassFileLoader();
		for(int i = 0; i<classPaths.length; i++){
			loader.addClassPath(classPaths[i]);
		}
		
		MethodArea methodArea = MethodArea.getInstance();//单例模式
		
		methodArea.setClassFileLoader(loader);//将类加载到方法区
		
		ExecutorEngine engine = new ExecutorEngine();
		
		className = className.replace(".", "/");
		
		engine.execute(methodArea.getMainMethod(className));
	}
	
}
