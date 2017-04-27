package assignment0326.jvm.loader;

import assignment0326.jvm.clz.AccessFlag;
import assignment0326.jvm.clz.ClassFile;
import assignment0326.jvm.clz.ClassIndex;
import assignment0326.jvm.constant.*;


public class ClassFileParser {

    private ConstantPool constantPool;

    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();
        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        magicNumberVerify(iterator);

        classFile.setMinorVersion(iterator.next2BytesToInt());
        classFile.setMajorVersion(iterator.next2BytesToInt());

        classFile.setConstPool(parseConstantPool(iterator));

        classFile.setAccessFlag(parseAccessFlag(iterator));

        classFile.setClassIndex(parseClassIndex(iterator));
        return classFile;
    }

    private void magicNumberVerify(ByteCodeIterator iterator) {
        String magicNumber=Integer.toHexString(iterator.nextByteToInt());
        for (int i = 0; i < 3; i++) {
            magicNumber += Integer.toHexString(iterator.nextByteToInt());
        }
        if (!"cafebabe".equals(magicNumber)) {
            throw new RuntimeException("Illegal class file.");
        }
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        AccessFlag flag = new AccessFlag(iter.next2BytesToInt());
        return flag;
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iter) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iter.next2BytesToInt());
        classIndex.setSuperClassIndex(iter.next2BytesToInt());
        return classIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        constantPool = new ConstantPool();
        int size = iter.next2BytesToInt();
        constantPool.addConstantInfo(new NullConstantInfo());
        for (int i = 0; i < size - 1; i++) {
            parseConstant(iter);
        }
        return constantPool;
    }

    private void parseConstant(ByteCodeIterator iter) {
        int tag = iter.nextByteToInt();
        ConstantInfo constantInfo;
        switch (tag) {
            case ConstantInfo.UTF8_INFO:
                constantInfo = parseUTF8Info(iter);
                break;
            case ConstantInfo.CLASS_INFO:
                constantInfo = parseClassInfo(iter);
                break;
            case ConstantInfo.STRING_INFO:
                constantInfo = parseStringInfo(iter);
                break;
            case ConstantInfo.FIELD_INFO:
                constantInfo = parseFieldInfo(iter);
                break;
            case ConstantInfo.METHOD_INFO:
                constantInfo = parseMethodInfo(iter);
                break;
            case ConstantInfo.NAME_AND_TYPE_INFO:
                constantInfo = parseNameAndTypeInfo(iter);
                break;
            default:
                throw new RuntimeException("Unsupported tag");
        }
        constantPool.addConstantInfo(constantInfo);
    }

    private ConstantInfo parseUTF8Info(ByteCodeIterator iter) {
        UTF8Info utf8Info = new UTF8Info(constantPool);
        int length = iter.next2BytesToInt();
        String value = new String(iter.nextNBytes(length));
        utf8Info.setLength(length);
        utf8Info.setValue(value);
        return utf8Info;
    }

    private ConstantInfo parseClassInfo(ByteCodeIterator iter) {
        ClassInfo classInfo = new ClassInfo(constantPool);
        classInfo.setUtf8Index(iter.next2BytesToInt());
        return classInfo;
    }

    private ConstantInfo parseStringInfo(ByteCodeIterator iter) {
        StringInfo stringInfo = new StringInfo(constantPool);
        stringInfo.setIndex(iter.next2BytesToInt());
        return stringInfo;
    }

    private ConstantInfo parseFieldInfo(ByteCodeIterator iter) {
        FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
        fieldRefInfo.setClassInfoIndex(iter.next2BytesToInt());
        fieldRefInfo.setNameAndTypeIndex(iter.next2BytesToInt());
        return fieldRefInfo;
    }

    private ConstantInfo parseMethodInfo(ByteCodeIterator iter) {
        MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
        methodRefInfo.setClassInfoIndex(iter.next2BytesToInt());
        methodRefInfo.setNameAndTypeIndex(iter.next2BytesToInt());
        return methodRefInfo;
    }

    private ConstantInfo parseNameAndTypeInfo(ByteCodeIterator iter) {
        NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
        nameAndTypeInfo.setIndex1(iter.next2BytesToInt());
        nameAndTypeInfo.setIndex2(iter.next2BytesToInt());
        return nameAndTypeInfo;
    }


}
