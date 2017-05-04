package me.lzb.jvm.clz;

/**
 * Created by LZB on 2017/4/14.
 */
public class ClassIndex {
    private int thisClassIndex;
    private int superClassIndex;

    public int getThisClassIndex() {
        return thisClassIndex;
    }

    public void setThisClassIndex(int thisClassIndex) {
        this.thisClassIndex = thisClassIndex;
    }

    public int getSuperClassIndex() {
        return superClassIndex;
    }

    public void setSuperClassIndex(int superClassIndex) {
        this.superClassIndex = superClassIndex;
    }
}
