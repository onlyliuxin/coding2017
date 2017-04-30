package jvm.engine;

import jvm.ClassFileLoader;
import jvm.exception.ReadClassException;

import java.util.ArrayList;
import java.util.List;

public class MiniJVM {
    private boolean enablePrint = true;

    private static final List<String> defaultClassPaths = new ArrayList<>();

    public MiniJVM() {
        defaultClassPaths.add("target/classes");
    }

    public void run(String[] classPaths, String className) throws ReadClassException {
        ClassFileLoader loader = new ClassFileLoader();
        for (String classPath : defaultClassPaths) {
            loader.addClassPath(classPath);
        }
        for (String classPath : classPaths) {
            loader.addClassPath(classPath);
        }

        MethodArea methodArea = MethodArea.getInstance();
        methodArea.setClassFileLoader(loader);
        ExecutorEngine engine = new ExecutorEngine();
        className = className.replace(".", "/");

        initConsolePrinting();

        engine.execute(methodArea.getMainMethod(className));

        while (enablePrint && ConsolePrinter.printingBuf == null) {
            enablePrint = false;
        }
    }

    private void initConsolePrinting() {
        new Thread(() -> {
            while (enablePrint) {
                if (ConsolePrinter.printingBuf != null) {
                    System.out.println(ConsolePrinter.printingBuf);
                    ConsolePrinter.printingBuf = null;
                }
            }
        }).start();
    }
}
