package assignment0326.jvm.attr;

import assignment0326.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;




public class LineNumberTable extends AttributeInfo {
	List<LineNumberItem> items = new ArrayList<LineNumberItem>();
	
	private static class LineNumberItem{
		int startPC;
		int lineNum;
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
        int attrNameIndex = iter.nextU2ToInt();
        int attrLength = iter.nextU4ToInt();
        int lineNumTableLength = iter.nextU2ToInt();
        LineNumberTable lineNumberTable = new LineNumberTable(attrNameIndex, attrLength);
        for (int i = 0; i < lineNumTableLength; i++) {
            LineNumberItem item = new LineNumberItem();
			item.setStartPC(iter.nextU2ToInt());
			item.setLineNum(iter.nextU2ToInt());
            lineNumberTable.addLineNumberItem(item);
        }
        return lineNumberTable;
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
