package com.github.HarryHook.coding2017.jvm.attr;

import java.util.ArrayList;
import java.util.List;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.loader.ByteCodeIterator;

import sun.util.logging.resources.logging;

public class LocalVariableTable extends AttributeInfo {

    List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
	super(attrNameIndex, attrLen);
    }

    public static LocalVariableTable parse(ByteCodeIterator iter) {
	int attrNameIndex = iter.nextU2ToInt();
	int attrlength = iter.nextU4ToInt();

	LocalVariableTable table = new LocalVariableTable(attrNameIndex, attrlength);
	int itemLength = iter.nextU2ToInt();
	for (int i = 1; i <= itemLength; i++) {
	    LocalVariableItem item = new LocalVariableItem();
	    item.setStartPC(iter.nextU2ToInt());
	    item.setLength(iter.nextU2ToInt());
	    item.setNameIndex(iter.nextU2ToInt());
	    item.setDescIndex(iter.nextU2ToInt());
	    item.setIndex(iter.nextU2ToInt());
	    table.addLocalVariableItem(item);
	}
	return table;
    }

    private void addLocalVariableItem(LocalVariableItem item) {
	this.items.add(item);
    }

}