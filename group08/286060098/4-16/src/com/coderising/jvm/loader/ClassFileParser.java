package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ConstantFactory;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFileParser {

    private static final String MAGIC_NUMBER = "cafebabe";

    public ClassFile parse(byte[] codes) {

        ClassFile clzFile = new ClassFile();

        ByteCodeIterator iterator = new ByteCodeIterator(codes);

        String magicNumber = iterator.nextU4ToHexString();
        if (!MAGIC_NUMBER.equals(magicNumber)) {
            return null;
        }

        clzFile.setMinorVersion(iterator.nextU2ToInt());
        clzFile.setMajorVersion(iterator.nextU2ToInt());

        ConstantPool pool = parseConstantPool(iterator);
        clzFile.setConstPool(pool);

        AccessFlag flag = parseAccessFlag(iterator);
        clzFile.setAccessFlag(flag);

        ClassIndex clzIndex = parseClassInfex(iterator);
        clzFile.setClassIndex(clzIndex);

        clzFile.setIntefaceCount(parseInterfaces(iterator));

        parseFields(clzFile, iterator);

        parseMethods(clzFile, iterator);

        return clzFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        return new AccessFlag(iter.nextU2ToInt());
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iter) {

        int thisClassIndex = iter.nextU2ToInt();
        int superClassIndex = iter.nextU2ToInt();

        ClassIndex clzIndex = new ClassIndex();

        clzIndex.setThisClassIndex(thisClassIndex);
        clzIndex.setSuperClassIndex(superClassIndex);

        return clzIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iterator) {

        int constPoolCount = iterator.nextU2ToInt();
        ConstantPool constantPool = new ConstantPool();
        for (int i = 1; i <= constPoolCount - 1; i++) {
            constantPool.addConstantInfo(ConstantFactory.build(constantPool, iterator));
        }
        return constantPool;
    }

    private int parseInterfaces(ByteCodeIterator iterator) {
        return iterator.nextU2ToInt();
    }

    private void parseFields(ClassFile clzFile, ByteCodeIterator iterator) {
        int fieldCount = iterator.nextU2ToInt();
        for (int i = 1; i <= fieldCount; i++) {
            Field f = Field.parse(clzFile.getConstantPool(), iterator);
            clzFile.addField(f);
        }

    }

    private void parseMethods(ClassFile clzFile, ByteCodeIterator iterator) {
        int methodCount = iterator.nextU2ToInt();
        for (int i = 1; i <= methodCount; i++) {
            Method m = Method.parse(clzFile, iterator);
            clzFile.addMethod(m);
        }

    }

}
