package com.pan.jvm.loader;

import com.pan.jvm.clz.AccessFlag;
import com.pan.jvm.clz.ClassFile;
import com.pan.jvm.clz.ClassIndex;
import com.pan.jvm.constant.*;
import com.pan.jvm.field.Field;
import com.pan.jvm.method.Method;

import java.io.UnsupportedEncodingException;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {

        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        String magicNumber = iterator.nextU4ToHexString();
        if (!"cafebabe".equals(magicNumber)) {// 验证是否为Java的.class文件
            return null;
        }
        ClassFile classFile = new ClassFile();
        classFile.setMinorVersion(iterator.nextU2ToInt());
        classFile.setMajorVersion(iterator.nextU2ToInt());

        ConstantPool constantPool = parseConstantPool(iterator);
        classFile.setConstPool(constantPool);

        AccessFlag flag = parseAccessFlag(iterator);
        classFile.setAccessFlag(flag);

        // this clz 和 supper clz
        ClassIndex clzIndex = parseClassIndex(iterator);
        classFile.setClassIndex(clzIndex);

        // interface
        parseInterfaces(iterator);

        // field
        parseFields(classFile, iterator);
        
        // method
        parseMethods(classFile, iterator);

        return classFile;
    }

    private void parseMethods(ClassFile classFile, ByteCodeIterator iterator) {
        int methodsCount = iterator.nextU2ToInt();
        System.out.println("Methods Count: " + methodsCount);

        for (int i = 1; i <= methodsCount; i++) {
            Method method = Method.parse(classFile, iterator);
            classFile.addMethod(method);
        }
    }


    private void parseFields(ClassFile clzFile, ByteCodeIterator iterator) {
        int fieldsCount = iterator.nextU2ToInt();
        System.out.println("Field count:" + fieldsCount);
        for (int i = 1; i <= fieldsCount; i++) {// 从第一个开始，因为不包含本身
            Field field = Field.parse(clzFile.getConstantPool(), iterator);
            clzFile.addField(field);
        }
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
        return accessFlag;
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iter) {

        int thisClassIndex = iter.nextU2ToInt();
        int supperClassIndex = iter.nextU2ToInt();

        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(thisClassIndex);
        classIndex.setSuperClassIndex(supperClassIndex);
        return classIndex;
    }

    private void parseInterfaces(ByteCodeIterator iter) {
        int interfaceCount = iter.nextU2ToInt();

        System.out.println("interfaceCount:" + interfaceCount);

        // TODO : 如果实现了interface, 这里需要解析
    }

    /**
     * 解析常量池
     *
     * @param iter
     * @return
     */
    private ConstantPool parseConstantPool(ByteCodeIterator iter) {

        int constPoolCount = iter.nextU2ToInt();

        System.out.println("Constant Pool Count :" + constPoolCount);
        ConstantPool pool = new ConstantPool();
        // 因为常量池中的信息是从 1 开始的，但是数组或者List 下标是从0开始，所以设置第一个为空的常量
        pool.addConstantInfo(new NullConstantInfo());

        for (int i = 1; i <= constPoolCount - 1; i++) {

            // 获取标识符信息
            int tag = iter.nextU1toInt();

            switch (tag) {

                case 7:         //CONSTANT_Class
                    int utf8Index = iter.nextU2ToInt();
                    ClassInfo classInfo = new ClassInfo(pool);
                    classInfo.setUtf8Index(utf8Index);

                    pool.addConstantInfo(classInfo);
                    break;
                case 9:         // CONSTANT_Fieldref
                    FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                    fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
                    fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());

                    pool.addConstantInfo(fieldRefInfo);
                    break;
                case 10:        // CONSTANT_Methodref
                    MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                    methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
                    methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());

                    pool.addConstantInfo(methodRefInfo);
                    break;
                case 8:
                    StringInfo info = new StringInfo(pool);
                    info.setIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(info);
                    break;
                case 12:        // CONSTANT_NameAndType
                    NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                    nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
                    nameAndTypeInfo.setIndex2(iter.nextU2ToInt());

                    pool.addConstantInfo(nameAndTypeInfo);
                    break;
                case 1:         // CONSTANT_Utf8
                    int length = iter.nextU2ToInt();
                    byte[] data = iter.getBytes(length);
                    String value = null;
                    try {
                        value = new String(data, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    UTF8Info utf8Str = new UTF8Info(pool);
                    utf8Str.setLength(length);
                    utf8Str.setValue(value);

                    pool.addConstantInfo(utf8Str);
                    break;
                default:
                    throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");

            }
        }
        System.out.println("Finished reading Constant pool ");
        return pool;
    }


}
