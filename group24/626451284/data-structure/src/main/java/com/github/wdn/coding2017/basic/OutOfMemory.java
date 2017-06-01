package com.github.wdn.coding2017.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdn on 2017/5/30 0030.
 */
public class OutOfMemory {
    private List<Object> list = new ArrayList<>();
    private void stackFunction(){
        stackFunction();
    }
    private void outOfMemory(){
        while (true) {
            list.add(new int[1024 * 1000]);
        }
    }
    private void outOfMemoryPermGenSpace(){
        // java8 设置-server -XX:MetaspaceSize=1M -XX:MaxMetaspaceSize=2m
        // java8 之前设置-server -XX:PermSize=64M -XX:MaxPermSize=128M

    }
    public static void main(String[] args) {
        OutOfMemory out = new OutOfMemory();
        // StackOverflowError
        //out.stackFunction();
        // OutOfMemoryError: Java heap space
        //out.outOfMemory();
        // OutOfMemory :PermGen space
        //out.outOfMemoryPermGenSpace();
    }
}
