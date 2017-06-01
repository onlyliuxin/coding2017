package me.lzb.jvm.engine;

import java.util.HashMap;
import java.util.Map;

/**
 * 自己定义一个Object代替java的Object
 * @author LZB
 */
public class JavaObject {
    public static final int OBJECT = 1;
    public static final int STRING = 2;
    public static final int INT = 3;
    public static final int FLOAT = 4;

    int type;
    private String className;

    private Map<String, JavaObject> fieldValues = new HashMap<String, JavaObject>();

    private String stringValue;

    private int intValue;

    private float floatValue;

    public void setFieldValue(String fieldName, JavaObject fieldValue) {
        fieldValues.put(fieldName, fieldValue);
    }

    public JavaObject(int type) {
        this.type = type;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStringValue(String value) {
        stringValue = value;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public void setIntValue(int value) {
        this.intValue = value;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public int getType() {
        return type;
    }

    public JavaObject getFieldValue(String fieldName) {
        return this.fieldValues.get(fieldName);
    }

    public String toString() {
        switch (this.getType()) {
            case INT:
                return String.valueOf(this.intValue);
            case STRING:
                return this.stringValue;
            case OBJECT:
                return this.className + ":" + this.fieldValues;
            case FLOAT:
                return String.valueOf(this.floatValue);
            default:
                return null;
        }
    }

    public String getClassName() {
        return this.className;
    }

    public void setFloatValue(float value) {
        this.floatValue = value;
    }

}
