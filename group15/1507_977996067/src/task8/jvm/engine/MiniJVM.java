package task8.jvm.engine;

import task8.jvm.loader.ClassFileLoader;

import java.util.Arrays;

public class MiniJVM {

    public void run(String[] classpath, String className) throws Exception {
        ClassFileLoader loader = new ClassFileLoader();
        Arrays.stream(classpath).forEach(loader::addClassPath);
        MethodArea methodArea = MethodArea.getInstance();
        methodArea.setClassFileLoader(loader);

        ExecutorEngine executorEngine = new ExecutorEngine();
        executorEngine.execute(methodArea.getMainMethod(className.replaceAll("\\.", "/")));
    }
}
