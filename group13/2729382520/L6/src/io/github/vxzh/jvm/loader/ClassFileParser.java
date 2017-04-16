package io.github.vxzh.jvm.loader;

import io.github.vxzh.jvm.clz.AccessFlag;
import io.github.vxzh.jvm.clz.ClassFile;
import io.github.vxzh.jvm.clz.ClassIndex;
import io.github.vxzh.jvm.clz.ConstantPool;
import io.github.vxzh.jvm.constant.ClassInfo;
import io.github.vxzh.jvm.constant.FieldRefInfo;
import io.github.vxzh.jvm.constant.MethodRefInfo;
import io.github.vxzh.jvm.constant.NameAndTypeInfo;
import io.github.vxzh.jvm.constant.NullConstantInfo;
import io.github.vxzh.jvm.constant.StringInfo;
import io.github.vxzh.jvm.constant.UTF8Info;
import io.github.vxzh.jvm.field.Field;
import io.github.vxzh.jvm.method.Method;

import java.io.UnsupportedEncodingException;

import static io.github.vxzh.jvm.constant.ConstantInfo.CONSTANT_CLASS_INFO;
import static io.github.vxzh.jvm.constant.ConstantInfo.CONSTANT_FIELDREF_INFO;
import static io.github.vxzh.jvm.constant.ConstantInfo.CONSTANT_METHODREF_INFO;
import static io.github.vxzh.jvm.constant.ConstantInfo.CONSTANT_NAMEANDTYPE_INFO;
import static io.github.vxzh.jvm.constant.ConstantInfo.CONSTANT_STRING_INFO;
import static io.github.vxzh.jvm.constant.ConstantInfo.CONSTANT_UTF8_INFO;

public class ClassFileParser {


    public ClassFile parse(byte[] codes) {

        ClassFile clzFile = new ClassFile();

        ByteCodeIterator iter = new ByteCodeIterator(codes);

        //验证魔数
        String magicNumber = iter.nextU4ToHexString();
        if (!"cafebabe".equals(magicNumber)) {
            return null;
        }

        //解析次版本号和主版本号
        clzFile.setMinorVersion(iter.nextU2ToInt());
        clzFile.setMajorVersion(iter.nextU2ToInt());

        //解析常量池
        ConstantPool pool = parseConstantPool(iter);
        clzFile.setConstPool(pool);

        //解析访问标志
        AccessFlag flag = parseAccessFlag(iter);
        clzFile.setAccessFlag(flag);

        //解析类索引和父类索引
        ClassIndex clzIndex = parseClassInfex(iter);
        clzFile.setClassIndex(clzIndex);

        //解析接口
        parseInterfaces(iter);

        //解析字段表集合
        parseFileds(clzFile, iter);

        //解析方法表集合
        parseMethods(clzFile, iter);

        return clzFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

        AccessFlag flag = new AccessFlag(iter.nextU2ToInt());
        // System.out.println("Is public class: " + flag.isPublicClass());
        // System.out.println("Is final class : " + flag.isFinalClass());

        return flag;
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iter) {

        int thisClassIndex = iter.nextU2ToInt();
        int superClassIndex = iter.nextU2ToInt();

        ClassIndex clzIndex = new ClassIndex();

        clzIndex.setThisClassIndex(thisClassIndex);
        clzIndex.setSuperClassIndex(superClassIndex);

        return clzIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {

        int constPoolCount = iter.nextU2ToInt();

        System.out.println("Constant Pool Count :" + constPoolCount);

        ConstantPool pool = new ConstantPool();

        pool.addConstantInfo(new NullConstantInfo());

        for (int i = 1; i <= constPoolCount - 1; i++) {

            int tag = iter.nextU1toInt();

            if (tag == CONSTANT_CLASS_INFO) {//7
                // Class Info
                int utf8Index = iter.nextU2ToInt();
                ClassInfo clzInfo = new ClassInfo(pool);
                clzInfo.setNameIndex(utf8Index);
                pool.addConstantInfo(clzInfo);
            } else if (tag == CONSTANT_UTF8_INFO) {//1
                // UTF-8 String
                int len = iter.nextU2ToInt();
                byte[] data = iter.getBytes(len);
                String value = null;
                try {
                    value = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                UTF8Info utf8Str = new UTF8Info(pool);
                utf8Str.setLength(len);
                utf8Str.setValue(value);
                pool.addConstantInfo(utf8Str);
            } else if (tag == CONSTANT_STRING_INFO) {//8
                StringInfo info = new StringInfo(pool);
                info.setIndex(iter.nextU2ToInt());
                pool.addConstantInfo(info);
            } else if (tag == CONSTANT_FIELDREF_INFO) {//9
                FieldRefInfo field = new FieldRefInfo(pool);
                field.setClassInfoIndex(iter.nextU2ToInt());
                field.setNameAndTypeIndex(iter.nextU2ToInt());
                pool.addConstantInfo(field);
            } else if (tag == CONSTANT_METHODREF_INFO) {//10
                // MethodRef
                MethodRefInfo method = new MethodRefInfo(pool);
                method.setClassInfoIndex(iter.nextU2ToInt());
                method.setNameAndTypeIndex(iter.nextU2ToInt());
                pool.addConstantInfo(method);
            } else if (tag == CONSTANT_NAMEANDTYPE_INFO) {//12
                // Name and Type Info
                NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
                nameType.setNameIndex(iter.nextU2ToInt());
                nameType.setDescriptorIndex(iter.nextU2ToInt());
                pool.addConstantInfo(nameType);
            } else {
                throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
            }
        }

        System.out.println("Finished reading Constant pool ");

        return pool;
    }

    private void parseInterfaces(ByteCodeIterator iter) {
        int interfaceCount = iter.nextU2ToInt();

        System.out.println("interfaceCount:" + interfaceCount);

        // TODO : 如果实现了interface, 这里需要解析
    }

    private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
        int fieldCount = iter.nextU2ToInt();

        for (int i = 1; i <= fieldCount; i++) {
            Field f = Field.parse(clzFile, iter);
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
