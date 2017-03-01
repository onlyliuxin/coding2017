
/**
 * Created by peter on 2017/2/23.
 */
public class Queue {
    private LinkedList linkedList = new LinkedList();
    //进队列
    public void enQueue(Object o){
        linkedList.add(o);
    }
    //出队列
    public Object deQueue(){
        return linkedList.removeFirst();
    }
    //是否为空
    public boolean isEmpty(){
        return linkedList.size()==0;
    }
    //队列内元素
    public int size(){
        return linkedList.size();
    }
}
