package task8.jvm.loader;

import task8.jvm.clz.AccessFlag;
import task8.jvm.clz.ClassFile;
import task8.jvm.clz.ClassIndex;
import task8.jvm.constant.*;
import task8.jvm.field.Field;
import task8.jvm.method.Method;

import java.util.ArrayList;
import java.util.List;

public class ClassFileParser {

    private ConstantPool constantPool;

    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();
        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        System.out.println(iterator.getMagicNumber());

        classFile.setMinorVersion(iterator.next2Bytes());
        classFile.setMajorVersion(iterator.next2Bytes());

        parseConstantPool(iterator);
        classFile.setConstPool(constantPool);
        classFile.setAccessFlag(parseAccessFlag(iterator));
        classFile.setClassIndex(parseClassIndex(iterator));//task5 over

        iterator.next2Bytes(); // interface

        classFile.setFields(parseFileds(iterator));
        classFile.setMethods(parseMethods(classFile, iterator));//task6 over
        return classFile;
    }


    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        return new AccessFlag(iter.next2Bytes());
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iter) {
        ClassIndex clazIndex = new ClassIndex();
        clazIndex.setThisClassIndex(iter.next2Bytes());
        clazIndex.setSuperClassIndex(iter.next2Bytes());
        return clazIndex;
    }

    private void parseConstantPool(ByteCodeIterator iter) {
        int poolCount = iter.next2Bytes();
        ConstantPool pool = new ConstantPool(poolCount);
        for (int i = 0; i < poolCount; i++) {
            int tag = iter.nextFlag();
            if (tag == ConstantInfo.UTF8_INFO) { //utf-8
                int length = iter.next2Bytes();
                byte[] bytes = iter.getBytes(length);
                UTF8Info utf8Info = new UTF8Info(pool);
                utf8Info.setValue(new String(bytes));
                utf8Info.setLength(length);
                pool.addConstantInfo(utf8Info);
            } else if (tag == ConstantInfo.STRING_INFO) {
                StringInfo stringInfo = new StringInfo(pool);
                stringInfo.setIndex(iter.next2Bytes());
                pool.addConstantInfo(stringInfo);
            } else if (tag == ConstantInfo.CLASS_INFO) {
                ClassInfo classInfo = new ClassInfo(pool);
                classInfo.setUtf8Index(iter.next2Bytes());
                pool.addConstantInfo(classInfo);
            } else if (tag == ConstantInfo.FIELD_INFO) {
                FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                fieldRefInfo.setClassInfoIndex(iter.next2Bytes());
                fieldRefInfo.setNameAndTypeIndex(iter.next2Bytes());
                pool.addConstantInfo(fieldRefInfo);
            } else if (tag == ConstantInfo.METHOD_INFO) {
                MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                methodRefInfo.setClassInfoIndex(iter.next2Bytes());
                methodRefInfo.setNameAndTypeIndex(iter.next2Bytes());
                pool.addConstantInfo(methodRefInfo);
            } else if (tag == ConstantInfo.NAME_AND_TYPE_INFO) {
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                nameAndTypeInfo.setIndex1(iter.next2Bytes());
                nameAndTypeInfo.setIndex2(iter.next2Bytes());
                pool.addConstantInfo(nameAndTypeInfo);
            }
        }
        this.constantPool = pool;
    }

    private List<Field> parseFileds(ByteCodeIterator iter) {
        int fieldCount = iter.next2Bytes();

        List<Field> fieldList = new ArrayList<>(fieldCount);

        for (int i = 0; i < fieldCount; i++) {
            Field f = Field.parse(constantPool, iter);
            fieldList.add(f);
        }
        return fieldList;
    }

    private List<Method> parseMethods(ClassFile classFile, ByteCodeIterator iter) {
        int methodCount = iter.next2Bytes();

        List<Method> methodList = new ArrayList<>(methodCount);

        for (int i = 0; i < methodCount; i++) {
            Method m = Method.parse(classFile, iter);
            methodList.add(m);
        }
        return methodList;
    }
}
