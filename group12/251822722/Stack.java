
public class Stack {
    private Object[] elementData = new Object[100];

    private int size =0;
    public void push(Object o) {
        elementData[size()]=o;
        size=size+1;
    }

    public Object pop() {
        Object a = elementData[size];
        elementData[size]=null;
        size = size-1;
        return a;
    }

    public Object peek() {
        return  elementData[size];
    }

    public boolean isEmpty() {

        if(size==0){
            return true;
        }
        return false;
    }

    public int size() {
        return elementData.length;
    }
}
