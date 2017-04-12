package attr;

import iterator.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IBM on 2017/4/12.
 */
public class LocalVariableTable extends AttributeInfo {
    List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LocalVariableTable parse(ByteCodeIterator iter) {

        return null;
    }

    private void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }

}
