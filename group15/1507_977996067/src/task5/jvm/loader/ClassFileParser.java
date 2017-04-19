package task5.jvm.loader;

import task5.jvm.clz.AccessFlag;
import task5.jvm.clz.ClassFile;
import task5.jvm.clz.ClassIndex;
import task5.jvm.constant.*;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();
        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        System.out.println(iterator.getMagicNumber());

        classFile.setMinorVersion(iterator.next2Bytes());
        classFile.setMajorVersion(iterator.next2Bytes());

        classFile.setConstPool(parseConstantPool(iterator));
        classFile.setAccessFlag(parseAccessFlag(iterator));
        classFile.setClassIndex(parseClassIndex(iterator));

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

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
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
        return pool;
    }


}
