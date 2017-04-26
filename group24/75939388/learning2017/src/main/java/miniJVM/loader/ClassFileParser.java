package miniJVM.loader;


import miniJVM.attr.CodeAttr;
import miniJVM.attr.LineNumberTable;
import miniJVM.attr.LocalVariableTable;
import miniJVM.attr.StackMapTable;
import miniJVM.clz.AccessFlag;
import miniJVM.clz.ClassFile;
import miniJVM.clz.ClassIndex;
import miniJVM.constant.*;
import miniJVM.field.Field;
import miniJVM.method.Method;

public class ClassFileParser {

    private ClassFile clzFile = new ClassFile();

    private ByteCodeIterator iter;

    private static final int MAGIC_NUMBER_LENGTH = 4;

    private static ConstantPool pool;

    public ClassFile parse(byte[] codes) {
        iter = new ByteCodeIterator(codes);
        iter.pos = MAGIC_NUMBER_LENGTH;
        //1. 版本信息
        parseVersions(iter);
        //2. 常量池
        pool = new ConstantPool();
        parseConstantPool(iter);
        //3. 访问标记
        parseAccessFlag(iter);
        //4. classIndex superClassIndex
        parseClassIndex(iter);
        //5. 接口，即便没有也要调用，因为字节码里是固定有的
        parseInterfaces(iter);
        //6. 字段
        parseFields(iter);
        //7. 方法
        parseMethods(iter);
        //8. class文件结尾的stackMapTable, 只读取，不处理
        parseStackMapTable(iter);
        return clzFile;
    }

    private void parseVersions(ByteCodeIterator iter) {
        int minorVersion = iter.nextU2ToInt();
        clzFile.setMinorVersion(minorVersion);

        int majorVersion = iter.nextU2ToInt();
        clzFile.setMajorVersion(majorVersion);
    }

    private void parseAccessFlag(ByteCodeIterator iter) {
        int accessValue = iter.nextU2ToInt();
        AccessFlag accessFlag = new AccessFlag(accessValue);
        clzFile.setAccessFlag(accessFlag);
    }

    private void parseClassIndex(ByteCodeIterator iter) {
        int thisClass = iter.nextU2ToInt();
        int superClass = iter.nextU2ToInt();
        ClassIndex classInfo = new ClassIndex(thisClass, superClass);
        clzFile.setClassIndex(classInfo);
    }

    private void parseConstantPool(ByteCodeIterator iter) {

        try {
            //常量池个数
            int cnstSize = iter.nextU2ToInt();
            for (int i = 0; i < cnstSize; i++) {
                int index = -1;

                if (i == 0) {
                    pool.addConstantInfo(null);
                    continue;
                } else {
                    index = iter.nextU1ToInt();
                }

//				System.out.println("i -> " + i + ", index -> " + index);
                if (index == ConstantInfo.CLASS_INFO) {
                    ClassInfo classInfo = new ClassInfo(pool);
                    classInfo.setUtf8Index(iter.nextU2ToInt());
                    pool.addConstantInfo(classInfo);
                } else if (index == ConstantInfo.UTF8_INFO) {
                    UTF8Info utf8Info = new UTF8Info(pool);
                    int length = iter.nextU2ToInt();
                    utf8Info.setLength(length);
                    utf8Info.setValue(new String(iter.getBytes(length), "utf8"));
                    pool.addConstantInfo(utf8Info);
                } else if (index == ConstantInfo.METHOD_INFO) {
                    MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                    methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
                    methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(methodRefInfo);
                } else if (index == ConstantInfo.NAME_AND_TYPE_INFO) {
                    NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                    nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
                    nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
                    pool.addConstantInfo(nameAndTypeInfo);
                } else if (index == ConstantInfo.FIELD_INFO) {
                    FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                    fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
                    fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(fieldRefInfo);
                } else if (index == ConstantInfo.STRING_INFO) {
                    StringInfo stringInfo = new StringInfo(pool);
                    stringInfo.setIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(stringInfo);
                } else if (index == 0) {
                    pool.addConstantInfo(null);
                } else {
                    throw new Exception("没有针对tag=" + index + "的数据进行处理");
                }
            }
            clzFile.setConstPool(pool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseInterfaces(ByteCodeIterator iter) {
        int interfaceCount = iter.nextU2ToInt();
//        System.out.println("interfaceCount:" + interfaceCount);
        // TODO : 如果实现了interface, 这里需要解析
    }

    private void parseFields(ByteCodeIterator iter) {
        int fieldsCount = iter.nextU2ToInt();

        for (int i = 0; i < fieldsCount; i++) {
            int accessFlag = iter.nextU2ToInt();
            int nameIndex = iter.nextU2ToInt();
            int descriptorIndex = iter.nextU2ToInt();
            int attributeCount = iter.nextU2ToInt();

            Field field = new Field(accessFlag, nameIndex, descriptorIndex, attributeCount, pool);
            clzFile.addField(field);
        }
    }

    private void parseMethods(ByteCodeIterator iter) {
        int methodCount = iter.nextU2ToInt();

        for (int i = 0; i < methodCount; i++) {
            int accessFlag = iter.nextU2ToInt();
            int nameIndex = iter.nextU2ToInt();
            int descriptorIndex = iter.nextU2ToInt();
            Method method = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);
            int attributeCount = iter.nextU2ToInt();//=1
            while(attributeCount > 0){
                CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
                int exceptionTableLength = iter.nextU2ToInt();
                //异常先不处理
                String exceptionTable = iter.nextUxToHexString(exceptionTableLength);

                int subAttributeCount = iter.nextU2ToInt();
                while(subAttributeCount > 0){
                    LineNumberTable lineNumberTable = LineNumberTable.parse(iter);
                    codeAttr.setLineNumberTable(lineNumberTable);
                    subAttributeCount--;
                    LocalVariableTable localVariableTable = LocalVariableTable.parse(iter);
                    codeAttr.setLocalVariableTable(localVariableTable);
                    subAttributeCount--;
                }
                method.setCodeAttr(codeAttr);
                attributeCount--;
            }
            clzFile.addMethod(method);
        }
    }

    private void parseStackMapTable(ByteCodeIterator iter){
        int stackMapCount = iter.nextU2ToInt();
        StackMapTable table = StackMapTable.parse(iter);
    }
}
