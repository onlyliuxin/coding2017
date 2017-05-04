package com.coderising.jvm.method;

import com.coderising.jvm.attribute.AttributeInfo;
import com.coderising.jvm.attribute.CodeAttr;
import com.coderising.jvm.clasfile.ClassFile;
import com.coderising.jvm.constant.Utf8Info;
import com.coderising.jvm.loader.ByteCodeIterator;

public class JMethod {
	
	private int access_flags;
	private int name_index;
	private int descriptor_index;
	private ClassFile clzFile;
	
	private CodeAttr codeAttr;

	public int getAccess_flags() {
		return access_flags;
	}
	
	public int getName_index() {
		return name_index;
	}

	public int getDescriptor_index() {
		return descriptor_index;
	}

	public ClassFile getClzFile() {
		return clzFile;
	}	

	
	public CodeAttr getCodeAttr() {
		return codeAttr;
	}

	public void setCodeAttr(CodeAttr codeAttr) {
		this.codeAttr = codeAttr;
	}

	public JMethod(ClassFile clzFile, int access_flags, int name_index, int descriptor_index) {
		this.access_flags = access_flags;
		this.name_index = name_index;
		this.descriptor_index = descriptor_index;
	}

	public static JMethod parse(ClassFile classFile, ByteCodeIterator iterator) {
		
		int accessFlag = iterator.next2BytesToInt();
		int nameIndex = iterator.next2BytesToInt();
		int descripIndex = iterator.next2BytesToInt();
		JMethod jMethod = new JMethod(classFile, accessFlag, nameIndex, descripIndex);
		
		int attribute_count = iterator.next2BytesToInt();
		if (attribute_count > 0) {
			for (int i = 0; i < attribute_count; i++) {
				
				int attrIndex = iterator.next2BytesToInt();
				String attrName = classFile.getPool().getUtf8String(attrIndex);
				
				if (AttributeInfo.CODE.equals(attrName)) {
					CodeAttr codeAttr = CodeAttr.parse(classFile,iterator);
					jMethod.setCodeAttr(codeAttr);
				}
				else {
					throw new RuntimeException("Attribute has not been added." + attrName);
				}
			}
		}
		
		
		
		
		
		
		return jMethod;
	}

}
