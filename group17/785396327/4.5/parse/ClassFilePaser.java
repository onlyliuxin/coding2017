package parse;

import clz.ClassFile;
import constant.ClassInfo;
import constant.ConstantInfo;
import constant.ConstantPool;
import constant.NullConstantInfo;
import iterator.ByteCodeIterator;

/**
 * Created by IBM on 2017/4/11.
 */
public class ClassFilePaser {
    private ClassFile classFile;

    public ClassFile parse(byte[] codes) {
        classFile = new ClassFile();

        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        String magic = iterator.nextU4ToHexString();
        if (!"cafebabe".equals(magic))
            return null;

        classFile.setMinorVersion(iterator.nextU2ToInt());
        classFile.setMajorVersion(iterator.nextU2ToInt());

        ConstantPool constantPool = parseConstantPool(iterator);
        classFile.setConstPool(constantPool);

        return classFile;
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iterator) {
        int poolSize = iterator.nextU2ToInt();
        System.out.println("constant pool size = " + (poolSize - 1));

        ConstantPool constantPool = new ConstantPool();

        constantPool.addConstantInfo(new NullConstantInfo());
        for (int i = 1; i < poolSize; i++) {
            int tag = iterator.nextU1ToInt();
            if (tag == ConstantInfo.CLASS_INFO) {
                ClassInfo classInfo = new ClassInfo(constantPool);
                classInfo.setUtf8Index(iterator.nextU2ToInt());
                constantPool.addConstantInfo(classInfo);
            }
        }
        return constantPool;
    }
}
