package miniJVM.attr;

import miniJVM.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;


public class LineNumberTable extends AttributeInfo {
	List<LineNumberItem> items = new ArrayList<LineNumberItem>();
	
	private static class LineNumberItem{
		int startPC;
		int lineNum;
		public LineNumberItem(int startPC, int lineNum){
			this.startPC = startPC;
			this.lineNum = lineNum;
		}

		public int getStartPC() {
			return startPC;
		}
		public void setStartPC(int startPC) {
			this.startPC = startPC;
		}
		public int getLineNum() {
			return lineNum;
		}
		public void setLineNum(int lineNum) {
			this.lineNum = lineNum;
		}
	}
	public void addLineNumberItem(LineNumberItem item){
		this.items.add(item);
	}
	public LineNumberTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
		
	}
	
	public static LineNumberTable parse(ByteCodeIterator iter){
		int attributeNameIndex = iter.nextU2ToInt();
		int attributeLength = iter.nextU4ToInt();
		int lineNumberTableLength = iter.nextU2ToInt();
		LineNumberTable table = new LineNumberTable(attributeNameIndex, attributeLength);
		for(int i = 0; i < lineNumberTableLength; i ++){
			int startPC = iter.nextU2ToInt();
			int lineNum = iter.nextU2ToInt();
			LineNumberItem item = new LineNumberItem(startPC, lineNum);
			table.addLineNumberItem(item);
		}
		return table;
	}
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("Line Number Table:\n");
		for(LineNumberItem item : items){
			buffer.append("startPC:"+item.getStartPC()).append(",");
			buffer.append("lineNum:"+item.getLineNum()).append("\n");
		}
		buffer.append("\n");
		return buffer.toString();
		
	}
	
	

}
