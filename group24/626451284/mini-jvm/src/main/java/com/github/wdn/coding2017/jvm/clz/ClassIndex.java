package com.github.wdn.coding2017.jvm.clz;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public class ClassIndex {
    private int thisClassIndex;
    private int superClassIndex;
    public ClassIndex(int thisClassIndex,int superClassIndex){
        this.thisClassIndex = thisClassIndex;
        this.superClassIndex = superClassIndex;
    }
    public int getThisClassIndex() {
        return thisClassIndex;
    }

    public int getSuperClassIndex() {
        return superClassIndex;
    }
}
