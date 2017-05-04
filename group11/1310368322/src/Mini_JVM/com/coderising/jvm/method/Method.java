package com.coderising.jvm.method;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.loader.ByteCodeIterator;

public class Method {
	
		private int accessFlag;
		private int nameIndex;
		private int descriptorIndex;
		
		private CodeAttr codeAttr;
		
		private ClassFile clzFile;
	
		public int getNameIndex() {
			return nameIndex;
		}
		
		public int getDescriptorIndex() {
			return descriptorIndex;
		}
		
		public CodeAttr getCodeAttr() {
			return codeAttr;
		}
	
		public void setCodeAttr(CodeAttr code) {
			this.codeAttr = code;
		}
	
		public ClassFile getClzFile() {
			return clzFile;
		}
		
		public Method(ClassFile clzFile,int accessFlag, int nameIndex, int descriptorIndex) {
			this.clzFile = clzFile;
			this.accessFlag = accessFlag;
			this.nameIndex = nameIndex;
			this.descriptorIndex = descriptorIndex;
		}
	
		
		public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
			int accessFlag = iter.nextU2toInt();
			int nameIndex = iter.nextU2toInt();
			int descriptor = iter.nextU2toInt();
			int attrCount = iter.nextU2toInt();
			
			
			Method method = new Method(clzFile,accessFlag,nameIndex,descriptor);
			System.out.println("attrCount: " + attrCount);
			// 解析method中的属性
			for(int i = 0; i < attrCount; i++){
				int attrNameIndex  = iter.nextU2toInt();
				String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
				System.out.println(attrName);
				if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){
					CodeAttr attrCode = CodeAttr.parse(clzFile, iter);
					method.setCodeAttr(attrCode);
				}
				
			}
			return method;
		}
}
