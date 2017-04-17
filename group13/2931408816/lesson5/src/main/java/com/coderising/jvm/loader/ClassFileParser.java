package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

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
import com.coderising.jvm.util.Util;

public class ClassFileParser {
    private final int CONSTANT_CLASS = 7;
    private final int CONSTANT_FIELD_REF = 9;
    private final int CONSTANT_METHOD_REF = 10;                // 0A
    private final int CONSTANT_INTERFACE_METHOD_REF = 11;
    private final int CONSTANT_STRING = 8;
    private final int CONSTANT_INTEGER = 3;
    private final int CONSTANT_FLOAT = 4;
    private final int CONSTANT_LONG = 5;
    private final int CONSTANT_DOUBLE = 6;
    private final int CONSTANT_NAME_AND_TYPE = 12;            // 0C
    private final int CONSTANT_UTF8 = 1;
    private final int CONSTANT_METHOD_HANDLE = 15;
    private final int CONSTANT_METHOD_TYPE = 16;
    private final int CONSTANT_INVOKE_DYNAMIC = 18;

    public ClassFile parse(byte[] codes) {

        // 读 Magic Number
        ByteCodeIterator it = new ByteCodeIterator(codes);
        String magicNumber = it.nextU4ToHexString();
        if (!Objects.equals("cafebabe", magicNumber)) {
            throw new RuntimeException("这不是一个Java字节码文件");
        }

        ClassFile classFile = new ClassFile();
        // 读 次版本号
        int minorVersion = it.nextU2ToInt();
        classFile.setMinorVersion(minorVersion);
        // 读 主版本号
        int majorVersion = it.nextU2ToInt();
        classFile.setMajorVersion(majorVersion);
        classFile.setConstPool(parseConstantPool(it));
        classFile.setAccessFlag(parseAccessFlag(it));
        classFile.setClassIndex(parseClassInfex(it));
        return classFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

        return new AccessFlag(iter.nextU2ToInt());
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iter) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iter.nextU2ToInt());
        classIndex.setSuperClassIndex(iter.nextU2ToInt());
        return classIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator it) {

        // 读 常量池个数
        int count = it.nextU2ToInt();
        ConstantPool constantPool = new ConstantPool();
        constantPool.addConstantInfo(new NullConstantInfo());
        for (int i = 0; i < count; i++) {
            int tag = it.nextU1ToInt();
            switch (tag) {
                case CONSTANT_CLASS:
                    ClassInfo classInfo = new ClassInfo(constantPool);
                    classInfo.setUtf8Index(it.nextU2ToInt());
                    constantPool.addConstantInfo(classInfo);
                    System.out.println("classInfo "+classInfo.getUtf8Index());
                    break;
                case CONSTANT_UTF8:
                    UTF8Info utf8Info = new UTF8Info(constantPool);
                    utf8Info.setLength(it.nextU2ToInt());
                    utf8Info.setValue(it.nextToString(utf8Info.getLength()));
                    constantPool.addConstantInfo(utf8Info);
                    System.out.println("utf-8 "+utf8Info.getValue());
                    break;
                case CONSTANT_METHOD_REF:
                    MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
                    methodRefInfo.setClassInfoIndex(it.nextU2ToInt());
                    methodRefInfo.setNameAndTypeIndex(it.nextU2ToInt());
                    constantPool.addConstantInfo(methodRefInfo);
                    System.out.println("method ref "+methodRefInfo.getClassInfoIndex()+" "+methodRefInfo.getNameAndTypeIndex());
                    break;
                case CONSTANT_NAME_AND_TYPE:
                    NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
                    nameAndTypeInfo.setIndex1(it.nextU2ToInt());
                    nameAndTypeInfo.setIndex2(it.nextU2ToInt());
                    constantPool.addConstantInfo(nameAndTypeInfo);
                    System.out.println("name and type "+nameAndTypeInfo.getIndex1()+" "+nameAndTypeInfo.getIndex2());
                    break;
                case CONSTANT_FIELD_REF:
                    FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
                    fieldRefInfo.setClassInfoIndex(it.nextU2ToInt());
                    fieldRefInfo.setNameAndTypeIndex(it.nextU2ToInt());
                    constantPool.addConstantInfo(fieldRefInfo);
                    System.out.println("field ref "+fieldRefInfo.getClassInfoIndex()+" "+fieldRefInfo.getNameAndTypeIndex());
                    break;
                case CONSTANT_STRING:
                    StringInfo stringInfo = new StringInfo(constantPool);
                    stringInfo.setIndex(it.nextU2ToInt());
                    constantPool.addConstantInfo(stringInfo);
                    System.out.println("string "+stringInfo.getIndex());
                    break;
                default:
//                    throw new RuntimeException("还不支持的tag " + tag);
            }
        }
        return constantPool;
    }

    public static void main(String[] args) {
        String path1 = "D:\\src\\java\\study\\coding2017\\group13\\2931408816\\lesson5\\build\\classes\\main";
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "com.coderising.jvm.test.Main";
        ClassFile clzFile = null;
        clzFile = loader.loadClass(className);
        clzFile.print();
    }
}
/*
Classfile /D:/src/java/study/coding2017/group13/2931408816/lesson5/build/classes/main/com/coderising/jvm/test/Main.class
  Last modified 2017-4-9; size 285 bytes
  MD5 checksum a159f82f5c9fecafdde2333579a3db3b
  Compiled from "Main.java"
public class com.coderising.jvm.test.Main
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #3.#13         // java/lang/Object."<init>":()V
   #2 = Class              #14            // com/coderising/jvm/test/Main
   #3 = Class              #15            // java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               LocalVariableTable
   #9 = Utf8               this
  #10 = Utf8               Lcom/coderising/jvm/test/Main;
  #11 = Utf8               SourceFile
  #12 = Utf8               Main.java
  #13 = NameAndType        #4:#5          // "<init>":()V
  #14 = Utf8               com/coderising/jvm/test/Main
  #15 = Utf8               java/lang/Object
{
  public com.coderising.jvm.test.Main();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 6: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/coderising/jvm/test/Main;
}
SourceFile: "Main.java"
 */