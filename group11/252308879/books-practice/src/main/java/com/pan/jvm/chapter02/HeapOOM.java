package com.pan.jvm.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QiPan on 2017/5/13.
 * OutOfMemoryErrorTest
 * VM Args:
 *      -Xms20m
 *      -Xmx20m
 *      -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
