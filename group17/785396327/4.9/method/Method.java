package method;

import attr.CodeAttr;
import clz.ClassFile;
import cmd.ByteCodeCommand;
import iterator.ByteCodeIterator;
import util.Util;

/**
 * Created by IBM on 2017/4/12.
 */
public class Method {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private CodeAttr codeAttr;

    private ClassFile clzFile;


    public ClassFile getClzFile() {
        return clzFile;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr code) {
        this.codeAttr = code;
    }

    public Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex) {
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }


    public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
        int accessFlags = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descriptorIndex = iter.nextU2ToInt();
        int attributesCount = iter.nextU2ToInt();//有几个属性
        Method method = new Method(clzFile, accessFlags, nameIndex, descriptorIndex);
        for (int i = 0; i < attributesCount; i++) {
            CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
            method.setCodeAttr(codeAttr);
        }
        return method;

    }

    public ByteCodeCommand[] getCmds() {
        return this.getCodeAttr().getCmds();
    }
}
