package main.coding_170416.jvm.attr;

import main.coding_170416.jvm.constant.ConstantPool;
import main.coding_170416.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2017/4/21.
 */
public class LocalVariableTable extends AttributeInfo {
    List<LocalVariableItem> items = new ArrayList<>();
    public LocalVariableTable(int attrNameIndex,int attrLen){
        super(attrNameIndex,attrLen);
    }
    public void addLocalVariableItem(LocalVariableItem item){
        this.items.add(item);
    }
    public static LocalVariableTable parse(ByteCodeIterator iterator){
        int index = iterator.nextU1ToInt();
        int len = iterator.nextU4ToInt();
        LocalVariableTable table = new LocalVariableTable(index,len);

        int itemLen = iterator.nextU2ToInt();
        for(int i=0;i<=itemLen;i++){
            LocalVariableItem item =new LocalVariableItem();
            item.setStartPC(iterator.nextU2ToInt());
            item.setLength(iterator.nextU2ToInt());
            item.setNameIndex(iterator.nextU2ToInt());
            item.setDescIndex(iterator.nextU2ToInt());
            item.setIndex(iterator.nextU2ToInt());
            table.addLocalVariableItem(item);
        }
        return table;
    }

    public String toString(ConstantPool pool){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Local variable table:\n");
        for(LocalVariableItem item:items){
            buffer.append("startPC:"+item.getStartPC()).append(",");
            buffer.append("name:"+pool.getUTF8String(item.getNameIndex())).append(",");
            buffer.append("desc:"+pool.getUTF8String(item.getDescIndex())).append(",");
            buffer.append("slotIndex:"+ item.getIndex()).append("\n");
        }
        buffer.append("\n");
        return buffer.toString();
    }
}
