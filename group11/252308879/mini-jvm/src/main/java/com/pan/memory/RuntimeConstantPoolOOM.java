package com.pan.memory;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by QiPan on 2017/5/13.
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 永久代溢出 OutOfMemory :PermGen space
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用List 保持着常量池的引用，避免Full GC 回收常量池行为
        List<String> list = new ArrayList<>();
        // 10MB 的 PermSize 在Integer 范围内足够产生OOM
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }

}
