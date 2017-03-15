/**
 * 先进先出
 */
public class MyQueue {
    private MyLinkedList myLinkedList = new MyLinkedList();
    public void enQueue(Object o){
        myLinkedList.add(o);
    }
    public void deQueue(){
        myLinkedList.remove(0) ;
    }
    public boolean isEmpty(){
        return myLinkedList.size<1;
    }
    public int size(){
        return myLinkedList.size;
    }
    public String toString(){
        return myLinkedList.toString();
    }
}
