package week8.jvm.engine;

public class Heap {

	private static Heap heap=new Heap();
	
	private Heap(){};
	
	public static Heap getInstance(){
		return heap;
	}
	
	public JavaObject newObject(String className){
		JavaObject jo=new JavaObject(JavaObject.OBJECT);
		jo.setClassName(className);
		return jo;
	}
	
	public JavaObject newInt(int intValue){
		JavaObject jo=new JavaObject(JavaObject.INT);
		jo.setIntValue(intValue);
		return jo;
	}
	
	public JavaObject newFloat(float floatValue){
		JavaObject jo=new JavaObject(JavaObject.FLOAT);
		jo.setFloatValue(floatValue);
		return jo;
	}
	
	public JavaObject newString(String stringValue){
		JavaObject jo=new JavaObject(JavaObject.STRING);
		jo.setStringValue(stringValue);
		return jo;
	}
}
