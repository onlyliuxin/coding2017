package week8.jvm.method;


import java.util.ArrayList;
import java.util.List;

import week8.jvm.attr.AttributeInfo;
import week8.jvm.attr.CodeAttr;
import week8.jvm.clz.ClassFile;
import week8.jvm.cmd.ByteCodeCommand;
import week8.jvm.constant.ConstantPool;
import week8.jvm.constant.UTF8Info;
import week8.jvm.loader.ByteCodeIterator;

public class Method {

	private int accessFlags;
	private int nameIndex;
	private int descIndex;	
	private AttributeInfo attrInfo;
	
	private ClassFile clzFile;
	
	public Method(int accessFlags, int nameIndex, int descIndex,ClassFile clzFile) {
		this.accessFlags=accessFlags;
		this.nameIndex=nameIndex;
		this.descIndex=descIndex;
		this.clzFile=clzFile;
	}

	@Override
	public String toString(){
		String name=clzFile.getConstantPool().getUTF8String(nameIndex);
		String desc=clzFile.getConstantPool().getUTF8String(descIndex);
		
		return name+":"+desc;
	}
	
	public static Method parseMethod(ClassFile clzFile,
			ByteCodeIterator iter) {
		int accessFlags=iter.nextU2ToInt();
		int nameIndex=iter.nextU2ToInt();
		int descIndex=iter.nextU2ToInt();
		int attributeCount=iter.nextU2ToInt();
		
		Method method=new Method(accessFlags,nameIndex,descIndex,clzFile);
		
		for(int i=1;i<=attributeCount;i++){
			String attrName=clzFile.getConstantPool().getUTF8String(iter.nextU2ToInt());
			iter.back(2);
			
			if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){//解析Code属性
				
				AttributeInfo info=CodeAttr.parseCodeAttr(clzFile,iter);
				method.setAttrInfo(info);
			
			}else{
				throw new RuntimeException("only CODE attribute is implemented , please implement the "+ attrName);
			}
		}
		
		return method;
	}
	
	/**
	 * 获取方法参数列表，在执行引擎中调用使用
	 * @return
	 */
	public List<String> getParameterList() {
		
		// e.g. (Ljava/util/List;Ljava/lang/String;II)V
		String paramAndType=getParamAndReturnType();
		
		int first=paramAndType.indexOf("(");
		int last=paramAndType.indexOf(")");
		
		String param=paramAndType.substring(first+1,last);
		
		List<String> params=new ArrayList<>();
		
		if(null == param || "" == param){
			return params;
		}

		
		while(!param.equals("")){
			
			int pos=0;
			char ch=param.charAt(pos);
			
			if(ch == 'L'){
				int end=param.indexOf(";");
				
				if(end == -1){
					throw new RuntimeException("can't find the ; for a object type");
				}
				
				params.add(param.substring(pos,end));
				pos=end+1;
				
			}else if(ch == 'I'){
				params.add("I");
				pos++;
				
			}else if(ch == 'F'){
			    params.add("F");
				pos++;
			}else{
				throw new RuntimeException("the param has unsupported type:" + param);
			}
			
			param=param.substring(pos);
		}
		
		return params;
	}
	
	private String getParamAndReturnType() {
		UTF8Info nameAndTypeInfo=(UTF8Info) this.getClzFile().getConstantPool().getConstantInfo(this.getDescIndex());
		
		return nameAndTypeInfo.getValue();
	}

	public ByteCodeCommand[] getCmds(){
		return ((CodeAttr)this.getAttrInfo()).getCmds();
	}

	public AttributeInfo getAttrInfo() {
		return attrInfo;
	}

	public void setAttrInfo(AttributeInfo attrInfo) {
		this.attrInfo = attrInfo;
	}

	public ClassFile getClzFile() {
		return clzFile;
	}

	public void setClzFile(ClassFile clzFile) {
		this.clzFile = clzFile;
	}

	public int getAccessFlags() {
		return accessFlags;
	}
	
	public void setAccessFlags(int accessFlags) {
		this.accessFlags = accessFlags;
	}
	
	public int getNameIndex() {
		return nameIndex;
	}
	
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	
	public int getDescIndex() {
		return descIndex;
	}
	
	public void setDescIndex(int descIndex) {
		this.descIndex = descIndex;
	}

}
