package jvm.classfile.attribute.parser.impl;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.item.impl.LocalVariableTableAttr;
import jvm.classfile.attribute.parser.AttributeInfoParser;
import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/4/13.
 * TODO:
 */
public class LocalVariableTableParser implements AttributeInfoParser {
    @Override
    public AttributeInfo parse(int attrNameIndex, int attrLen,
                               ByteCodeIterator iterator, ConstantPool constantPool) {
        int tableLen = iterator.nextU2ToInt();

        List<LocalVariableTableAttr.LocalVariableItem> items = new ArrayList<>();

        for (int i = 0; i < tableLen; ++i) {
            int startPC = iterator.nextU2ToInt();
            int length = iterator.nextU2ToInt();
            int nameIndex = iterator.nextU2ToInt();
            int descIndex = iterator.nextU2ToInt();
            int index = iterator.nextU2ToInt();
            LocalVariableTableAttr.LocalVariableItem item =
                    new LocalVariableTableAttr.LocalVariableItem(
                            startPC, length, nameIndex, descIndex, index);
            items.add(item);
        }
        return new LocalVariableTableAttr(attrNameIndex, attrLen ,items);
    }
}
