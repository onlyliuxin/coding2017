package miniJVM.loader;


import miniJVM.clz.AccessFlag;
import miniJVM.clz.ClassFile;
import miniJVM.clz.ClassIndex;
import miniJVM.cmd.ByteCodeCommand;
import miniJVM.cmd.CommandParser;
import miniJVM.constant.*;
import miniJVM.field.Field;

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
        //TODO 3. 访问标记
        parseAccessFlag(iter);
        //TODO 4. classIndex superClassIndex
        parseClassIndex(iter);
        //TODO 5. 接口，即便没有也要调用，因为字节码里是固定有的
        parseInterfaces(iter);
        //TODO 6. 字段
        parseFields(iter);
        //TODO 7. 方法
        parseMethods(iter);

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
            System.out.println("常量个数：" + (cnstSize - 1));
            for (int i = 0; i < cnstSize; i++) {
                int index = -1;

                if(i == 0){
                	pool.addConstantInfo(null);
                	continue;
				}else{
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
                } else if(index == 0){
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

		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}

	private void parseFields(ByteCodeIterator iter) {
        int fieldsCount = iter.nextU2ToInt();

        for(int i = 0 ; i < fieldsCount; i++){
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

        for(int i = 0; i < methodCount; i++){
            int accessFlag = iter.nextU2ToInt();
            int nameIndex = iter.nextU2ToInt();
            int descriptorIndex = iter.nextU2ToInt();
            int attributeCount = iter.nextU2ToInt();
            int attributeCountTemp = attributeCount;
            while(attributeCountTemp > 0){
                int attributeNameIndex = iter.nextU2ToInt();
                UTF8Info attributeName = (UTF8Info) pool.getConstantInfo(attributeNameIndex);
                if(attributeName.getValue().equalsIgnoreCase("code")){
                    int attributeLength = iter.nextU4ToInt();
                    int maxStack = iter.nextU2ToInt();
                    int maxLocals = iter.nextU2ToInt();
                    int codeLength = iter.nextU4ToInt();
                    String cmdCodes = iter.nextUxToHexString(codeLength);
                    ByteCodeCommand[] cmds = CommandParser.parse(clzFile, cmdCodes);
                }

                attributeCountTemp--;
            }
        }
	}
}
