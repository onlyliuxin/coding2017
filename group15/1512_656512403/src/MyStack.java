/**
 * Created by wangtiegang on 2017/2/25.
 */
public class MyStack {
    private int size;

    private MyArrayList list = new MyArrayList();

    public void push(Object o){
        list.add(o);
        size = list.size();
    }

    public Object pop(){
        if(size == 0){
            return null;
        }

        Object obj = list.get(size-1);
        list.remove(size-1);
        size = list.size();
        return obj;
    }

    public Object peek(){
        if(size == 0){
            return null;
        }
        Object obj = list.get(size-1);
        return obj;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        else
            return false;
    }

    public int size(){
        return size;
    }
}
