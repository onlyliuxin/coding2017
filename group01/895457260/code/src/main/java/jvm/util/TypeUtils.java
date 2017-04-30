package jvm.util;

import jvm.classfile.field.Field;
import jvm.engine.Heap;
import jvm.engine.JavaObject;

/**
 * Created by Haochen on 2017/4/30.
 * TODO:
 */
public class TypeUtils {
    private static final String INT = "I";
    private static final String FLOAT = "F";
    private static final String LONG = "J";
    private static final String DOUBLE = "D";
    private static final String CHAR = "C";
    private static final String BYTE = "B";
    private static final String BOOLEAN = "Z";
    private static final String SHORT = "S";

    private static final String REFERENCE = "ref";

    public static String parse(String inClassFile) {
        switch (inClassFile) {
            case INT:
                return "int";
            case FLOAT:
                return "float";
            case LONG:
                return "long";
            case DOUBLE:
                return "double";
            case CHAR:
                return "char";
            case BYTE:
                return "byte";
            case BOOLEAN:
                return "boolean";
            case SHORT:
                return "short";
        }
        return REFERENCE;
    }

    public static JavaObject getDefaultValue(Field field) {
        switch (field.getDescriptor()) {
            case INT:
                return Heap.getInstance().newInt(0);
            case FLOAT:
                return Heap.getInstance().newFloat(0);
        }
        return null;
    }
}
