/**
 * 先进后出
 */
public class MyStack {
    private MyArrayList myArrayList = new MyArrayList();
    public MyStack(){}
    public void push(Object o) {
        myArrayList.add(o);
    }
    public Object pop(){
        Object popo=myArrayList.get(myArrayList.size-1);
        myArrayList.remove(myArrayList.size-1);
        return popo;
    }
    public Object peek(){

        return myArrayList.get(myArrayList.size-1);

    }
    public boolean  isEmpty(){

        return myArrayList.size<1;
    }
    public int size(){
        return myArrayList.size;
    }
    public String toString(){
        return myArrayList.toString();
    }
}
