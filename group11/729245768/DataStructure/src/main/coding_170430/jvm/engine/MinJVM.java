package main.coding_170430.jvm.engine;

import main.coding_170430.jvm.loader.ClassFileLoader;

/**
 * Created by peterchen on 2017/5/5.
 */
public class MinJVM {
    public void run(String[] classPaths,String className){
        ClassFileLoader loader = new ClassFileLoader();
        for(int i=0;i<classPaths.length;i++){
            loader.addClassPath(classPaths[i]);
        }
        MethodArea methodArea = MethodArea.getInstance();
        methodArea.setClassFileLoader(loader);
        ExecutorEngine engine = new ExecutorEngine();
        className = className.replace(".","/");
        engine.execute(methodArea.getMainMethod(className));
    }
}
