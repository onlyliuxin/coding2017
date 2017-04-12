package jvm.loader;

import jvm.clz.AccessFlag;
import jvm.clz.ClassFile;
import jvm.clz.ClassIndex;
import jvm.constant.*;
import jvm.util.Util;

/**
 * @author jiaxun
 */
public class ClassFileParser {

    public ClassFile parse(byte[] codes) {
        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        ClassFile classFile = new ClassFile();
        classFile.setMagicNumber(parseMagicNumber(iterator));
        classFile.setMinorVersion(parseMinorVersion(iterator));
        classFile.setMajorVersion(parseMajorVersion(iterator));
        classFile.setConstantPool(parseConstantPool(iterator));
        classFile.setAccessFlag(parseAccessFlag(iterator));
        classFile.setClassIndex(parseClassInfex(iterator));
        return classFile;
    }

    private String parseMagicNumber(ByteCodeIterator iterator) {
        return Util.byteToHexString(iterator.nextFour());
    }

    private int parseMinorVersion(ByteCodeIterator iterator) {
        return Util.byteToInt(iterator.nextTwo());
    }

    private int parseMajorVersion(ByteCodeIterator iterator) {
        return Util.byteToInt(iterator.nextTwo());
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iterator) {
        int count = Util.byteToInt(iterator.nextTwo());
        ConstantPool constantPool = new ConstantPool();

        ConstantInfo nullConstantInfo = new NullConstantInfo();
        constantPool.addConstantInfo(nullConstantInfo);
        count--;

        while (count != 0) {
            int tag = Util.byteToInt(iterator.next());
            switch (tag) {
                case ConstantInfo.CLASS_INFO:
                    ClassInfo classInfo = new ClassInfo(constantPool);
                    classInfo.setNameIndex(Util.byteToInt(iterator.nextTwo()));
                    constantPool.addConstantInfo(classInfo);
                    break;
                case ConstantInfo.UTF8_INFO:
                    UTF8Info utf8Info = new UTF8Info(constantPool);
                    int length = Util.byteToInt(iterator.nextTwo());
                    utf8Info.setLength(length);
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < length; i++) {
                        int ascii = Util.byteToInt(iterator.next());
                        builder.append(Util.asciiToChar(ascii));
                    }
                    utf8Info.setBytes(builder.toString());
                    constantPool.addConstantInfo(utf8Info);
                    break;
                case ConstantInfo.STRING_INFO:
                    StringInfo stringInfo = new StringInfo(constantPool);
                    stringInfo.setIndex(Util.byteToInt(iterator.nextTwo()));
                    constantPool.addConstantInfo(stringInfo);
                    break;
                case ConstantInfo.METHOD_INFO:
                    MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
                    methodRefInfo.setClassInfoIndex(Util.byteToInt(iterator.nextTwo()));
                    methodRefInfo.setNameAndTypeIndex(Util.byteToInt(iterator.nextTwo()));
                    constantPool.addConstantInfo(methodRefInfo);
                    break;
                case ConstantInfo.FIELD_INFO:
                    FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
                    fieldRefInfo.setClassInfoIndex(Util.byteToInt(iterator.nextTwo()));
                    fieldRefInfo.setNameAndTypeIndex(Util.byteToInt(iterator.nextTwo()));
                    constantPool.addConstantInfo(fieldRefInfo);
                    break;
                case ConstantInfo.NAME_AND_TYPE_INFO:
                    NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
                    nameAndTypeInfo.setIndex1(Util.byteToInt(iterator.nextTwo()));
                    nameAndTypeInfo.setIndex2(Util.byteToInt(iterator.nextTwo()));
                    constantPool.addConstantInfo(nameAndTypeInfo);
                    break;
                case ConstantInfo.INTEGER_INFO:
                    IntegerInfo integerInfo = new IntegerInfo();
                    integerInfo.setBytes(Util.byteToInt(iterator.nextFour()));
                    constantPool.addConstantInfo(integerInfo);
                    break;
                case ConstantInfo.FLOAT_INFO:
                    FloatInfo floatInfo = new FloatInfo();
                    floatInfo.setBytes(Util.byteToInt(iterator.nextFour()));
                    break;
                default:
                    break;
            }
            count--;
        }


        return constantPool;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
        return new AccessFlag(Util.byteToInt(iterator.nextTwo()));
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iterator) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(Util.byteToInt(iterator.nextTwo()));
        classIndex.setSuperClassIndex(Util.byteToInt(iterator.nextTwo()));
        return classIndex;
    }

}
