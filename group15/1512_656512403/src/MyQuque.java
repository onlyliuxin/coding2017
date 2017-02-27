/**
 * Created by wangtiegang on 2017/2/25.
 */
public class MyQuque {

    private int size;

    private MyLinkedList linkedList = new MyLinkedList();

    public void enQueue(Object o){
        linkedList.add(0,o);
        size = linkedList.size();
    }

    public Object deQueue(){
        if(size == 0){
            return null;
        }
        Object obj = linkedList.get(size-1);
        linkedList.remove(size-1);
        size = linkedList.size();
        return obj;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }
}
