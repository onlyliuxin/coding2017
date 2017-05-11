package com.github.wdn.coding2017.jvm.constant;


import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public class ConstantPool {
    public static ArrayList<ConstantInfo> constantPool = new ArrayList<ConstantInfo>();
    static{
        constantPool.add(new NullConstantInfo());
    }
    public void put(ConstantInfo info){
        constantPool.add(info);
    }
    public int getSize() {
        return constantPool.size()-1;
    }

    public ConstantInfo getConstantInfo(int i) {
        return constantPool.get(i);
    }
    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < constantPool.size(); i++) {
            stringBuffer.append("#"+i+"=>"+constantPool.get(i).getValue());
        }
        return stringBuffer.toString();
    }
}
