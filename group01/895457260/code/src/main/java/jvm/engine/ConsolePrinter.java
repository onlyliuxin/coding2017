package jvm.engine;

/**
 * Created by Haochen on 2017/4/30.
 * TODO:
 */
public class ConsolePrinter {
    volatile static Object printingBuf = null;

    public static void println(Object o) {
        while (true) {
            if (printingBuf == null) {
                printingBuf = o;
                break;
            }
        }
    }
}
