package com.coderising.jvm.attr;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xyy
 * @create 2017-04-14 17:36
 **/
public class LocalVariableTable extends AttributeInfo{

    List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }


    private void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }

    public static LocalVariableTable parse(ByteCodeIterator iter){

        int index = iter.nextU2ToInt();
        int len = iter.nextU4ToInt();

        LocalVariableTable table = new LocalVariableTable(index,len);

        int itemLen = iter.nextU2ToInt();

        for(int i=1; i<=itemLen; i++){
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


    public String toString(ConstantPool pool){
        StringBuilder buffer = new StringBuilder();
        buffer.append("Local Variable Table:\n");
        for(LocalVariableItem item : items){
            buffer.append("startPC:"+item.getStartPC()).append(",");
            buffer.append("name:"+pool.getUTF8String(item.getNameIndex())).append(",");
            buffer.append("desc:"+pool.getUTF8String(item.getDescIndex())).append(",");
            buffer.append("slotIndex:"+ item.getIndex()).append("\n");
        }
        buffer.append("\n");
        return buffer.toString();
    }
}
