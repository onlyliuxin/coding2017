package me.lzb.jvm.loader;

import me.lzb.common.utils.ByteUtils;
import me.lzb.common.utils.StringUtils;
import me.lzb.jvm.attr.*;
import me.lzb.jvm.clz.AccessFlag;
import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.clz.ClassIndex;
import me.lzb.jvm.constant.*;
import me.lzb.jvm.field.Field;
import me.lzb.jvm.method.Method;

import java.io.UnsupportedEncodingException;

/**
 * 处理字class文件字节流
 *
 * @author LZB
 */
public class ClassFileParser {


    private final byte[] data;

    private int index;

    public ClassFileParser(byte[] data) {
        this.data = data;
    }


    public ClassFile parse() {
        ClassFile classFile = new ClassFile();
        //class文件的开头四个字节一定是“cafebabe”，叫做magic number
        parserMagicNumber(classFile);

        if (!StringUtils.equals(classFile.getMagicNumber(), ConstantInfo.MAGIC_NUMBER)) {
            throw new RuntimeException("It is not a java class file.");
        }
        //两个字节的小版本号，两个字节的主版本号
        parserVersion(classFile);

        //常量池，进去看
        parserConstantPool(classFile);

        //两个字节的类的访问权限
        parserAccessFlag(classFile);

        //本class和父类的常量池索引
        parserClassIndex(classFile);

        //接口部分没有实现
        parserInterface(classFile);

        //成员变量
        parserField(classFile);

        //函数
        parserMethod(classFile);

        return classFile;
    }


    private byte[] nextBytes(int nextLength) {
        byte[] target = new byte[nextLength];
        System.arraycopy(data, index, target, 0, nextLength);
        index = index + nextLength;
        return target;
    }

    private int nextBytesToInt(int nextLength) {
        return ByteUtils.byteToInt(nextBytes(nextLength));
    }

    private String nextBytesToString(int nextLength) {
        return ByteUtils.byteToHexString(nextBytes(nextLength));
    }


    private void parserMagicNumber(ClassFile classFile) {
        this.index = 0;
        classFile.setMagicNumber(nextBytesToString(4));
    }

    private void parserVersion(ClassFile classFile) {
        classFile.setMinorVersion(nextBytesToInt(2));
        classFile.setMajorVersion(nextBytesToInt(2));
    }

    private void parserConstantPool(ClassFile classFile) {
        //开头两个字节，常量池常量总个数，包括这两个字节（这两字节算一个常量）
        int count = nextBytesToInt(2);

        ConstantPool pool = new ConstantPool();
        pool.addConstantInfo(new NullConstantInfo());


        //i=1开始，遍历剩下的常量
        for (int i = 1; i < count; i++) {
            //每次循环，第一个字节，根据jvm规范，确定常量类型，
            //CONSTANT_Class 7
            //CONSTANT_Fieldref 9
            //CONSTANT_Methodref 10
            //CONSTANT_InterfaceMethodref 11
            //CONSTANT_String 8
            //CONSTANT_Integer 3
            //CONSTANT_Float 4
            //CONSTANT_Long 5
            //CONSTANT_Double 6
            //CONSTANT_NameAndType 12
            //CONSTANT_Utf8 1
            //CONSTANT_MethodHandle 15
            //CONSTANT_MethodType 16
            //CONSTANT_InvokeDynamic 18
            int tag = nextBytesToInt(1);

            if (tag == ConstantInfo.Class_info) {
                //CONSTANT_Class_info {
                //  u1 tag;         //07
                //  u2 name_index;  //是一个UTF8类型的常量池的索引
                // }
                ClassInfo classInfo = new ClassInfo(pool);
                classInfo.setUtf8Index(nextBytesToInt(2));

                pool.addConstantInfo(classInfo);
                continue;
            }

            if (tag == ConstantInfo.Fieldref_info) {

                //CONSTANT_Fieldref_info {
                //  u1 tag;                 // 9
                //  u2 class_index;         //CONSTANT_Class类型的索引，表示这个成员变量是这个类，或者接口的
                //  u2 name_and_type_index; //CONSTANT_NameAndType_info类型的索引，表示字段的名字和描述符
                // }
                FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                fieldRefInfo.setClassInfoIndex(nextBytesToInt(2));
                fieldRefInfo.setNameAndTypeIndex(nextBytesToInt(2));

                pool.addConstantInfo(fieldRefInfo);
                continue;
            }

            if (tag == ConstantInfo.Methodref_info) {
                //CONSTANT_Methodref_info {
                //  u1 tag;                 //10
                //  u2 class_index;         //CONSTANT_Class类型的索引，表示这个成员变量是这个类的(不能是接口)
                //  u2 name_and_type_index; //CONSTANT_NameAndType_info类型的索引, 方法名&(参数)返回值  println&(Ljava/lang/String;I)V   ,类型参数有;结尾L开头，基础数据类型没有;和L
                // }
                MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                methodRefInfo.setClassInfoIndex(nextBytesToInt(2));
                methodRefInfo.setNameAndTypeIndex(nextBytesToInt(2));

                pool.addConstantInfo(methodRefInfo);
                continue;
            }

            if (tag == ConstantInfo.NameAndType_info) {

                //CONSTANT_NameAndType_info {
                //  u1 tag;                 //12
                //  u2 name_index;          //utf8类型索引，方法名或者字段名
                //  u2 descriptor_index;    //utf8类型索引，字段类型，或者方法的(参数)返回值
                // }
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                nameAndTypeInfo.setIndex1(nextBytesToInt(2));
                nameAndTypeInfo.setIndex2(nextBytesToInt(2));

                pool.addConstantInfo(nameAndTypeInfo);
                continue;
            }

            if (tag == ConstantInfo.String_info) {
                //CONSTANT_String_info {
                //  u1 tag;             //8
                //  u2 string_index;    //utf8类型常量池索引
                //}
                StringInfo stringInfo = new StringInfo(pool);
                stringInfo.setIndex(nextBytesToInt(2));

                pool.addConstantInfo(stringInfo);
                continue;
            }

            if (tag == ConstantInfo.Utf8_info) {
                //CONSTANT_Utf8_info {
                //  u1 tag;             //1
                //  u2 length;          //bytes[]数组的长度
                //  u1 bytes[length];   //字符串常量采用改进过的UTF-8编码表示
                // }
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


    private void parserAccessFlag(ClassFile classFile) {
        AccessFlag flag = new AccessFlag(nextBytesToInt(2));
        classFile.setAccessFlag(flag);
    }


    private void parserClassIndex(ClassFile classFile) {
        ClassIndex clzIndex = new ClassIndex();
        //本class 常量池class类型索引
        int thisClassIndex = nextBytesToInt(2);
        //父类  常量池class类型索引
        int superClassIndex = nextBytesToInt(2);
        clzIndex.setThisClassIndex(thisClassIndex);
        clzIndex.setSuperClassIndex(superClassIndex);

        classFile.setClzIndex(clzIndex);
    }

    private void parserInterface(ClassFile classFile) {
        int count = nextBytesToInt(2);
        //TODO 实现interface
    }


    private void parserField(ClassFile classFile) {
        //成员变量个数
        int count = nextBytesToInt(2);
        for (int i = 1; i <= count; i++) {
            //访问权限
            int accessFlags = nextBytesToInt(2);
            //常量池UTF8类型索引，变量名
            int nameIndex = nextBytesToInt(2);
            //常量池utf8类型索引，变量类型
            int descriptorIndex = nextBytesToInt(2);
            //当前字段附加属性的个数
            int attributesCount = nextBytesToInt(2);

            Field field = new Field(accessFlags, nameIndex, descriptorIndex, classFile.getConstantPool());

            for (int j = 1; j <= attributesCount; j++) {
                //属性类型，utf8类型的索引
                int attrNameIndex = nextBytesToInt(2);
                //获取类型值
                String attrName = classFile.getConstantPool().getUTF8String(attrNameIndex);

                if (AttributeInfo.CONST_VALUE.equals(attrName)) {
                    //ConstantValue_attribute {
                    //  u2 attribute_name_index;    //就是if前面的两个字节，utf8类型索引，表面名字
                    //  u4 attribute_length;        //ConstantValue的attribute_length固定值为2
                    //  u2 constantvalue_index;     // 常量池索引，可以引用基础数据类型和String
                    // }
                    int attrLen = nextBytesToInt(4);
                    ConstantValue constValue = new ConstantValue(attrNameIndex, attrLen);
                    constValue.setConstValueIndex(nextBytesToInt(2));
                    field.setConstantValue(constValue);
                } else {
                    throw new RuntimeException("the attribute " + attrName + " has not been implemented yet.");
                }
            }

            classFile.addField(field);
        }
    }

    private void parserMethod(ClassFile classFile) {
        //函数个数
        int count = nextBytesToInt(2);

        for (int i = 1; i <= count; i++) {
            //函数访问权限
            int accessFlags = nextBytesToInt(2);
            //常量池utf8类型索引，函数名
            int nameIndex = nextBytesToInt(2);
            //常量池utf8类型索引，参数和返回值()V
            int descriptorIndex = nextBytesToInt(2);
            //附加属性数量
            int attributesCount = nextBytesToInt(2);


            Method method = new Method(classFile, accessFlags, nameIndex, descriptorIndex);


            for (int j = 1; j <= attributesCount; j++) {

                //附加属性名索引，utf8类型常量池索引
                int attributeNameIndex = nextBytesToInt(2);
                //获取属性名
                String attributeName = classFile.getConstantPool().getUTF8String(attributeNameIndex);

                if (StringUtils.equalsIgnoreCase(attributeName, AttributeInfo.CODE)) {
                    //处理code类型附加属性
                    parserCodeAttr(attributeNameIndex, method, classFile);
                    continue;
                }


                throw new RuntimeException("only CODE attribute is implemented.");
            }

            classFile.addMethod(method);

        }
    }


    private void parserCodeAttr(int attributeNameIndex, Method method, ClassFile classFile) {
        //Code_attribute {
        //  u2 attribute_name_index;
        //  u4 attribute_length;    //项的值表示当前属性的长度，不包括开始的6个字节。
        //  u2 max_stack;           //栈的最大深度
        //  u2 max_locals;          //分配在当前方法引用的局部变量表中的局部变量个数，包括调用此方法时用于传递参数的局部变量。
        //  u4 code_length;         //code[]数组的字节数长度（真正要执行的指令都在这里面）
        //  u1 code[code_length];   //方法的指令集
        //  u2 exception_table_length; {
        //      u2 start_pc;
        //      u2 end_pc;
        //      u2 handler_pc;
        //      u2 catch_type;
        //  }
        //  exception_table[exception_table_length];
        //  u2 attributes_count;
        //  attribute_info attributes[attributes_count];
        // }

        //
        int attributeLength = nextBytesToInt(4);
        int maxStack = nextBytesToInt(2);
        int maxLocals = nextBytesToInt(2);
        int codeLength = nextBytesToInt(4);

        String code = nextBytesToString(codeLength);

        //处理cmd，解析需要执行的各种指令
        CommandParser commandParser = new CommandParser(code);

        CodeAttr codeAttr = new CodeAttr(attributeNameIndex, attributeLength, maxStack, maxLocals, codeLength, code, commandParser.parse(classFile));

        int exceptionTableLength = nextBytesToInt(2);
        if (exceptionTableLength > 0) {
            String exceptionTable = nextBytesToString(exceptionTableLength);
            //TODO 异常
        }


        int subAttrCount = nextBytesToInt(2);
        for (int k = 1; k <= subAttrCount; k++) {
            int subAttrIndex = nextBytesToInt(2);

            String subAttrName = classFile.getConstantPool().getUTF8String(subAttrIndex);

            if (StringUtils.equalsIgnoreCase(subAttrName, AttributeInfo.LINE_NUM_TABLE)) {

                parserLineNumberTable(codeAttr, subAttrIndex);
                continue;
            }

            if (StringUtils.equalsIgnoreCase(subAttrName, AttributeInfo.LOCAL_VAR_TABLE)) {
                parserLocalVariableTable(codeAttr, subAttrIndex);
                continue;
            }

            if (StringUtils.equalsIgnoreCase(subAttrName, AttributeInfo.STACK_MAP_TABLE)) {
                parserStackMapTable(codeAttr, subAttrIndex);
                continue;
            }


            throw new RuntimeException("Need code to process" + subAttrName);
        }


        method.setCodeAttr(codeAttr);
    }


    private void parserLineNumberTable(CodeAttr codeAttr, int attributeNameIndex) {
//        int attributeNameIndex = nextBytesToInt(2);
        int attributeLength = nextBytesToInt(4);

        int lineNumberTableLength = nextBytesToInt(2);

        LineNumberTable table = new LineNumberTable(attributeNameIndex, attributeLength);

        for (int l = 1; l <= lineNumberTableLength; l++) {
            int startPc = nextBytesToInt(2);
            int lineNumber = nextBytesToInt(2);
            LineNumberItem item = new LineNumberItem(startPc, lineNumber);
            table.addLineNumberItem(item);
        }

        codeAttr.setLineNumTable(table);
    }


    private void parserLocalVariableTable(CodeAttr codeAttr, int attributeNameIndex) {
//        int attributeNameIndex = nextBytesToInt(2);
        int attributeLength = nextBytesToInt(4);

        int localVariableTableLength = nextBytesToInt(2);

        LocalVariableTable table = new LocalVariableTable(attributeNameIndex, attributeLength);

        for (int l = 1; l <= localVariableTableLength; l++) {
            int startPc = nextBytesToInt(2);
            int lineNumber = nextBytesToInt(2);
            int nameIndex = nextBytesToInt(2);
            int descriptorIndex = nextBytesToInt(2);
            int index = nextBytesToInt(2);
            LocalVariableItem item = new LocalVariableItem(startPc, lineNumber, nameIndex, descriptorIndex, index);
            table.addLocalVariableItem(item);
        }

        codeAttr.setLocalVarTable(table);
    }

    private void parserStackMapTable(CodeAttr codeAttr, int attributeNameIndex) {
//        int attributeNameIndex = nextBytesToInt(2);
        int attributeLength = nextBytesToInt(4);
        String code = nextBytesToString(attributeLength);
        StackMapTable table = new StackMapTable(attributeNameIndex, attributeLength, code);
        //后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
        codeAttr.setStackMapTable(table);
    }

}
