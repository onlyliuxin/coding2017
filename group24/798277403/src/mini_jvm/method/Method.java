package mini_jvm.method;


import mini_jvm.attr.AttributeInfo;
import mini_jvm.attr.CodeAttr;
import mini_jvm.clz.ClassFile;
import mini_jvm.cmd.ByteCodeCommand;
import mini_jvm.constant.ConstantPool;
import mini_jvm.constant.UTF8Info;
import mini_jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;


public class Method {
	private int accessFlag; //方法的访问标志
	private int nameIndex; //名称索引
	private int descriptorIndex; //描述符索引

	private CodeAttr codeAttr; //属性表集合

	private ClassFile clzFile;


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

	public String toString() {

		ConstantPool pool = this.clzFile.getConstantPool();
		StringBuilder buffer = new StringBuilder();

		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();

		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();

		buffer.append(name).append(":").append(desc).append("\n");

		buffer.append(this.codeAttr.toString(pool));

		return buffer.toString();
	}

	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descIndex = iter.nextU2ToInt();
		int attribCount = iter.nextU2ToInt();
		Method m = new Method(clzFile, accessFlag, nameIndex, descIndex);
		for( int j=1; j<= attribCount; j++){

			int attrNameIndex = iter.nextU2ToInt();
			String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
			iter.back(2);

			if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
				m.setCodeAttr(codeAttr);
			} else{
				throw new RuntimeException("only CODE attribute is implemented , please implement the "+ attrName);
			}

		}
		return m;
	}

	public ByteCodeCommand[] getCmds() {
		return this.getCodeAttr().getCmds();
	}

	private String getParamAndReturnType(){
		UTF8Info  nameAndTypeInfo = (UTF8Info)this.getClzFile()
				.getConstantPool().getConstantInfo(this.getDescriptorIndex());
		return nameAndTypeInfo.getValue();
	}
	public List<String> getParameterList(){

		// e.g. (Ljava/util/List;Ljava/lang/String;II)V
		String paramAndType = getParamAndReturnType();

		int first = paramAndType.indexOf("(");
		int last = paramAndType.lastIndexOf(")");
		// e.g. Ljava/util/List;Ljava/lang/String;II
		String param = paramAndType.substring(first+1, last);

		List<String> paramList = new ArrayList<String>();

		if((null == param) || "".equals(param)){
			return paramList;
		}

		while(!param.equals("")){

			int pos = 0;
			// 这是一个对象类型
			if(param.charAt(pos) == 'L'){

				int end = param.indexOf(";");

				if(end == -1){
					throw new RuntimeException("can't find the ; for a object type");
				}
				paramList.add(param.substring(pos+1,end));

				pos = end + 1;

			}
			else if(param.charAt(pos) == 'I'){
				// int
				paramList.add("I");
				pos ++;

			}
			else if(param.charAt(pos) == 'F'){
				// float
				paramList.add("F");
				pos ++;

			} else{
				throw new RuntimeException("the param has unsupported type:" + param);
			}

			param = param.substring(pos);

		}
		return paramList;

	}
}
