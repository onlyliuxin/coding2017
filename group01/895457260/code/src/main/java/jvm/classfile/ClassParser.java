package jvm.classfile;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.CountConstant;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.classfile.constant.parser.ConstantParserFactory;
import jvm.util.ByteUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ClassParser {
    private static class StartIndex {
        int magicNumber = 0;
        int minorVersion = 4;
        int majorVersion = 6;
        int constantPoolCount = 8;
        Map<Integer, Integer> constantIndexMap = new HashMap<>();
        int accessFlags;
        int thisClass;
        int superClass;
        int interfacesCount;
        Map<Integer, Integer> interfaceIndexMap = new HashMap<>();
        int fieldsCount;
        Map<Integer, Integer> fieldIndexMap = new HashMap<>();
        int methodsCount;
        Map<Integer, Integer> methodIndexMap = new HashMap<>();
        int attributesCount;
        Map<Integer, Integer> attributeIndexMap = new HashMap<>();
    }

    private static StartIndex index = new StartIndex();

    public static ClassFile parse(byte[] bytes) {
        ClassFile classFile = new ClassFile();
        classFile.constantPool = parseConstantPool(bytes, index);
        classFile.classIndex = parseClassIndex(bytes, index);
        classFile.accessFlag = parseAccessFlag(bytes, index);
        restore(classFile);
        return classFile;
    }

    private static void restore(ClassFile classFile) {
        
    }

    private static ConstantPool parseConstantPool(byte[] bytes, StartIndex index) {
        final int COUNT_LEN = 2;

        ConstantPool constantPool = new ConstantPool();
        int currentIndex = index.constantPoolCount;

        index.constantIndexMap.put(1, currentIndex);
        int count = ByteUtils.toInt(bytes, currentIndex, COUNT_LEN);
        constantPool.constantMap.put(1, new CountConstant(count));
        currentIndex += COUNT_LEN;

        Map<Integer, ConstantParser> parserMap = new HashMap<>();
        for (int i = 2; i <= count; ++i) {
            ConstantParser parser = ConstantParserFactory.get(
                    ByteUtils.toInt(bytes, currentIndex, ConstantParser.TAG_LEN),
                    bytes, currentIndex);
            parserMap.put(i, parser);
            index.constantIndexMap.put(i, currentIndex);
            currentIndex += parser.length();
        }
        for (int i = 2; i <= count; ++i) {
            ConstantParser parser = parserMap.get(i);
            int startIndex = index.constantIndexMap.get(i);
            Constant constant = parser.parse(bytes, startIndex);
            constantPool.constantMap.put(i, constant);
        }

        index.accessFlags = currentIndex;
        index.thisClass = index.accessFlags + 2;
        index.superClass = index.thisClass + 2;
        index.interfacesCount = index.superClass + 2;
        return constantPool;
    }

    private static ClassIndex parseClassIndex(byte[] bytes, StartIndex index) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.minorVersion = ByteUtils.toInt(bytes, index.minorVersion, 2);
        classIndex.majorVersion = ByteUtils.toInt(bytes, index.majorVersion, 2);
        classIndex.thisClass = ByteUtils.toInt(bytes, index.thisClass, 2);
        classIndex.superClass = ByteUtils.toInt(bytes, index.superClass, 2);
        return classIndex;
    }

    private static AccessFlag parseAccessFlag(byte[] bytes, StartIndex index) {
        AccessFlag accessFlag = new AccessFlag();
        accessFlag.flags = ByteUtils.toInt(bytes, index.accessFlags, 2);
        return accessFlag;
    }
}
