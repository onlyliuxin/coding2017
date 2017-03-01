package basic;

/**
 * 实现队列
 * @author Wayss
 * 2017-02-25
 */

public class MyQueue {
    
    MyLinkedList linkList = new MyLinkedList();
    
    public void enQueue(Object o){
        linkList.addLast(o);
    }
    
    public Object deQueue(){
        return linkList.removeFirst();
    }
    
    public boolean isEmpty(){
        if(linkList.size() == 0){
            return true;
        }
        return false;
    }
    
    public int size(){
        return linkList.size();
    }
}
