package week6.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import week6.jvm.loader.ByteCodeIterator;

public class LineNumberTable extends AttributeInfo{

	List<LineNumberItem> items=new ArrayList<>();
	
	public LineNumberTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	
	}

	public static AttributeInfo parse(ByteCodeIterator iter) {
		
		int attrNameIndex=iter.nextU2ToInt();
		int attrLen=iter.nextU4ToInt();
		int lineNumberTableLen=iter.nextU2ToInt();
		
		LineNumberTable lineNumberTable=new LineNumberTable(attrNameIndex, attrLen);
		
		for(int i=1;i<=lineNumberTableLen;i++){
			LineNumberItem item=lineNumberTable.new LineNumberItem();
			
			int startPc=iter.nextU2ToInt();
			int lineNumber=iter.nextU2ToInt();
			
			item.setStartPc(startPc);
			item.setLineNumber(lineNumber);
			
			lineNumberTable.addLineNumberItem(item);
		}
		
		return lineNumberTable;
	}
	
	public void addLineNumberItem(LineNumberItem item){
		this.items.add(item);
	}
	
	@Override
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("lineNumberTable:\n");
		
		for(LineNumberItem item:items){
			buffer.append("startPc:"+item.getStartPc()+",");
			buffer.append("lineNumber:"+item.getLineNumber()+"\n");
		}
		buffer.append("\n");
		
		return buffer.toString();
	}
	
	private  class LineNumberItem{
		
		int startPc;//字节码行号
		int lineNumber;//java源码行号
		
		private int getStartPc() {
			return startPc;
		}
		private void setStartPc(int startPc) {
			this.startPc = startPc;
		}
		private int getLineNumber() {
			return lineNumber;
		}
		private void setLineNumber(int lineNumber) {
			this.lineNumber = lineNumber;
		}	
	}
}
