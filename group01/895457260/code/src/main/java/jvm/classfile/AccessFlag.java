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

    public boolean isPublic(){
        return (this.flagValue & 0x0001) != 0;
    }
    public boolean isPrivate(){
        return (this.flagValue & 0x0002) != 0;
    }
    public boolean isProtected(){
        return (this.flagValue & 0x0004) != 0;
    }
    public boolean isStatic(){
        return (this.flagValue & 0x0008) != 0;
    }
    public boolean isFinal(){
        return (this.flagValue & 0x0010) != 0;
    }
}
