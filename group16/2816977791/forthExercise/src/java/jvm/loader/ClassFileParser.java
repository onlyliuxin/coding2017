package jvm.loader;

import jvm.clz.AccessFlag;
import jvm.clz.ClassFile;
import jvm.clz.ClassIndex;
import jvm.constant.*;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();
        ByteCodeIterator byteCodeIterator = new ByteCodeIterator(codes);
        //跳过魔数的验证
        byteCodeIterator.getBytes(4);
        //获取此版本和主版本号
        classFile.setMinorVersion(byteCodeIterator.nextU2ToInt());
        classFile.setMajorVersion(byteCodeIterator.nextU2ToInt());
        //设置常量池
        classFile.setConstPool(parseConstantPool(byteCodeIterator));
        //设置Access
        classFile.setAccessFlag(parseAccessFlag(byteCodeIterator));
        //设置ClassIndex
        classFile.setClassIndex(parseClassInfex(byteCodeIterator));
        return classFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        return new AccessFlag(iter.nextU2ToInt());
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iter) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iter.nextU2ToInt());
        classIndex.setSuperClassIndex(iter.nextU2ToInt());
        return classIndex;
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        int constantNumber = iter.nextU2ToInt();
        ConstantPool constantPool = new ConstantPool();
        constantPool.addConstantInfo(new NullConstantInfo());
        int length;
        for (int i = 1; i < constantNumber; i++) {
            switch (iter.nextU1toInt()) {
                case ConstantInfo.UTF8_INFO:
                    UTF8Info utf8Info = new UTF8Info(constantPool);
                    length = iter.nextU2ToInt();
                    utf8Info.setLength(length);
                    utf8Info.setValue(iter.nextUxToString(length));
                    constantPool.addConstantInfo(utf8Info);
                    break;
                case ConstantInfo.CLASS_INFO:
                    ClassInfo classInfo = new ClassInfo(constantPool);
                    classInfo.setUtf8Index(iter.nextU2ToInt());
                    constantPool.addConstantInfo(classInfo);
                    break;
                case ConstantInfo.STRING_INFO:
                    StringInfo stringInfo = new StringInfo(constantPool);
                    stringInfo.setIndex(iter.nextU2ToInt());
                    constantPool.addConstantInfo(stringInfo);
                    break;
                case ConstantInfo.FIELD_INFO:
                    FieldRefInfo fieldInfo = new FieldRefInfo(constantPool);
                    fieldInfo.setClassInfoIndex(iter.nextU2ToInt());
                    fieldInfo.setNameAndTypeIndex(iter.nextU2ToInt());
                    constantPool.addConstantInfo(fieldInfo);
                    break;
                case ConstantInfo.METHOD_INFO:
                    MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
                    methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
                    methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
                    constantPool.addConstantInfo(methodRefInfo);
                    break;
                case ConstantInfo.NAME_AND_TYPE_INFO:
                    NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
                    nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
                    nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
                    constantPool.addConstantInfo(nameAndTypeInfo);
                    break;
            }
        }
        return constantPool;
    }


}
