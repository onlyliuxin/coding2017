package com.github.wdn.coding2017.jvm.attr;

import com.github.wdn.coding2017.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public class LocalVariableTable extends AttributeInfo{
    List<LocalVariableTableItem> localVariableTableItems = new ArrayList<>();
    public LocalVariableTable(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }
    private static class LocalVariableTableItem{
        int startPC;
        int length;
        int nameIndex;
        int descriptorIndex;
        int index;

        public int getStartPC() {
            return startPC;
        }

        public void setStartPC(int startPC) {
            this.startPC = startPC;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getNameIndex() {
            return nameIndex;
        }

        public void setNameIndex(int nameIndex) {
            this.nameIndex = nameIndex;
        }

        public int getDescriptorIndex() {
            return descriptorIndex;
        }

        public void setDescriptorIndex(int descriptorIndex) {
            this.descriptorIndex = descriptorIndex;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
    private void addLocalVariableItem(LocalVariableTableItem item){
        this.localVariableTableItems.add(item);
    }
    public static LocalVariableTable parse(ByteCodeIterator iter) {
        LocalVariableTable localVariableTable = new LocalVariableTable(iter.readU2ToInt(),iter.readU2ToInt());
        int LocalVariableTableCount = iter.readU2ToInt();
        for (int l = 0; l < LocalVariableTableCount; l++) {
            LocalVariableTableItem item = new LocalVariableTableItem();
            item.setStartPC(iter.readU2ToInt());
            item.setLength(iter.readU2ToInt());
            item.setNameIndex(iter.readU2ToInt());
            item.setDescriptorIndex(iter.readU2ToInt());
            item.setIndex(iter.readU2ToInt());
            localVariableTable.addLocalVariableItem(item);
        }
        return localVariableTable;
    }
}
