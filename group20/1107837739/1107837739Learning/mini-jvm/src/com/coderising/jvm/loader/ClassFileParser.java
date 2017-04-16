package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;
import java.io.UnsupportedEncodingException;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();

        ByteCodeIterator iterator = new ByteCodeIterator(codes);

        String magicNumber = iterator.nextU4ToHexString();
        if (!"cafebabe".equals(magicNumber)) {
            return null;
        }

        classFile.setMinorVersion(iterator.nextU2ToInt());
        classFile.setMajorVersion(iterator.nextU2ToInt());

        classFile.setConstPool(parseConstantPool(iterator));
        classFile.setAccessFlag(parseAccessFlag(iterator));
        classFile.setClassIndex(parseClassInfex(iterator));

        parseInterfaces(classFile, iterator);

        parseFields(classFile, iterator);

        parseMethods(classFile, iterator);

        return classFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
        return accessFlag;
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iter) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iter.nextU2ToInt());
        classIndex.setSuperClassIndex(iter.nextU2ToInt());
        return classIndex;
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        int constantPoolCount = iter.nextU2ToInt();

        ConstantPool pool = new ConstantPool();
        pool.addConstantInfo(new NullConstantInfo());

        for (int i = 1; i < constantPoolCount; i++) {
            int type = iter.nextU1toInt();

            if (type == ConstantInfo.CLASS_INFO) {
                int utf8Index = iter.nextU2ToInt();
                ClassInfo clzInfo = new ClassInfo(pool);
                clzInfo.setUtf8Index(utf8Index);

                pool.addConstantInfo(clzInfo);
            } else if (type == ConstantInfo.UTF8_INFO) {
                int len = iter.nextU2ToInt();
                byte[] data = iter.getBytes(len);

                String value = null;
                try {
                    value = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // ignore
                }

                UTF8Info utf8Info = new UTF8Info(pool);
                utf8Info.setLength(len);
                utf8Info.setValue(value);

                pool.addConstantInfo(utf8Info);
            } else if (type == ConstantInfo.STRING_INFO) {
                StringInfo stringInfo = new StringInfo(pool);
                stringInfo.setIndex(iter.nextU2ToInt());

                pool.addConstantInfo(stringInfo);
            } else if (type == ConstantInfo.FIELD_INFO) {
                FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
                fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());

                pool.addConstantInfo(fieldRefInfo);
            } else if (type == ConstantInfo.METHOD_INFO) {
                MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
                methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());

                pool.addConstantInfo(methodRefInfo);
            } else if (type == ConstantInfo.NAME_AND_TYPE_INFO) {
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
                nameAndTypeInfo.setIndex2(iter.nextU2ToInt());

                pool.addConstantInfo(nameAndTypeInfo);
            } else {
                throw new RuntimeException("the constant pool type " + type + " hasn't been implemented yet!");
            }
        }

        return pool;
    }

    private void parseInterfaces(ClassFile clzFile, ByteCodeIterator iter) {
        int interfaceCount = iter.nextU2ToInt();

        System.out.println("Interface Count: " + interfaceCount);
    }

    private void parseFields(ClassFile clzFile, ByteCodeIterator iter) {
        int filedCount = iter.nextU2ToInt();
        System.out.println("Field Count: " + filedCount);

        for (int i = 0; i < filedCount; i++) {
            Field field = Field.parse(clzFile.getConstantPool(), iter);
            clzFile.addField(field);
        }
    }

    private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
        int methodCount = iter.nextU2ToInt();
        System.out.println("Method Count: " + methodCount);

        for (int i = 0; i < methodCount; i++) {
            Method method = Method.parse(clzFile, iter);
            clzFile.addMethod(method);
        }
    }
}
