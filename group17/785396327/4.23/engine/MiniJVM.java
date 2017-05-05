package engine;

import jvm_1.ClassFileLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by gongxun on 2017/4/24.
 */
public class MiniJVM {
    public void run(String[]classPaths , String className) throws FileNotFoundException, IOException {

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
}
