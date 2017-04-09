package jvm.classfile;

import jvm.classfile.constant.item.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ConstantPool {
    Map<Integer, Constant> constantMap = new HashMap<>();
}
