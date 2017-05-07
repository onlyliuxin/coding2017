package jvm.classfile.attribute.parser.impl;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.item.impl.LineNumberTableAttr;
import jvm.classfile.attribute.parser.AttributeInfoParser;
import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/4/13.
 * TODO:
 */
public class LineNumberTableParser implements AttributeInfoParser {
    @Override
    public AttributeInfo parse(int attrNameIndex, int attrLen,
                               ByteCodeIterator iterator, ConstantPool constantPool) {
        int tableLength = iterator.nextU2ToInt();

        List<LineNumberTableAttr.LineNumberItem> items = new ArrayList<>();

        for (int i = 0; i < tableLength; ++i) {
            int startPC = iterator.nextU2ToInt();
            int lineNumber = iterator.nextU2ToInt();
            LineNumberTableAttr.LineNumberItem item =
                    new LineNumberTableAttr.LineNumberItem(startPC, lineNumber);
            items.add(item);
        }
        return new LineNumberTableAttr(attrNameIndex, attrLen, items);
    }
}
