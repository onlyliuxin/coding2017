package task8.jvm.engine;

public class Heap {

    private Heap() {

    }

    public static Heap INSTANCE = new Heap();

    public static Heap getInstance() {
        return INSTANCE;
    }

    public JavaObject newObject(String className) {
        JavaObject object = new JavaObject(JavaObject.OBJECT);
        object.setClassName(className);
        return object;
    }

    public JavaObject newString(String value) {
        JavaObject object = new JavaObject(JavaObject.STRING);
        object.setStringValue(value);
        return object;
    }

    public JavaObject newInt(int value) {
        JavaObject object = new JavaObject(JavaObject.INT);
        object.setIntValue(value);
        return object;
    }

    public JavaObject newFloat(int value) {
        JavaObject object = new JavaObject(JavaObject.FLOAT);
        object.setFloatValue(value);
        return object;
    }

}
