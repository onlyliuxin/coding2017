package io.github.vxzh.jvm.loader;

import io.github.vxzh.jvm.clz.AccessFlag;
import io.github.vxzh.jvm.clz.ClassFile;
import io.github.vxzh.jvm.clz.ClassIndex;
import io.github.vxzh.jvm.clz.ConstantPool;
import io.github.vxzh.jvm.constant.*;


public class ClassFileParser {

    public ClassFile parse(byte[] codes) {

        ByteCodeIterator byteCodeIterator = new ByteCodeIterator(codes);
        String magicNumber = byteCodeIterator.nextU4ToHexString();
        if (!"cafebabe".equals(magicNumber))
            throw new RuntimeException("This is not a Java Class file!");

        ClassFile classFile = new ClassFile();
        int minorVersion = byteCodeIterator.nextU2ToInt();
        classFile.setMinorVersion(minorVersion);
        int majorVersion = byteCodeIterator.nextU2ToInt();
        classFile.setMajorVersion(majorVersion);

        classFile.setConstPool(this.parseConstantPool(byteCodeIterator));
        classFile.setAccessFlag(this.parseAccessFlag(byteCodeIterator));
        classFile.setClassIndex(this.parseClassInfex(byteCodeIterator));

        return classFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
        return new AccessFlag(iterator.nextU2ToInt());
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iterator) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iterator.nextU2ToInt());
        classIndex.setSuperClassIndex(iterator.nextU2ToInt());
        return classIndex;
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iterator) {

        int constantCount = iterator.nextU2ToInt();
        ConstantPool constantPool = new ConstantPool();
        constantPool.addConstantInfo(new NullConstantInfo());
        for (int i = 0; i < constantCount; i++) {
            int tag = iterator.nextU1ToInt();
            switch (tag) {
                case ConstantInfo.CONSTANT_CLASS_INFO:
                    ClassInfo classInfo = new ClassInfo(constantPool);
                    classInfo.setNameIndex(iterator.nextU2ToInt());
                    constantPool.addConstantInfo(classInfo);
                    System.out.println("classInfo " + classInfo.getNameIndex());
                    break;
                case ConstantInfo.CONSTANT_UTF8_INFO:
                    UTF8Info utf8Info = new UTF8Info(constantPool);
                    utf8Info.setLength(iterator.nextU2ToInt());
                    utf8Info.setValue(iterator.nextBytesToString(utf8Info.getLength()));
                    constantPool.addConstantInfo(utf8Info);
                    System.out.println("utf-8 " + utf8Info.getValue());
                    break;
                case ConstantInfo.CONSTANT_METHODREF_INFO:
                    MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
                    methodRefInfo.setClassInfoIndex(iterator.nextU2ToInt());
                    methodRefInfo.setNameAndTypeIndex(iterator.nextU2ToInt());
                    constantPool.addConstantInfo(methodRefInfo);
                    System.out.println("method ref " + methodRefInfo.getClassInfoIndex() + " " + methodRefInfo.getNameAndTypeIndex());
                    break;
                case ConstantInfo.CONSTANT_NAMEANDTYPE_INFO:
                    NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
                    nameAndTypeInfo.setNameIndex(iterator.nextU2ToInt());
                    nameAndTypeInfo.setDescriptorIndex(iterator.nextU2ToInt());
                    constantPool.addConstantInfo(nameAndTypeInfo);
                    System.out.println("name and type " + nameAndTypeInfo.getNameIndex() + " " + nameAndTypeInfo.getDescriptorIndex());
                    break;
                case ConstantInfo.CONSTANT_FIELDREF_INFO:
                    FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
                    fieldRefInfo.setClassInfoIndex(iterator.nextU2ToInt());
                    fieldRefInfo.setNameAndTypeIndex(iterator.nextU2ToInt());
                    constantPool.addConstantInfo(fieldRefInfo);
                    System.out.println("field ref " + fieldRefInfo.getClassInfoIndex() + " " + fieldRefInfo.getNameAndTypeIndex());
                    break;
                case ConstantInfo.CONSTANT_STRING_INFO:
                    StringInfo stringInfo = new StringInfo(constantPool);
                    stringInfo.setIndex(iterator.nextU2ToInt());
                    constantPool.addConstantInfo(stringInfo);
                    System.out.println("string " + stringInfo.getIndex());
                    break;
                default:
                    //throw new RuntimeException("the constant Pool tag "+tag+" has not been implement yet！");
            }
        }
        return constantPool;
    }


}
