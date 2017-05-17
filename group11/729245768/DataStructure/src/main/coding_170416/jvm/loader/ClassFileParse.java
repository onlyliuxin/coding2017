package main.coding_170416.jvm.loader;

import main.coding_170416.jvm.clz.AccessFlag;
import main.coding_170416.jvm.clz.ClassFile;
import main.coding_170416.jvm.clz.ClassIndex;
import main.coding_170416.jvm.constant.*;
import main.coding_170416.jvm.field.Field;
import main.coding_170416.jvm.method.Method;

import java.io.UnsupportedEncodingException;

/**
 * Created by peter on 2017/4/21.
 */
public class ClassFileParse {
    public ClassFile parse(byte[] codes){
        ClassFile clzFile = new ClassFile();

        ByteCodeIterator iterator = new ByteCodeIterator(codes);

        String magicNumber = iterator.nextU4ToHexString();

        if(!"cafebabe".equals(magicNumber)){
            return null;
        }
        clzFile.setMinorVersion(iterator.nextU2ToInt());
        clzFile.setMajorVersion(iterator.nextU2ToInt());

        ConstantPool pool = parseConstantPool(iterator);
        clzFile.setConstantPool(pool);

        AccessFlag flag = parseAccessFlag(iterator);
        clzFile.setAccessFlag(flag);

        parseInterfaces(iterator);

        parseFields(clzFile,iterator);

        parseMethods(clzFile,iterator);

        return clzFile;
    }

    public AccessFlag parseAccessFlag(ByteCodeIterator iterator){
        AccessFlag flag = new AccessFlag(iterator.nextU2ToInt());
        return flag;
    }

    public ClassIndex parseClassIndex(ByteCodeIterator iterator){
        int thisClassIndex = iterator.nextU2ToInt();
        int superClassIndex = iterator.nextU2ToInt();

        ClassIndex clzIndex = new ClassIndex();

        clzIndex.setThisClassIndex(thisClassIndex);
        clzIndex.setSuperClassIndex(superClassIndex);
        return clzIndex;
    }

    public ConstantPool parseConstantPool(ByteCodeIterator iterator){
        int constantPoolCount = iterator.nextU2ToInt();

        ConstantPool pool = new ConstantPool();
        pool.addConstantInfo(new NullConstantInfo());

        for(int i =1;i<=constantPoolCount-1;i++){
            int tag = iterator.nextU1ToInt();
            if(tag==7){
                //Class Info
                int utf8Index = iterator.nextU2ToInt();
                ClassInfo classInfo = new ClassInfo(pool);
                classInfo.setUtf8Index(utf8Index);

                pool.addConstantInfo(classInfo);
            }else if(tag==1){
                //UTF-8 String
                int len = iterator.nextU2ToInt();
                byte[] data = iterator.getBytes(len);
                String value = null;
                try {
                    value = new String(data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                UTF8Info utf8Info = new UTF8Info(pool);
                utf8Info.setLength(len);
                utf8Info.setValue(value);
                pool.addConstantInfo(utf8Info);
            }else if(tag==8){
                StringInfo info = new StringInfo(pool);
                info.setIndex(iterator.nextU2ToInt());
                pool.addConstantInfo(info);
            }else if(tag==9){
                FieldRefInfo field = new FieldRefInfo(pool);
                field.setClassInfoIndex(iterator.nextU2ToInt());
                field.setNameAndTypeIndex(iterator.nextU2ToInt());
                pool.addConstantInfo(field);
            }else if(tag==10){
                //MethodRef
                MethodRefInfo method = new MethodRefInfo(pool);
                method.setClassInfoIndex(iterator.nextU2ToInt());
                method.setNameAndTypeIndex(iterator.nextU2ToInt());
                pool.addConstantInfo(method);
            }else if(tag==12){
                //Name and Type Info
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                nameAndTypeInfo.setIndexA(iterator.nextU2ToInt());
                nameAndTypeInfo.setIndexB(iterator.nextU2ToInt());
                pool.addConstantInfo(nameAndTypeInfo);
            }else {
                throw new RuntimeException("the constant pool tag "+tag+" has not implemented");
            }
        }
        System.out.println("Finished reading Constant pool");

        return pool;
    }
    public void parseInterfaces(ByteCodeIterator iterator){
        int interfaxrCount = iterator.nextU2ToInt();
        System.out.println("interfaceCount:"+interfaxrCount);
        //如果实现了接口这里需要实现
    }
    public void parseFields(ClassFile clzFile, ByteCodeIterator iterator){
        int fieldCount = iterator.nextU2ToInt();

        for(int i=1;i<=fieldCount;i++){
            Field f = Field.parse(clzFile.getConstantPool(),iterator);
            clzFile.addField(f);
        }
    }

    public void parseMethods(ClassFile clzfFile, ByteCodeIterator iterator){
        int methodCount = iterator.nextU2ToInt();

        for(int i=0;i<=methodCount;i++){
            Method m = Method.parse(clzfFile,iterator);
            clzfFile.addMethod(m);
        }
    }
}
