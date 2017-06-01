package com.coding.jvm.method;

import java.util.ArrayList;
import java.util.List;

import com.coding.jvm.attr.AttributeInfo;
import com.coding.jvm.attr.CodeAttr;
import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.cmd.ByteCodeCommand;
import com.coding.jvm.loader.ByteCodeIterator;




public class Method {
	
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	private CodeAttr codeAttr;
	
	private ClassFile clzFile;
	
	
	public int getAccessFlag() {
		return accessFlag;
	}

	public ClassFile getClzFile() {
		return clzFile;
	}

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

	public Method(ClassFile clzFile,int accessFlag, int nameIndex, int descriptorIndex) {
		this.clzFile = clzFile;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
		Method method = new Method(clzFile, iter.nextU2ToInt(), iter.nextU2ToInt(), iter.nextU2ToInt());
		int attrCount = iter.nextU2ToInt();
		for (int i = 0; i < attrCount; i++) {
			String attrInfoName = clzFile.getConstantPool().getUTF8String(iter.readU2ToInt());
			switch (attrInfoName) {
			case AttributeInfo.CODE:
				method.setCodeAttr(CodeAttr.parse(clzFile, iter));
				break;
			default:
				AttributeInfo.parse(iter);
				break;
			}
		}
		return method;
	}

	public ByteCodeCommand[] getCmds() {		
		return this.getCodeAttr().getCmds();
	}
	
	public String getParamAndReturnType(){
		return this.clzFile.getConstantPool().getUTF8String(this.descriptorIndex);
	}
	// e.g. (Ljava/util/List;IF)V
	public List<String> getParamList(){
		String paramAndReturn = getParamAndReturnType();
		int first = paramAndReturn.indexOf('(');
		int last = paramAndReturn.indexOf(')');
		
		String param = paramAndReturn.substring(first+1,last);
		List<String> result = new ArrayList<String>();
		if(param==null||param.length()==0){
			return result;
		}
		while(!"".equals(param)){
			int pos = 0;
			char head = param.charAt(pos);
			switch (head) {
			case 'L':
				int end = param.indexOf(";");
				if(end==-1){
					throw new RuntimeException("can't find the ';' for a object type");
				}
				result.add(param.substring(pos+1,end));
				pos = end + 1;
				break;
			case 'I':
				result.add("I");
				pos++;
				break;
			case 'F':
				result.add("F");
				pos++;
				break;
			case 'C':
				result.add("C");
				pos++;
				break;
			case 'Z':
				result.add("Z");
				pos++;
				break;
			default:
				throw new RuntimeException("the param has unsupported type:" + param);
			}
			//使用该方式方便‘L’类型中获取边界‘;’的index
			param = param.substring(pos);
		}
		return result;
	}
	
	
}
