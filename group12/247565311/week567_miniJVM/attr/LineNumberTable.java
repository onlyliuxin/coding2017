
package week567_miniJVM.attr;

import java.util.ArrayList;
import java.util.List;

import week567_miniJVM.loader.ByteCodeIterator;

public class LineNumberTable extends AttributeInfo {
	List<LineNumberItem> items = new ArrayList<LineNumberItem>();
	
	private static class LineNumberItem{
		int startPC;
		int lineNum;
		public LineNumberItem(int startpc,int linenum){
			this.startPC = startpc;
			this.lineNum = linenum;
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
	public void addLineNumberItem(int startpc,int linenum){
		this.items.add(new LineNumberItem(startpc,linenum));
	}
	public LineNumberTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen,AttributeInfo.LINE_NUM_TABLE);
		
	}
}








