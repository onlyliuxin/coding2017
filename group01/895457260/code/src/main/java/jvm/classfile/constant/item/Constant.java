package jvm.classfile.constant.item;

import java.util.Map;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public interface Constant {
    int PRINT_TYPE = 1;
    int PRINT_PARAM = 2;
    int PRINT_COMMENT = 3;

    int size();
    Map<Integer, String> printableMap();
}
