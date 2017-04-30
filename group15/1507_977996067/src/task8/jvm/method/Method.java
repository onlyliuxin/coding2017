package task8.jvm.method;

import task8.jvm.attr.AttributeInfo;
import task8.jvm.attr.CodeAttr;
import task8.jvm.clz.ClassFile;
import task8.jvm.cmd.ByteCodeCommand;
import task8.jvm.constant.ConstantPool;
import task8.jvm.constant.UTF8Info;
import task8.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

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
        int accessFlag = iter.next2Bytes();
        int nameIndex = iter.next2Bytes();
        int descIndex = iter.next2Bytes();
        int attribCount = iter.next2Bytes();


        Method m = new Method(clzFile, accessFlag, nameIndex, descIndex);

        for (int j = 1; j <= attribCount; j++) {

            int attrNameIndex = iter.next2Bytes();
            String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
            iter.back(2);

            if (AttributeInfo.CODE.equalsIgnoreCase(attrName)) {
                CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
                m.setCodeAttr(codeAttr);
            } else {
                throw new RuntimeException("only CODE attribute is implemented , please implement the " + attrName);
            }
        }
        return m;
    }

    public List<String> getParameterList() {
        String paramType = ((UTF8Info) getClzFile().getConstantPool().getConstantInfo(getDescriptorIndex())).getValue();
        String param = paramType.substring(paramType.indexOf("("), paramType.lastIndexOf(")"));
        List<String> paramList = new ArrayList<>();
        while (!"".equalsIgnoreCase(param)) {
            String params = param.substring(0, param.indexOf("L"));
            paramList.add(params);
        }
        return paramList;
    }

    public ByteCodeCommand[] getCmds() {
        return new ByteCodeCommand[0];
    }
}
