package com.coderising.jvm.attr;


import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo {

    List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LocalVariableTable parse(ByteCodeIterator iter,int attribute_name_index) {
		int attribute_len = iter.nextU2ToInt();

		byte[] bytes = iter.getBytes(attribute_len);
		ByteCodeIterator lvtIter = new ByteCodeIterator(bytes);

		LocalVariableTable table = new LocalVariableTable(attribute_name_index,attribute_len);

		while(lvtIter.isNotEnd()){
		    LocalVariableItem item = new LocalVariableItem();
		    item.setStartPC(lvtIter.nextU2ToInt());
            item.setLength(lvtIter.nextU2ToInt());
            item.setNameIndex(lvtIter.nextU2ToInt());
            item.setDescIndex(lvtIter.nextU2ToInt());
            item.setIndex(lvtIter.nextU2ToInt());
            table.addLocalVariableItem(item);
        }


        return table;
    }

    private void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }


}
