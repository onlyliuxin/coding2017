package com.coderising.jvm.method;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.UTF8Info;
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
				System.out.println("执行第： " + i + "次");
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

		public ByteCodeCommand[] getCmds() {
			return this.codeAttr.getCmds();
		}
		
		// 拿到方法的签名[ 也就是参数列表和返回值 ]
		private String getParamAndReturnType(){
			
			UTF8Info nameAndTypeInfo = (UTF8Info)this.getClzFile().getConstantPool()
					.getConstantInfo(this.getDescriptorIndex());
			return nameAndTypeInfo.getValue();
		}
		
		public List<String> getParameterList() {
			
			// e.g  (Ljava/util/List;Ljava/lang/String;II)V
			String paramAndType = getParamAndReturnType();
			
			int first = paramAndType.indexOf("(");
			int last = paramAndType.lastIndexOf(")");
			
			String param = paramAndType.substring(first+1, last);// 取出括号里面的参数描述符
			
			java.util.List<String> paramList = new ArrayList<String>();
			
			if( null == param || "".equals(param)){
				return paramList;
			}
			
			while( !param.equals("")){
				
				int pos = 0;
				// 这是一个对象类型
				if( param.charAt(pos) == 'L'){
					
					int end = param.indexOf(";");
					if(end == -1){
						throw new RuntimeException("can not find the ;");
					}
					paramList.add(param.substring(pos+1, end));
					pos = end + 1;
				}
			}
			
			
			
			return null;
		}
}
