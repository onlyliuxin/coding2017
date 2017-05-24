package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import sun.invoke.util.BytecodeDescriptor;

import javax.lang.model.element.Name;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {

        ClassFile clzFile = new ClassFile();
        ByteCodeIterator iter = new ByteCodeIterator(codes);

        String magicNumber = iter.nextU4ToHexString();
        if (!"cafebabe".equals(magicNumber)) {
            return null;
        }

        clzFile.setMinorVersion(iter.nextU2ToInt());
        clzFile.setMajorVersion(iter.nextU2ToInt());
        clzFile.setConstPool(parseConstantPool(iter));
        clzFile.setAccessFlag(parseAccessFlag(iter));
        clzFile.setClassIndex(parseClassInfex(iter));
        return clzFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

        return new AccessFlag(iter.nextU2ToInt());
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iter) {

        ClassIndex clzIndex = new ClassIndex();
        clzIndex.setThisClassIndex(iter.nextU2ToInt());
        clzIndex.setSuperClassIndex(iter.nextU2ToInt());

        return clzIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        int constantNum = iter.nextU2ToInt();
        ConstantPool pool = new ConstantPool();
        pool.addConstantInfo(new NullConstantInfo());

        for (int i = 0; i < constantNum - 1; i++) {
            int tag = iter.nextU1ToInt();
            if (tag == 1) {
                //utf-8_info
                int len = iter.nextU2ToInt();
                byte[] buffer = new byte[len];
                buffer = iter.getCodes(len);
                String s = null;
                try {
                    s = new String(buffer, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                UTF8Info utf8Info = new UTF8Info(pool);
                utf8Info.setLength(len);
                utf8Info.setValue(s);
                pool.addConstantInfo(utf8Info);
            } else if (tag == 7) {
                //class_info
                int utf8Index = iter.nextU2ToInt();
                ClassInfo classInfo = new ClassInfo(pool);
                classInfo.setUtf8Index(utf8Index);
                pool.addConstantInfo(classInfo);
            } else if (tag == 8) {
                int stringIndex = iter.nextU2ToInt();
                StringInfo stringInfo = new StringInfo(pool);
                stringInfo.setIndex(stringIndex);
                pool.addConstantInfo(stringInfo);
            } else if (tag == 9) {
                FieldRefInfo info = new FieldRefInfo(pool);
                info.setClassInfoIndex(iter.nextU2ToInt());
                info.setNameAndTypeIndex(iter.nextU2ToInt());
                pool.addConstantInfo(info);
            } else if (tag == 10) {
                MethodRefInfo info = new MethodRefInfo(pool);
                info.setClassInfoIndex(iter.nextU2ToInt());
                info.setNameAndTypeIndex(iter.nextU2ToInt());
                pool.addConstantInfo(info);
            } else if (tag == 12) {
                NameAndTypeInfo info = new NameAndTypeInfo(pool);
                info.setIndex1((iter.nextU2ToInt()));
                info.setIndex2(iter.nextU2ToInt());
                pool.addConstantInfo(info);
            } else {
                throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
            }

        }
        return pool;
    }


}
