package com.coderising.jvm.loader;

import java.util.List;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantFactory;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.util.ByteUtil;
import com.google.common.collect.Lists;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();
        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        classFile.setMagicNum(ByteUtil.byteToHexString(iterator.getNext(4)));
        classFile.setMinorVersion(ByteUtil.byteToInt(iterator.getNext(2)));
        classFile.setMajorVersion(ByteUtil.byteToInt(iterator.getNext(2)));
        ConstantPool pool = parseConstantPool(iterator);
        classFile.setConstPool(pool);
        classFile.setAccessFlag(parseAccessFlag(iterator));
        classFile.setClassIndex(parseClassIndex(pool));
        return classFile;
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iterator) {
        int constantSize = ByteUtil.byteToInt(iterator.getNext(2));
        int index = 0;
        ConstantPool constantPool = new ConstantPool();
        while (iterator.hasNext() && index++ < constantSize) {
            constantPool.addConstantInfo(ConstantFactory.build(constantPool, iterator));
        }
        return constantPool;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
        return new AccessFlag(ByteUtil.byteToInt(iterator.getNext(2)));
    }

    private ClassIndex parseClassIndex(ConstantPool pool) {
        ClassIndex classIndex = new ClassIndex();
        List<ClassInfo> classInfos = Lists.newArrayList();
        for (int i = 0; i < pool.getSize(); i++) {
            ConstantInfo constantInfo = pool.getConstantInfo(i);
            if (constantInfo.getType() == ConstantInfo.CLASS_INFO) {
                classInfos.add((ClassInfo) constantInfo);
            }
        }
        classIndex.setThisClassIndex(classInfos.get(0).getUtf8Index());
        classIndex.setSuperClassIndex(classInfos.get(1).getUtf8Index());
        return classIndex;
    }

}
