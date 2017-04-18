package me.lzb.jvm.method;

import me.lzb.jvm.attr.CodeAttr;

/**
 * Created by LZB on 2017/4/15.
 */
public class Method {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private CodeAttr codeAttr;

//    private ClassFile clzFile;


    public Method(/*ClassFile clzFile,*/ int accessFlag, int nameIndex, int descriptorIndex) {
//        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }


    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr codeAttr) {
        this.codeAttr = codeAttr;
    }

//    public ClassFile getClzFile() {
//        return clzFile;
//    }
//
//    public void setClzFile(ClassFile clzFile) {
//        this.clzFile = clzFile;
//    }
}
