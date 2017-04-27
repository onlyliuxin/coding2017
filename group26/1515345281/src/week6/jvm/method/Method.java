package week6.jvm.method;


import week6.jvm.attr.AttributeInfo;
import week6.jvm.attr.CodeAttr;
import week6.jvm.clz.ClassFile;
import week6.jvm.constant.ConstantPool;
import week6.jvm.loader.ByteCodeIterator;

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
