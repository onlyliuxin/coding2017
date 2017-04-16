package jvm.classfile.constant.parser;

import jvm.classfile.constant.parser.impl.*;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ConstantParserFactory {
    private static final int CONSTANT_CLASS = 7;
    private static final int CONSTANT_FIELD_REF = 9;
    private static final int CONSTANT_METHOD_REF = 10;
    private static final int CONSTANT_INTERFACE_METHOD_REF = 11;
    private static final int CONSTANT_STRING = 8;
    private static final int CONSTANT_INTEGER = 3;
    private static final int CONSTANT_FLOAT = 4;
    private static final int CONSTANT_LONG = 5;
    private static final int CONSTANT_DOUBLE = 6;
    private static final int CONSTANT_NAME_AND_TYPE = 12;
    private static final int CONSTANT_UTF8 = 1;
    private static final int CONSTANT_METHOD_HANDLE = 15;
    private static final int CONSTANT_METHOD_TYPE = 16;
    private static final int CONSTANT_INVOKE_DYNAMIC = 18;

    public static ConstantParser get(int type) {
        switch (type) {
            case CONSTANT_CLASS:
                return new ClassInfoParser();
            case CONSTANT_FIELD_REF:
                return new FieldRefInfoParser();
            case CONSTANT_METHOD_REF:
                return new MethodRefInfoParser();
            case CONSTANT_INTERFACE_METHOD_REF:
                return new InterfaceMethodRefInfoParser();
            case CONSTANT_STRING:
                return new StringInfoParser();
            case CONSTANT_INTEGER:
                return new IntegerInfoParser();
            case CONSTANT_FLOAT:
                return new FloatInfoParser();
            case CONSTANT_LONG:
                return new LongInfoParser();
            case CONSTANT_DOUBLE:
                return new DoubleInfoParser();
            case CONSTANT_NAME_AND_TYPE:
                return new NameAndTypeInfoParser();
            case CONSTANT_UTF8:
                return new UTF8InfoParser();
            case CONSTANT_METHOD_HANDLE:
                return new MethodHandleInfoParser();
            case CONSTANT_METHOD_TYPE:
                return new MethodTypeInfoParser();
            case CONSTANT_INVOKE_DYNAMIC:
                return new InvokeDynamicInfoParser();
            default:
                return null;
        }
    }
}
