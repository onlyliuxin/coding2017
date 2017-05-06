

package week567_miniJVM.attr;


import java.util.ArrayList;
import java.util.List;

import week567_miniJVM.constant.ConstantPool;

import week567_miniJVM.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen,AttributeInfo.LOCAL_VAR_TABLE);		
	}
	public static LocalVariableTable parse(ByteCodeIterator iter){
		
		return null;
	}
	public void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}

	
	
}







