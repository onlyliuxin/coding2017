package jvm.engine;

import jvm.ClassFileLoader;
import jvm.exception.ReadClassException;

public class MiniJVM {
    public void run(String[] classPaths, String className) throws ReadClassException {
        ClassFileLoader loader = new ClassFileLoader();
        for (String classPath : classPaths) {
            loader.addClassPath(classPath);
        }

        MethodArea methodArea = MethodArea.getInstance();
        methodArea.setClassFileLoader(loader);
        ExecutorEngine engine = new ExecutorEngine();
        className = className.replace(".", "/");
        engine.execute(methodArea.getMainMethod(className));
    }
}
