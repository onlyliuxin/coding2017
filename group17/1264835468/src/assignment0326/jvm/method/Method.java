package assignment0326.jvm.method;


import assignment0326.jvm.attr.CodeAttr;
import assignment0326.jvm.clz.ClassFile;
import assignment0326.jvm.cmd.ByteCodeCommand;
import assignment0326.jvm.constant.ConstantPool;
import assignment0326.jvm.constant.UTF8Info;
import assignment0326.jvm.loader.ByteCodeIterator;

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


    public String toString() {

        ConstantPool pool = this.clzFile.getConstantPool();
        StringBuilder buffer = new StringBuilder();

        String name = ((UTF8Info) pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info) pool.getConstantInfo(this.descriptorIndex)).getValue();

        buffer.append(name).append(":").append(desc).append("\n");

        buffer.append(this.codeAttr.toString(pool));

        return buffer.toString();
    }

    public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
        int accessFlags = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descriptorIndex = iter.nextU2ToInt();
        int attrCount = iter.nextU2ToInt();
        Method method = new Method(clzFile, accessFlags, nameIndex, descriptorIndex);
        CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
        method.setCodeAttr(codeAttr);
        return method;

    }

    public ByteCodeCommand[] getCmds() {
        return this.getCodeAttr().getCmds();
    }
}
