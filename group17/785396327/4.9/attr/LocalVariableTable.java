package attr;

import iterator.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 2017/4/12.
 */
public class LocalVariableTable extends AttributeInfo {
    List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LocalVariableTable parse(ByteCodeIterator iter, int attributeNameIndex) {
        int attributeLength = iter.nextU4ToInt();
        int localVariableTableLength = iter.nextU2ToInt();
        LocalVariableTable localVariableTable = new LocalVariableTable(attributeNameIndex, attributeLength);
        for (int i = 0; i < localVariableTableLength; i++) {
            int startPc = iter.nextU2ToInt();
            int length = iter.nextU2ToInt();
            int nameIndex = iter.nextU2ToInt();
            int descIndex = iter.nextU2ToInt();
            int index = iter.nextU2ToInt();
            LocalVariableItem localVariableItem = new LocalVariableItem();
            localVariableItem.setStartPC(startPc);
            localVariableItem.setLength(length);
            localVariableItem.setNameIndex(nameIndex);
            localVariableItem.setDescIndex(descIndex);
            localVariableItem.setIndex(index);
            localVariableTable.addLocalVariableItem(localVariableItem);
        }
        return localVariableTable;
    }

    private void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }

}
