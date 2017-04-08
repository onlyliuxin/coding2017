package minijvm.loader;

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

	
}
