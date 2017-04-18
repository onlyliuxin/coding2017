package com.coderising.jvm.attr;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xudanxia on 2017/4/11.
 */
public class LocalVariableTable extends AttributeInfo {

    List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LocalVariableTable parse(ByteCodeIterator iter) {

        int attrIndex = iter.nextU2toInt();
        int attrLen = iter.nextU4ToInt();

        LocalVariableTable localVariableTable = new LocalVariableTable(attrIndex, attrLen);

        int tableLen = iter.nextU2toInt();
        for (int i = 0; i < tableLen; i++) {
            LocalVariableItem localVariableItem = new LocalVariableItem();
            localVariableItem.setStartPC(iter.nextU2toInt());
            localVariableItem.setLength(iter.nextU2toInt());
            localVariableItem.setNameIndex(iter.nextU2toInt());
            localVariableItem.setDescIndex(iter.nextU2toInt());
            localVariableItem.setIndex(iter.nextU2toInt());
            localVariableTable.addLocalVariableItem(localVariableItem);
        }

        return localVariableTable;
    }

    private void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }

    public String toString(ConstantPool pool) {

        StringBuilder buffer = new StringBuilder();
        buffer.append("Local Variable Table:\n");
        for (LocalVariableItem item : items) {
            buffer.append("startPC:" + item.getStartPC()).append(",");
            buffer.append("name:" + pool.getUTF8String(item.getNameIndex())).append(",");
            buffer.append("desc:" + pool.getUTF8String(item.getDescIndex())).append(",");
            buffer.append("slotIndex:" + item.getIndex()).append("\n");
        }
        buffer.append("\n");
        return buffer.toString();
    }

}