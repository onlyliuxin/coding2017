package main.coding_170416.jvm.method;

import main.coding_170416.jvm.attr.AttributeInfo;
import main.coding_170416.jvm.attr.CodeAttr;
import main.coding_170416.jvm.clz.ClassFile;
import main.coding_170416.jvm.constant.ConstantPool;
import main.coding_170416.jvm.constant.UTF8Info;
import main.coding_170416.jvm.loader.ByteCodeIterator;

/**
 * Created by peter on 2017/4/21.
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

    public void setCodeAttr(CodeAttr codeAttr) {
        this.codeAttr = codeAttr;
    }
    public Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex){
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public String toString() {
        ConstantPool pool = this.clzFile.getConstantPool();
        StringBuilder sb = new StringBuilder();

        String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();

        sb.append(name).append(":").append(desc).append("\n");
        sb.append(this.codeAttr.toString(pool));

        return sb.toString();
    }

    public static Method parse(ClassFile clzFile, ByteCodeIterator iterator){
        int accessFlag = iterator.nextU2ToInt();
        int nameIndex = iterator.nextU2ToInt();
        int descIndex = iterator.nextU2ToInt();
        int attributeCount = iterator.nextU2ToInt();

        Method m = new Method(clzFile,accessFlag,nameIndex,descIndex);

        for(int i=1;i<attributeCount;i++){
            int attrNameIndex = iterator.nextU2ToInt();
            String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
            iterator.back(2);
            if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){
                CodeAttr codeAttr = CodeAttr.parse(clzFile,iterator);
                m.setCodeAttr(codeAttr);
            }else {
                throw new RuntimeException("only Code attribute is implemented");
            }
        }
        return  m;
    }
}
