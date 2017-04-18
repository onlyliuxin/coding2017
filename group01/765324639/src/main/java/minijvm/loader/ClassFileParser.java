package minijvm.loader;

import java.util.ArrayList;
import java.util.List;

import minijvm.attr.AttributeInfo;
import minijvm.attr.CodeAttr;
import minijvm.attr.LineNumberTable;
import minijvm.attr.LocalVariableTable;
import minijvm.clz.AccessFlag;
import minijvm.clz.ClassFile;
import minijvm.clz.ClassIndex;
import minijvm.constant.ClassInfo;
import minijvm.constant.ConstantInfo;
import minijvm.constant.ConstantPool;
import minijvm.constant.FieldRefInfo;
import minijvm.constant.MethodRefInfo;
import minijvm.constant.NameAndTypeInfo;
import minijvm.constant.NullConstantInfo;
import minijvm.constant.StringInfo;
import minijvm.constant.UTF8Info;
import minijvm.field.Field;
import minijvm.method.Method;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
	    ByteCodeIterator iter = new ByteCodeIterator(codes);
	    ClassFile classFile = new ClassFile();
	    
	    String magicNumber = iter.nextU4ToHexString();
	    if (!"cafebabe".equalsIgnoreCase(magicNumber)) {
	        return null;
	    }
	    
	    classFile.setMinorVersion(iter.nextU2ToInt());
	    classFile.setMajorVersion(iter.nextU2ToInt());
	    
	    ConstantPool pool = parseConstantPool(iter);
	    classFile.setConstPool(pool);
	    
	    classFile.setAccessFlag(parseAccessFlag(iter));
	    classFile.setClassIndex(parseClassIndex(iter));
	    
	    // 目前没有处理接口
	    parseInterfaces(iter);
	    
	    List<Field> fieldList = parseFields(iter, pool);
	    addFields(classFile, fieldList);
	    
	    List<Method> methodList = parseMethods(iter, classFile);
	    addMethods(classFile, methodList);
	    
		return classFile;
	}

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
	    int flag = iter.nextU2ToInt();
	    AccessFlag accessFlag = new AccessFlag(flag);
		return accessFlag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
	    ClassIndex classIndex = new ClassIndex();
	    int thisClassIndex = iter.nextU2ToInt();
	    int superClassIndex = iter.nextU2ToInt();
	    classIndex.setThisClassIndex(thisClassIndex);
	    classIndex.setSuperClassIndex(superClassIndex);
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
	    int size = iter.nextU2ToInt();
	    System.out.println("ConstantPool size: " + size);
	    
	    ConstantPool pool = new ConstantPool();
	    pool.addConstantInfo(new NullConstantInfo()); // 添加无效的第0项
	    
	    for (int i = 1; i < size; i++) {
	        int tag = iter.nextU1ToInt();
	        
	        if (tag == ConstantInfo.UTF8_INFO) {
	            UTF8Info utf8Info = new UTF8Info(pool);
	            int length = iter.nextU2ToInt();
	            String value = iter.nextLengthToString(length);
	            utf8Info.setLength(length);
	            utf8Info.setValue(value);
	            
	            pool.addConstantInfo(utf8Info);
	        } else if (tag == ConstantInfo.CLASS_INFO) {
	            ClassInfo classInfo = new ClassInfo(pool);
	            int index = iter.nextU2ToInt();
	            classInfo.setUtf8Index(index);
	            
	            pool.addConstantInfo(classInfo);
	        } else if (tag == ConstantInfo.STRING_INFO) {
	            StringInfo stringInfo = new StringInfo(pool);
	            int index = iter.nextU2ToInt();
	            stringInfo.setIndex(index);
	            
	            pool.addConstantInfo(stringInfo);
	        } else if (tag == ConstantInfo.FIELD_INFO) {
	            FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
	            int classInfoIndex = iter.nextU2ToInt();
	            int nameAndTypeIndex = iter.nextU2ToInt();
	            fieldRefInfo.setClassInfoIndex(classInfoIndex);
	            fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
	            
	            pool.addConstantInfo(fieldRefInfo);
	        } else if (tag == ClassInfo.METHOD_INFO) {
	            MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
	            int classInfoIndex = iter.nextU2ToInt();
	            int nameAndTypeIndex = iter.nextU2ToInt();
	            methodRefInfo.setClassInfoIndex(classInfoIndex);
	            methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
	            
	            pool.addConstantInfo(methodRefInfo);
	        } else if (tag == ClassInfo.NAME_AND_TYPE_INFO) {
	            NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
	            int index1 = iter.nextU2ToInt();
	            int index2 = iter.nextU2ToInt();
	            nameAndTypeInfo.setIndex1(index1);
	            nameAndTypeInfo.setIndex2(index2);
	            
	            pool.addConstantInfo(nameAndTypeInfo);
	        } else {
	            throw new RuntimeException("这个类型的常量还没有实现");
	        }
	        
	    }
	    
		return pool;
	}

	private void parseInterfaces(ByteCodeIterator iter) {
	    int interfaceNum = iter.nextU2ToInt();
	    if (interfaceNum != 0) {
	        throw new RuntimeException("有接口没有读取");
	    }
	}
	
	private List<Field> parseFields(ByteCodeIterator iter, ConstantPool pool) {
	    int filedNum = iter.nextU2ToInt();
	    System.out.println("Field num: " + filedNum);
	    
	    List<Field> fieldList = new ArrayList<>();
	    for (int i = 0; i < filedNum; i++) {
	        int accessFlag = iter.nextU2ToInt();
	        int nameIndex = iter.nextU2ToInt();
	        int descriptionIndex = iter.nextU2ToInt();
	        int attributesCount = iter.nextU2ToInt();
	        if (attributesCount != 0) {
	            throw new RuntimeException("字段的属性表没有处理");
	        }
	        
	        fieldList.add(new Field(accessFlag, nameIndex, descriptionIndex, pool));
	    }
	    
	    return fieldList;
	}
	
	private void addFields(ClassFile classFile, List<Field> fieldList) {
	    for (Field field : fieldList) {
	        classFile.addField(field);
	    }
	}
	
	private List<Method> parseMethods(ByteCodeIterator iter, ClassFile classFile) {
	    int methodNum = iter.nextU2ToInt();
	    System.out.println("Methods num: " + methodNum);
	    
	    List<Method> methodList = new ArrayList<>();
	    for (int i = 0; i < methodNum; i++) {
	        int accessFlag = iter.nextU2ToInt();
            int nameIndex = iter.nextU2ToInt();
            int descriptionIndex = iter.nextU2ToInt();
            
            Method method = new Method(classFile, accessFlag, nameIndex, descriptionIndex);
            
            int attributesCount = iter.nextU2ToInt();
            for (int j = 0; j < attributesCount; j++) {
                parseAttribute(iter, method, classFile);
            }
            
            methodList.add(method);
	    }
	    return methodList;
	}
	
	private void parseAttribute(ByteCodeIterator iter, Method method, ClassFile classFile) {
	    int nameIndex = iter.nextU2ToInt();
	    int length = iter.nextU4ToInt();
	    
	    if (AttributeInfo.CODE.equals(getUTF8ValueOfConstantPool(nameIndex, classFile))) {
	        CodeAttr codeAttr = parseCodeAttribute(iter, classFile);
	        method.setCodeAttr(codeAttr);
	    } else {
	        //TODO 目前没有处理除CODE外的其余属性
	        iter.nextUxToHexString(length);
	    }
	    
	}
	
	private CodeAttr parseCodeAttribute(ByteCodeIterator iter, ClassFile classFile) {
	    iter.back(6); // 因为之前验证类型时前进了6个字节，在此回退
	    
	    int nameIndex = iter.nextU2ToInt();
	    int length = iter.nextU4ToInt();
	    int maxStack = iter.nextU2ToInt();
	    int maxLocals = iter.nextU2ToInt();
	    int codeLength = iter.nextU4ToInt();
	    String code = iter.nextUxToHexString(codeLength);
	    
	    CodeAttr codeAttr = new CodeAttr(nameIndex, length, maxStack, maxLocals, codeLength, code);
	    
	    // TODO Code属性中的exception
	    int exceptionLength = iter.nextU2ToInt();
	    if (exceptionLength != 0) {
	        throw new RuntimeException("Code属性中有异常字段没有处理");
	    }
	    
	    // Code属性中的属性
        int attributesCount = iter.nextU2ToInt();
        for (int i = 0; i < attributesCount; i++) {
            int attrNameIndex = iter.nextU2ToInt();
            int attrLength = iter.nextU4ToInt();
            
            if (AttributeInfo.LINE_NUM_TABLE.equals(getUTF8ValueOfConstantPool(attrNameIndex, classFile))) {
                LineNumberTable lineNumberTable = new LineNumberTable(attrNameIndex, attrLength);
                codeAttr.setLineNumberTable(lineNumberTable);
                iter.nextUxToHexString(attrLength);
            } else if (AttributeInfo.LOCAL_VAR_TABLE.equals(getUTF8ValueOfConstantPool(attrNameIndex, classFile))) {
                LocalVariableTable localVariableTable = new LocalVariableTable(attrNameIndex, attrLength);
                codeAttr.setLocalVariableTable(localVariableTable);
                iter.nextUxToHexString(attrLength);
            } else {
                String msg = "此属性：" + getUTF8ValueOfConstantPool(attrNameIndex, classFile) + "还没有处理";
                throw new RuntimeException(msg);
            }
        }
	    
	    return codeAttr;
	}
	
	private void addMethods(ClassFile classFile, List<Method> methodList) {
	    for (Method method : methodList) {
	        classFile.addMethod(method);
	    }
	}
	
	private String getUTF8ValueOfConstantPool(int index, ClassFile classFile) {
	    ConstantPool constantPool = classFile.getConstantPool();
	    return constantPool.getUTF8String(index);
	}
	
}
