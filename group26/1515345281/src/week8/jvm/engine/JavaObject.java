package week8.jvm.engine;

import java.util.HashMap;
import java.util.Map;

public class JavaObject {
	public static final int OBJECT=1;
	public static final int STRING=2;
	public static final int INT=3;
	public static final int FLOAT=4;
	
	private int type;
	
	private String className;
	private Map<String,JavaObject> fieldValues=new HashMap<>();
	
	private String stringValue;
	private int intValue;
	private float floatValue;
	
	
	public JavaObject(){};
	
	public JavaObject(int type){
		this.type=type;
	}

	@Override
	public String toString(){
		switch(getType()){
		case INT:
			return String.valueOf(this.intValue);
		case FLOAT:
			return String.valueOf(this.floatValue);
		case STRING:
			return this.stringValue;
		case OBJECT:
			return this.className+":"+this.fieldValues;
		default:
			return null;
		}
	}
	
	public JavaObject getFieldValues(String fieldName) {
		return this.fieldValues.get(fieldName);
	}

	public void setFieldValues(String fieldName,JavaObject fieldValue) {
		this.fieldValues.put(fieldName, fieldValue);
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}
	
	
}
