package me.lzb.jvm.constant;

/**
 * Created by LZB on 2017/4/14.
 */
public abstract class ConstantInfo {

    protected ConstantPool constantPool;


    public ConstantInfo(){

    }

    public ConstantInfo(ConstantPool pool) {
        this.constantPool = pool;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }
    public ConstantInfo getConstantInfo(int index){
        return this.constantPool.getConstantInfo(index);
    }


    public abstract int getType();
}
