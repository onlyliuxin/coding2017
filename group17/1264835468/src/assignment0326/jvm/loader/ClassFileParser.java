package assignment0326.jvm.loader;

import assignment0326.jvm.clz.AccessFlag;
import assignment0326.jvm.clz.ClassFile;
import assignment0326.jvm.clz.ClassIndex;
import assignment0326.jvm.constant.*;
import assignment0326.jvm.field.Field;
import assignment0326.jvm.method.Method;


public class ClassFileParser {

    private ConstantPool constantPool;

    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();
        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        magicNumberVerify(iterator);

        classFile.setMinorVersion(iterator.nextU2ToInt());
        classFile.setMajorVersion(iterator.nextU2ToInt());

        classFile.setConstPool(parseConstantPool(iterator));

        classFile.setAccessFlag(parseAccessFlag(iterator));

        classFile.setClassIndex(parseClassIndex(iterator));
        parseInterfaces(iterator);

        parseFileds(classFile, iterator);

        //parseMethods(classFile, iterator);
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
        AccessFlag flag = new AccessFlag(iter.nextU2ToInt());
        return flag;
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iter) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iter.nextU2ToInt());
        classIndex.setSuperClassIndex(iter.nextU2ToInt());
        return classIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        constantPool = new ConstantPool();
        int size = iter.nextU2ToInt();
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
        int length = iter.nextU2ToInt();
        String value = new String(iter.nextNBytes(length));
        utf8Info.setLength(length);
        utf8Info.setValue(value);
        return utf8Info;
    }

    private ConstantInfo parseClassInfo(ByteCodeIterator iter) {
        ClassInfo classInfo = new ClassInfo(constantPool);
        classInfo.setUtf8Index(iter.nextU2ToInt());
        return classInfo;
    }

    private ConstantInfo parseStringInfo(ByteCodeIterator iter) {
        StringInfo stringInfo = new StringInfo(constantPool);
        stringInfo.setIndex(iter.nextU2ToInt());
        return stringInfo;
    }

    private ConstantInfo parseFieldInfo(ByteCodeIterator iter) {
        FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
        fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
        fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
        return fieldRefInfo;
    }

    private ConstantInfo parseMethodInfo(ByteCodeIterator iter) {
        MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
        methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
        methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
        return methodRefInfo;
    }

    private ConstantInfo parseNameAndTypeInfo(ByteCodeIterator iter) {
        NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
        nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
        nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
        return nameAndTypeInfo;
    }

    private void parseInterfaces(ByteCodeIterator iter) {
        int interfaceCount = iter.nextU2ToInt();

        System.out.println("interfaceCount:" + interfaceCount);

        // TODO : 如果实现了interface, 这里需要解析
    }

    private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
        int fieldCount = iter.nextU2ToInt();

        for (int i = 1; i <= fieldCount; i++) {
            Field f = Field.parse(clzFile.getConstantPool(), iter);
            clzFile.addField(f);
        }

    }

    private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {

        int methodCount = iter.nextU2ToInt();

        for (int i = 1; i <= methodCount; i++) {
            Method m = Method.parse(clzFile, iter);
            clzFile.addMethod(m);
        }

    }

}
