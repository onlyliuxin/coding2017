package jvm.classfile;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.parser.AttributeParser;
import jvm.classfile.constant.item.IReference;
import jvm.classfile.constant.item.impl.ClassInfo;
import jvm.classfile.constant.item.impl.CountConstant;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.classfile.constant.parser.ConstantParserFactory;
import jvm.classfile.field.Field;
import jvm.classfile.method.Method;
import jvm.util.ByteCodeIterator;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ClassParser {
    public static ClassFile parse(byte[] bytes) {
        ClassFile classFile = new ClassFile();
        ByteCodeIterator iterator = new ByteCodeIterator(bytes);

        iterator.skip(4); // skip magic number

        classFile.minorVersion = parseMinorVersion(iterator);
        classFile.majorVersion = parseMajorVersion(iterator);
        classFile.constantPool = parseConstantPool(iterator);
        classFile.accessFlag = parseAccessFlag(iterator);
        classFile.classIndex = parseClassIndex(iterator);
        parseInterfaces(classFile, iterator);
        parseFields(classFile, iterator);
        parseMethods(classFile, iterator);
        parseAttributes(classFile, iterator);
        linkConstantReferences(classFile);
        return classFile;
    }

    private static void parseAttributes(ClassFile classFile, ByteCodeIterator iterator) {
        int count = iterator.nextU2ToInt();
        for (int i = 0; i < count; ++i) {
            AttributeInfo attribute = AttributeParser.parse(iterator, classFile.constantPool);
            classFile.attributes.add(attribute);
        }
    }

    private static int parseMinorVersion(ByteCodeIterator iterator) {
        return iterator.nextU2ToInt();
    }

    private static int parseMajorVersion(ByteCodeIterator iterator) {
        return iterator.nextU2ToInt();
    }

    private static ConstantPool parseConstantPool(ByteCodeIterator iterator) {
        ConstantPool constantPool = new ConstantPool();

        int count = iterator.nextU2ToInt();
        constantPool.addConstantInfo(new CountConstant(count));

        for (int i = 1; i < count; ++i) {
            int tag = iterator.nextU1ToInt();
            ConstantParser parser = ConstantParserFactory.get(tag);
            constantPool.addConstantInfo(parser.parse(iterator));
        }
        return constantPool;
    }

    private static AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
        AccessFlag accessFlag = new AccessFlag();
        accessFlag.flagValue = iterator.nextU2ToInt();
        return accessFlag;
    }

    private static ClassIndex parseClassIndex(ByteCodeIterator iterator) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.thisClass = iterator.nextU2ToInt();
        classIndex.superClass = iterator.nextU2ToInt();
        return classIndex;
    }

    private static void parseInterfaces(ClassFile classFile, ByteCodeIterator iterator) {
        int count = iterator.nextU2ToInt();
        ConstantPool constantPool = classFile.constantPool;
        for (int i = 0; i < count; ++i) {
            int index = iterator.nextU2ToInt();
            ClassInfo info = (ClassInfo) constantPool.getConstantInfo(index);
            classFile.interfaces.add(info);
        }
    }

    private static void parseFields(ClassFile classFile, ByteCodeIterator iterator) {
        int count = iterator.nextU2ToInt();
        for (int i = 0; i < count; ++i) {
            classFile.fields.add(Field.parse(iterator, classFile.constantPool));
        }
    }

    private static void parseMethods(ClassFile classFile, ByteCodeIterator iterator) {
        int count = iterator.nextU2ToInt();
        for (int i = 0; i < count; ++i) {
            classFile.methods.add(Method.parse(iterator, classFile.constantPool));
        }
    }

    private static void linkConstantReferences(ClassFile classFile) {
        ConstantPool constantPool = classFile.constantPool;
        constantPool.forEach(c -> {
            if (c instanceof IReference) {
                ((IReference) c).linkReference(constantPool);
            }
        });
    }
}
