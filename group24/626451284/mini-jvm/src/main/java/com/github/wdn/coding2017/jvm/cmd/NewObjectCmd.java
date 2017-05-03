package com.github.wdn.coding2017.jvm.cmd;

import com.github.wdn.coding2017.jvm.clz.ClassFile;
import com.github.wdn.coding2017.jvm.constant.ConstantPool;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public class NewObjectCmd extends TwoOperandCmd {
    public NewObjectCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString(ConstantPool pool) {
        return null;
    }
}
