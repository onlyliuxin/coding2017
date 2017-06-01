package me.lzb.jvm.engine;

import me.lzb.jvm.loader.ClassFileLoader;

import java.io.IOException;

/**
 * @author LZB
 */
public class MiniJVM {

    public void run(String[] classPaths, String className) throws IOException {

        ClassFileLoader loader = new ClassFileLoader();
        for (int i = 0; i < classPaths.length; i++) {
            loader.addClassPath(classPaths[i]);
        }

        //方法区，存放class文件，常量池
        MethodArea methodArea = MethodArea.getInstance();

        methodArea.setClassFileLoader(loader);

        ExecutorEngine engine = new ExecutorEngine();
        //获取构造函数名
        className = className.replace(".", "/");

        engine.execute(methodArea.getMainMethod(className));
    }

}
