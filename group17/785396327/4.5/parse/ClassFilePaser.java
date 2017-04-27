package parse;

import clz.AccessFlag;
import clz.ClassFile;
import clz.ClassIndex;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import constant.*;
import field.Field;
import iterator.ByteCodeIterator;
import method.Method;
import util.Util;

import java.util.List;

/**
 * Created by william on 2017/4/11.
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

        AccessFlag accessFlag = parseAccessFlag(iterator);
        classFile.setAccessFlag(accessFlag);

        ClassIndex classIndex = parseClassIndex(iterator);
        classFile.setClassIndex(classIndex);

        iterator.nextU2ToInt();//没有接口直接读取两个字节

        int fieldCount = iterator.nextU2ToInt();
        for (int i = 0; i < fieldCount; i++) {
            Field field = parseField(iterator, constantPool);
            classFile.addField(field);
        }

        int methodCount = iterator.nextU2ToInt();
        for (int i = 0; i < methodCount; i++) {
            Method method = parseMethod(iterator, classFile);
            classFile.addMethod(method);
        }
        return classFile;
    }

    private Method parseMethod(ByteCodeIterator iterator, ClassFile classFile) {
        return Method.parse(classFile,iterator);
    }

    private Field parseField(ByteCodeIterator iterator, ConstantPool constantPool) {
        return Field.parse(constantPool, iterator);
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iterator) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iterator.nextU2ToInt());
        classIndex.setSuperClassIndex(iterator.nextU2ToInt());
        return classIndex;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
        AccessFlag accessFlag = new AccessFlag(iterator.nextU2ToInt());
        return accessFlag;
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
            } else if (tag == ConstantInfo.METHOD_INFO) {
                MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
                methodRefInfo.setClassInfoIndex(iterator.nextU2ToInt());
                methodRefInfo.setNameAndTypeIndex(iterator.nextU2ToInt());
                constantPool.addConstantInfo(methodRefInfo);
            } else if (tag == ConstantInfo.FIELD_INFO) {
                FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
                fieldRefInfo.setClassInfoIndex(iterator.nextU2ToInt());
                fieldRefInfo.setNameAndTypeIndex(iterator.nextU2ToInt());
                constantPool.addConstantInfo(fieldRefInfo);
            } else if (tag == ConstantInfo.STRING_INFO) {
                StringInfo stringInfo = new StringInfo(constantPool);
                stringInfo.setIndex(iterator.nextU2ToInt());
                constantPool.addConstantInfo(stringInfo);
            } else if (tag == ConstantInfo.NAME_AND_TYPE_INFO) {
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
                nameAndTypeInfo.setIndex1(iterator.nextU2ToInt());
                nameAndTypeInfo.setIndex2(iterator.nextU2ToInt());
                constantPool.addConstantInfo(nameAndTypeInfo);
            } else if (tag == ConstantInfo.UTF8_INFO) {
                UTF8Info utf8Info = new UTF8Info(constantPool);
                int length = iterator.nextU2ToInt();
                utf8Info.setLength(length);
                utf8Info.setValue(new String(iterator.nextLengthBytes(length)));
                constantPool.addConstantInfo(utf8Info);
            } else {
                System.out.println("other class info ");
            }
        }
        return constantPool;
    }
}
