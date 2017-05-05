package week567_miniJVM.attr;

import week567_miniJVM.clz.ClassFile;
import week567_miniJVM.constant.ConstantInfo;
import week567_miniJVM.constant.UTF8Info;
import week567_miniJVM.loader.ByteCodeIterator;
import week567_miniJVM.attr.LineNumberTable;

public class AttrFactory{
    private static AttrFactory instance = new AttrFactory();
    public static AttrFactory Instance(){
        return instance;
    }
    public AttributeInfo parse(ClassFile clzFile,ByteCodeIterator iter){
        int attrNameIndex = iter.nextU2ToInt();
        ConstantInfo info = clzFile.getConstantPool().getConstantInfo(attrNameIndex);
        if(info.getType()==1&&"Code".equals(((UTF8Info) info).getValue())){
        	int attrLen = iter.nextU4ToInt();
        	int maxStack = iter.nextU2ToInt();
        	int maxLocal = iter.nextU2ToInt();
        	int codeLen = iter.nextU4ToInt();
        	String code = iter.nextUxToHexString(codeLen);
        	CodeAttr codeattr = new CodeAttr(attrNameIndex,attrLen,maxStack,maxLocal,codeLen,code);
        	
        	int exceptLen = iter.nextU2ToInt(); // 异常长度
        	while(exceptLen>0){
        		exceptLen -= 1;
        		int startPc = iter.nextU2ToInt();
        		int endPc = iter.nextU2ToInt();
        		int handlerPc = iter.nextU2ToInt();
        		int catchType = iter.nextU2ToInt();
        		// TODO
        	}
        	int attributeLen = iter.nextU2ToInt(); // 属性下还有属性的个数
        	while(attributeLen>0){
        		attributeLen -= 1;
        		AttributeInfo chattr = AttrFactory.Instance().parse(clzFile, iter);
        		codeattr.addAttr(chattr);
        	}
        	return codeattr;
        }else if(info.getType()==1&&"LineNumberTable".equals(((UTF8Info) info).getValue())){
        	int attrLen = iter.nextU4ToInt();
        	int lineNum = iter.nextU2ToInt();
        	LineNumberTable linetable = new LineNumberTable(attrNameIndex,lineNum);
        	while(lineNum>0){
        		lineNum -= 1;
        		int startPc = iter.nextU2ToInt();
        		int lineNumber = iter.nextU2ToInt();
        		linetable.addLineNumberItem(startPc,lineNumber);
        	}
        	return linetable;
        }else if(info.getType()==1&&"LocalVariableTable".equals(((UTF8Info) info).getValue())){
        	int attrLen = iter.nextU4ToInt();
        	int localVarNum = iter.nextU2ToInt();
        	LocalVariableTable lvartable = new LocalVariableTable(attrNameIndex,localVarNum);
        	while(localVarNum>0){
        		localVarNum -= 1;
        		int startPc = iter.nextU2ToInt();
        		int length = iter.nextU2ToInt();
        		int nameIndex = iter.nextU2ToInt();
        		int descIndex = iter.nextU2ToInt();
        		int index = iter.nextU2ToInt();
        		LocalVariableItem item = new LocalVariableItem(startPc,length,nameIndex,descIndex,index);
        	    lvartable.addLocalVariableItem(item);
        	}
        	return lvartable;
        }else if(info.getType()==1&&"StackMapTable".equals(((UTF8Info) info).getValue())){
        	int attrLen = iter.nextU4ToInt();
        	int entryNum = iter.nextU2ToInt();
        	StackMapTable stacktable = new StackMapTable(attrNameIndex,entryNum);
        	stacktable.parse(iter);
        	//while(entryNum>0){
        	//	entryNum -= 1;
        		
        	//}
        	return stacktable;
        }
    	return null;
    }
}