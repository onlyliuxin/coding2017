package main.coding_170430.jvm.engine;

/**
 * Created by peterchen on 2017/5/5.
 */
public class Heap {
    private static Heap instance = new Heap();
    private Heap(){
    }
    public static Heap getInstance(){
        return instance;
    }
    public JavaObject newObject(String clzName){
        JavaObject jo = new JavaObject(JavaObject.OBJECT);
        jo.setClassName(clzName);
        return jo;
     }
     public JavaObject newString(String value){
        JavaObject jo = new JavaObject(JavaObject.STRING);
        jo.setClassName(value);
        return jo;
     }

     public JavaObject newFloat(float value){
         JavaObject jo = new JavaObject(JavaObject.FLOAT);
         jo.setFloatValue(value);
         return jo;
     }
     public JavaObject newInt(int value){
         JavaObject jo = new JavaObject(JavaObject.INT);
         jo.setIntValue(value);
         return jo;
    }
}
