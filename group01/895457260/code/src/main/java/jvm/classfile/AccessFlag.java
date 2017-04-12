package jvm.classfile;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class AccessFlag {
    int flagValue;

    public AccessFlag() {}

    public AccessFlag(int value) {
        this.flagValue = value;
    }

    public int getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(int flag) {
        this.flagValue = flag;
    }

    public boolean isPublicClass(){
        return (this.flagValue & 0x0001) != 0;
    }
    public boolean isFinalClass(){
        return (this.flagValue & 0x0010) != 0;
    }
}
