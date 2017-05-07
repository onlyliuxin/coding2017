
package week567_miniJVM.attr;

import week567_miniJVM.clz.ClassFile;
import week567_miniJVM.constant.ConstantPool;
import week567_miniJVM.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo {
	private int maxStack ;
	private int maxLocals ;
	private int codeLen ;
	private String code;
	public String getCode() {
		return code;
	}
	
	public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code /*ByteCodeCommand[] cmds*/) {
		super(attrNameIndex, attrLen,AttributeInfo.CODE);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
	}

	public LineNumberTable getLineNumberTable() {
		for(int i=0;i<attrs.size();i++){
			if(attrs.get(i).getAttrName().equals(AttributeInfo.LINE_NUM_TABLE)){
				return (LineNumberTable)(attrs.get(i));
			}
		}
        return null;
	}

	public LocalVariableTable getLocalVariableTable() {
		for(int i=0;i<attrs.size();i++){
			if(attrs.get(i).getAttrName().equals(AttributeInfo.LOCAL_VAR_TABLE)){
				return (LocalVariableTable)(attrs.get(i));
			}
		}
        return null;
	}
	public StackMapTable getStackMapTable() {
		for(int i=0;i<attrs.size();i++){
			if(attrs.get(i).getAttrName().equals(AttributeInfo.STACK_MAP_TABLE)){
				return (StackMapTable)(attrs.get(i));
			}
		}
        return null;
	}
}




