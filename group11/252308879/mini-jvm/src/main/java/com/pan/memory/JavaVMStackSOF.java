package com.pan.memory;

/**
 * Created by QiPan on 2017/5/13.
 * VM args: -Xss128k
 * 虚拟机栈溢出 StackOverflowError
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    private void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try{
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
