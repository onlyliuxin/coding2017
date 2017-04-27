
package week567_miniJVM.attr;


import week567_miniJVM.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo{
	
	private String originalCode;

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}

	public static StackMapTable parse(ByteCodeIterator iter){
		int index = iter.nextU2ToInt();
		int len = iter.nextU4ToInt();
		StackMapTable t = new StackMapTable(index,len);
		
		//鍚庨潰鐨凷tackMapTable澶繃澶嶆潅锛�涓嶅啀澶勭悊锛�鍙妸鍘熷鐨勪唬鐮佽杩涙潵淇濆瓨
		String code = iter.nextUxToHexString(len);
		t.setOriginalCode(code);
		
		return t;
	}

	private void setOriginalCode(String code) {
		this.originalCode = code;
		
	}
}













