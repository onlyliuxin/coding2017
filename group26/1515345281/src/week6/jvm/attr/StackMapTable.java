package week6.jvm.attr;

import week6.jvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo{
	
	private String originalCode;

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}

	
	public static AttributeInfo parse(ByteCodeIterator iter) {
		
		int attrNameIndex=iter.nextU2ToInt();
		int attrLen=iter.nextU2ToInt();
		
		//后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
		String originalCode=iter.nextUxToString(attrLen);
		
		StackMapTable table=new StackMapTable(attrNameIndex, attrLen);
		table.setOriginalCode(originalCode);
		
		return table;
	}
	
	
	public String getOriginalCode() {
		return originalCode;
	}

	private void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}

}
