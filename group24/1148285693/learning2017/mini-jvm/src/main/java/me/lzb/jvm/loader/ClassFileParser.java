package me.lzb.jvm.loader;

import me.lzb.common.utils.ByteUtils;
import me.lzb.common.utils.StringUtils;
import me.lzb.jvm.clz.AccessFlag;
import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.clz.ClassIndex;
import me.lzb.jvm.constant.*;
import me.lzb.jvm.exception.NotClassFileException;

import java.io.UnsupportedEncodingException;

/**
 * 处理字class文件字节流
 * Created by LZB on 2017/4/14.
 */
public class ClassFileParser {


    private final byte[] data;

    private int index;

    public ClassFileParser(byte[] data) {
        this.data = data;
    }



    public ClassFile parse() throws NotClassFileException{
        ClassFile classFile = new ClassFile();
        magicNumber(classFile);

        if(!StringUtils.equals(classFile.getMagicNumber(), JvmConstant.MAGIC_NUMBER)){
            throw new NotClassFileException("It is not a java class file.");
        }

        version(classFile);

        constantPool(classFile);

        accessFlag(classFile);

        classIndex(classFile);

        return classFile;
    }



    private byte[] nextBytes(int nextLength){
        byte[] target = new byte[nextLength];
        System.arraycopy(data, index, target, 0, nextLength);
        index = index + nextLength;
        return target;
    }

    private int nextBytesToInt(int nextLength){
        return ByteUtils.byteToInt(nextBytes(nextLength));
    }

    private String nextBytesToString(int nextLength){
        return ByteUtils.byteToHexString(nextBytes(nextLength));
    }


    private void magicNumber(ClassFile classFile){
        this.index = 0;
        classFile.setMagicNumber(nextBytesToString(4));
    }

    private void version(ClassFile classFile){
        classFile.setMinorVersion(nextBytesToInt(2));
        classFile.setMajorVersion(nextBytesToInt(2));
    }

    private void constantPool(ClassFile classFile){
        int count = nextBytesToInt(2);
        classFile.setConstantPoolCount(count);

        ConstantPool pool = new ConstantPool();
        pool.addConstantInfo(new NullConstantInfo());

        for (int i = 1; i < count; i++) {

            int tag = nextBytesToInt(1);

            if(tag == JvmConstant.Class_info){
                ClassInfo classInfo = new ClassInfo(pool);
                classInfo.setUtf8Index(nextBytesToInt(2));

                pool.addConstantInfo(classInfo);
                continue;
            }

            if(tag == JvmConstant.Fieldref_info){
                FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                fieldRefInfo.setClassInfoIndex(nextBytesToInt(2));
                fieldRefInfo.setNameAndTypeIndex(nextBytesToInt(2));

                pool.addConstantInfo(fieldRefInfo);
                continue;
            }

            if(tag == JvmConstant.Methodref_info){
                MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                methodRefInfo.setClassInfoIndex(nextBytesToInt(2));
                methodRefInfo.setNameAndTypeIndex(nextBytesToInt(2));

                pool.addConstantInfo(methodRefInfo);
                continue;
            }

            if(tag == JvmConstant.NameAndType_info){
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                nameAndTypeInfo.setIndex1(nextBytesToInt(2));
                nameAndTypeInfo.setIndex2(nextBytesToInt(2));

                pool.addConstantInfo(nameAndTypeInfo);
                continue;
            }

            if(tag == JvmConstant.String_info){
                StringInfo stringInfo = new StringInfo(pool);
                stringInfo.setIndex(nextBytesToInt(2));

                pool.addConstantInfo(stringInfo);
                continue;
            }

            if(tag == JvmConstant.Utf8_info){
                UTF8Info utf8Info = new UTF8Info(pool);
                int len = nextBytesToInt(2);
                byte[] data = nextBytes(len);
                String value = null;
                try {
                    value = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                utf8Info.setLength(len);
                utf8Info.setValue(value);

                pool.addConstantInfo(utf8Info);
                continue;
            }


            throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet." + i);
        }



        classFile.setConstantPool(pool);
    }



    private void accessFlag(ClassFile classFile){
        AccessFlag flag = new AccessFlag(nextBytesToInt(2));
        classFile.setAccessFlag(flag);
    }


    private void classIndex(ClassFile classFile){
        ClassIndex clzIndex = new ClassIndex();

        int thisClassIndex = nextBytesToInt(2);
        int superClassIndex = nextBytesToInt(2);
        clzIndex.setThisClassIndex(thisClassIndex);
        clzIndex.setSuperClassIndex(superClassIndex);

        classFile.setClzIndex(clzIndex);
    }


}
