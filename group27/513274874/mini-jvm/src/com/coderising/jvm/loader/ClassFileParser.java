package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.*;

import java.io.UnsupportedEncodingException;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {

        ClassFile clzFile = new ClassFile();
        ByteCodeIterator iterator = new ByteCodeIterator(codes);

        String magicNumber = iterator.nextU4HexString();
        if (!"cafebabe".equals(magicNumber)) {
            return null;
        }

        clzFile.setMinorVersion(iterator.nextU2Int());
        clzFile.setMajorVersion(iterator.nextU2Int());

        clzFile.setConstPool(parseConstantPool(iterator));
        clzFile.setAccessFlag(parseAccessFlag(iterator));
        clzFile.setClassIndex(parseClassIndex(iterator));

        return clzFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        AccessFlag accessFlag = new AccessFlag(iter.nextU2Int());
        return accessFlag;
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iter) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iter.nextU2Int());
        classIndex.setSuperClassIndex(iter.nextU2Int());

        return classIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        int constantCount = iter.nextU2Int();
        ConstantPool pool = new ConstantPool();
        //因为常量池索引是#1开始，所以此处常量池的第0位设置成空
        pool.addConstantInfo(new NullConstantInfo());
        for (int i = 1; i < constantCount; i++) {
            int tag = iter.nextU1Int();
            if (tag == 7) {
                //CONSTANT_Class_info
                ClassInfo classInfo = new ClassInfo(pool);
                classInfo.setUtf8Index(iter.nextU2Int());
                pool.addConstantInfo(classInfo);
            } else if (tag == 1) {
                //CONSTANT_Utf8_info
                UTF8Info utf8Info = new UTF8Info(pool);
                int len = iter.nextU2Int();
                utf8Info.setLength(len);
                byte[] utf8Bytes = iter.getBytes(len);
                String utf8Value = null;
                try {
                    utf8Value = new String(utf8Bytes, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                utf8Info.setValue(utf8Value);

                pool.addConstantInfo(utf8Info);
            } else if (tag == 8) {
                //CONSTANT_String_info
                StringInfo stringInfo = new StringInfo(pool);
                stringInfo.setIndex(iter.nextU2Int());

                pool.addConstantInfo(stringInfo);
            } else if (tag == 9) {
                //CONSTANT_Fieldref_info
                FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                fieldRefInfo.setClassInfoIndex(iter.nextU2Int());
                fieldRefInfo.setNameAndTypeIndex(iter.nextU2Int());

                pool.addConstantInfo(fieldRefInfo);
            } else if (tag == 10) {
                //CONSTANT_Methodref_info
                MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                methodRefInfo.setClassIndex(iter.nextU2Int());
                methodRefInfo.setNameAndTypeIndex(iter.nextU2Int());

                pool.addConstantInfo(methodRefInfo);
            } else if (tag == 12) {
                //CONSTANT_NameAndType_info
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                nameAndTypeInfo.setNameIndex(iter.nextU2Int());
                nameAndTypeInfo.setDescriptorIndex(iter.nextU2Int());

                pool.addConstantInfo(nameAndTypeInfo);
            } else {
                throw new RuntimeException("constant pool hasn't this tag : " + tag);
            }

        }
        return pool;
    }


}
