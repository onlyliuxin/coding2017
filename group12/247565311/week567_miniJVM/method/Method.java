package week567_miniJVM.method;
import week567_miniJVM.clz.ClassFile;
import week567_miniJVM.constant.ConstantInfo;
import week567_miniJVM.constant.UTF8Info;
import week567_miniJVM.loader.ByteCodeIterator;
import week567_miniJVM.attr.AttrFactory;
import week567_miniJVM.attr.AttributeInfo;
import week567_miniJVM.attr.CodeAttr;

public class Method {
	private int accessFlag,nameIndex,descIndex;
	private CodeAttr codeAttr;
	private ClassFile clzFile;
	
	public ClassFile getClzFile() {
		return clzFile;
	}
    public int getAccessFlag(){
    	return accessFlag;
    }
	public int getNameIndex() {
		return nameIndex;
	}
	public int getDescriptorIndex() {
		return descIndex;
	}
	public CodeAttr getCodeAttr() {
		return codeAttr;
	}
	public void setCodeAttr(CodeAttr code) {
		this.codeAttr = code;
	}
	public Method(ClassFile clzFile,int accessFlag, int nameIndex, int descIndex) {
		this.clzFile = clzFile;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descIndex = descIndex;
	}
	
	public void parse(ClassFile clzFile, ByteCodeIterator iter){
        int attributeNum = iter.nextU2ToInt();
        while(attributeNum>0){
        	attributeNum -= 1;
        	AttributeInfo attrinfo = AttrFactory.Instance().parse(clzFile, iter);
        	if(attrinfo == null) continue;

            
        }
	}
}

