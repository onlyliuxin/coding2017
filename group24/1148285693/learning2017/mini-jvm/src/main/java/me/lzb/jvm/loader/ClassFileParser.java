package me.lzb.jvm.loader;

import me.lzb.common.utils.ByteUtils;
import me.lzb.common.utils.StringUtils;
import me.lzb.jvm.attr.*;
import me.lzb.jvm.clz.AccessFlag;
import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.clz.ClassIndex;
import me.lzb.jvm.constant.*;
import me.lzb.jvm.field.Field;
import me.lzb.jvm.method.Method;

import java.io.UnsupportedEncodingException;

/**
 * 处理字class文件字节流
 * Created by LZB on 2017/4/14.
 */
public class ClassFileParser {


    private final byte[] data;

    private int index;

    public ClassFileParser(byte[] data) {
        this.data = data;
    }


    public ClassFile parse() {
        ClassFile classFile = new ClassFile();
        parserMagicNumber(classFile);

        if (!StringUtils.equals(classFile.getMagicNumber(), ConstantInfo.MAGIC_NUMBER)) {
            throw new RuntimeException("It is not a java class file.");
        }

        parserVersion(classFile);

        parserConstantPool(classFile);

        parserAccessFlag(classFile);

        parserClassIndex(classFile);

        parserInterface(classFile);

        parserField(classFile);

        parserMethod(classFile);

        return classFile;
    }


    private byte[] nextBytes(int nextLength) {
        byte[] target = new byte[nextLength];
        System.arraycopy(data, index, target, 0, nextLength);
        index = index + nextLength;
        return target;
    }

    private int nextBytesToInt(int nextLength) {
        return ByteUtils.byteToInt(nextBytes(nextLength));
    }

    private String nextBytesToString(int nextLength) {
        return ByteUtils.byteToHexString(nextBytes(nextLength));
    }


    private void parserMagicNumber(ClassFile classFile) {
        this.index = 0;
        classFile.setMagicNumber(nextBytesToString(4));
    }

    private void parserVersion(ClassFile classFile) {
        classFile.setMinorVersion(nextBytesToInt(2));
        classFile.setMajorVersion(nextBytesToInt(2));
    }

    private void parserConstantPool(ClassFile classFile) {
        int count = nextBytesToInt(2);

        ConstantPool pool = new ConstantPool();
        pool.addConstantInfo(new NullConstantInfo());

        for (int i = 1; i < count; i++) {

            int tag = nextBytesToInt(1);

            if (tag == ConstantInfo.Class_info) {
                ClassInfo classInfo = new ClassInfo(pool);
                classInfo.setUtf8Index(nextBytesToInt(2));

                pool.addConstantInfo(classInfo);
                continue;
            }

            if (tag == ConstantInfo.Fieldref_info) {
                FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                fieldRefInfo.setClassInfoIndex(nextBytesToInt(2));
                fieldRefInfo.setNameAndTypeIndex(nextBytesToInt(2));

                pool.addConstantInfo(fieldRefInfo);
                continue;
            }

            if (tag == ConstantInfo.Methodref_info) {
                MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                methodRefInfo.setClassInfoIndex(nextBytesToInt(2));
                methodRefInfo.setNameAndTypeIndex(nextBytesToInt(2));

                pool.addConstantInfo(methodRefInfo);
                continue;
            }

            if (tag == ConstantInfo.NameAndType_info) {
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                nameAndTypeInfo.setIndex1(nextBytesToInt(2));
                nameAndTypeInfo.setIndex2(nextBytesToInt(2));

                pool.addConstantInfo(nameAndTypeInfo);
                continue;
            }

            if (tag == ConstantInfo.String_info) {
                StringInfo stringInfo = new StringInfo(pool);
                stringInfo.setIndex(nextBytesToInt(2));

                pool.addConstantInfo(stringInfo);
                continue;
            }

            if (tag == ConstantInfo.Utf8_info) {
                UTF8Info utf8Info = new UTF8Info(pool);
                int len = nextBytesToInt(2);
                byte[] data = nextBytes(len);
                String value = null;
                try {
                    value = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                utf8Info.setLength(len);
                utf8Info.setValue(value);

                pool.addConstantInfo(utf8Info);
                continue;
            }


            throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet." + i);
        }


        classFile.setConstantPool(pool);
    }


    private void parserAccessFlag(ClassFile classFile) {
        AccessFlag flag = new AccessFlag(nextBytesToInt(2));
        classFile.setAccessFlag(flag);
    }


    private void parserClassIndex(ClassFile classFile) {
        ClassIndex clzIndex = new ClassIndex();

        int thisClassIndex = nextBytesToInt(2);
        int superClassIndex = nextBytesToInt(2);
        clzIndex.setThisClassIndex(thisClassIndex);
        clzIndex.setSuperClassIndex(superClassIndex);

        classFile.setClzIndex(clzIndex);
    }

    private void parserInterface(ClassFile classFile) {
        int count = nextBytesToInt(2);
        //TODO 实现interface
    }


    private void parserField(ClassFile classFile) {
        int count = nextBytesToInt(2);
        for (int i = 1; i <= count; i++) {
            int accessFlags = nextBytesToInt(2);
            int nameIndex = nextBytesToInt(2);
            int descriptorIndex = nextBytesToInt(2);
            int attributesCount = nextBytesToInt(2);

            if (attributesCount > 0) {
                throw new RuntimeException("Field Attribute has not been implement");
            }

            Field field = new Field(accessFlags, nameIndex, descriptorIndex, classFile.getConstantPool());
            classFile.addField(field);
        }
    }

    private void parserMethod(ClassFile classFile) {
        int count = nextBytesToInt(2);
        for (int i = 1; i <= count; i++) {
            int accessFlags = nextBytesToInt(2);
            int nameIndex = nextBytesToInt(2);
            int descriptorIndex = nextBytesToInt(2);
            int attributesCount = nextBytesToInt(2);


            Method method = new Method(accessFlags, nameIndex, descriptorIndex);


            for (int j = 1; j <= attributesCount; j++) {

                int attributeNameIndex = nextBytesToInt(2);

                String attributeName = classFile.getConstantPool().getUTF8String(attributeNameIndex);

                if (StringUtils.equalsIgnoreCase(attributeName, AttributeInfo.CODE)) {
                    parserCodeAttr(attributeNameIndex, method, classFile);
                    continue;
                }


                throw new RuntimeException("only CODE attribute is implemented.");
            }

            classFile.addMethod(method);

        }
    }


    private void parserCodeAttr(int attributeNameIndex, Method method, ClassFile classFile) {
        int attributeLength = nextBytesToInt(4);
        int maxStack = nextBytesToInt(2);
        int maxLocals = nextBytesToInt(2);
        int codeLength = nextBytesToInt(4);

        String code = nextBytesToString(codeLength);
        CodeAttr codeAttr = new CodeAttr(attributeNameIndex, attributeLength, maxStack, maxLocals, codeLength, code);

        int exceptionTableLength = nextBytesToInt(2);
        if (exceptionTableLength > 0) {
            String exceptionTable = nextBytesToString(exceptionTableLength);
            //TODO 异常
        }


        int subAttrCount = nextBytesToInt(2);
        for (int k = 1; k <= subAttrCount; k++) {
            int subAttrIndex = nextBytesToInt(2);

            String subAttrName = classFile.getConstantPool().getUTF8String(subAttrIndex);

            if (StringUtils.equalsIgnoreCase(subAttrName, AttributeInfo.LINE_NUM_TABLE)) {

                parserLineNumberTable(codeAttr, subAttrIndex);
                continue;
            }

            if (StringUtils.equalsIgnoreCase(subAttrName, AttributeInfo.LOCAL_VAR_TABLE)) {
                parserLocalVariableTable(codeAttr, subAttrIndex);
                continue;
            }

            if (StringUtils.equalsIgnoreCase(subAttrName, AttributeInfo.STACK_MAP_TABLE)) {
                parserStackMapTable(codeAttr, subAttrIndex);
                continue;
            }


            throw new RuntimeException("Need code to process" + subAttrName);
        }


        method.setCodeAttr(codeAttr);
    }


    private void parserLineNumberTable(CodeAttr codeAttr, int attributeNameIndex) {
//        int attributeNameIndex = nextBytesToInt(2);
        int attributeLength = nextBytesToInt(4);

        int lineNumberTableLength = nextBytesToInt(2);

        LineNumberTable table = new LineNumberTable(attributeNameIndex, attributeLength);

        for (int l = 1; l <= lineNumberTableLength; l++) {
            int startPc = nextBytesToInt(2);
            int lineNumber = nextBytesToInt(2);
            LineNumberItem item = new LineNumberItem(startPc, lineNumber);
            table.addLineNumberItem(item);
        }

        codeAttr.setLineNumTable(table);
    }


    private void parserLocalVariableTable(CodeAttr codeAttr, int attributeNameIndex) {
//        int attributeNameIndex = nextBytesToInt(2);
        int attributeLength = nextBytesToInt(4);

        int localVariableTableLength = nextBytesToInt(2);

        LocalVariableTable table = new LocalVariableTable(attributeNameIndex, attributeLength);

        for (int l = 1; l <= localVariableTableLength; l++) {
            int startPc = nextBytesToInt(2);
            int lineNumber = nextBytesToInt(2);
            int nameIndex = nextBytesToInt(2);
            int descriptorIndex = nextBytesToInt(2);
            int index = nextBytesToInt(2);
            LocalVariableItem item = new LocalVariableItem(startPc, lineNumber, nameIndex, descriptorIndex, index);
            table.addLocalVariableItem(item);
        }

        codeAttr.setLocalVarTable(table);
    }

    private void parserStackMapTable(CodeAttr codeAttr, int attributeNameIndex) {
//        int attributeNameIndex = nextBytesToInt(2);
        int attributeLength = nextBytesToInt(4);
        String code = nextBytesToString(attributeLength);
        StackMapTable table = new StackMapTable(attributeNameIndex, attributeLength, code);
        //后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
        codeAttr.setStackMapTable(table);
    }

}
