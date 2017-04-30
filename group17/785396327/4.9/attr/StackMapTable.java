package attr;

import iterator.ByteCodeIterator;

/**
 * Created by william on 2017/4/12.
 */
public class StackMapTable extends AttributeInfo {
    private String originalCode;

    public StackMapTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static StackMapTable parse(ByteCodeIterator iter,int index){
//        int len = iter.nextU4ToInt();
//        StackMapTable t = new StackMapTable(index,len);

        //后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
//        String code = iter.nextUxToHexString(len);
//        t.setOriginalCode(code);

//        return t;
        return null;
    }

    private void setOriginalCode(String code) {
        this.originalCode = code;

    }
}
