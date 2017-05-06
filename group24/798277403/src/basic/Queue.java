package basic;

/**
 * 自己实现的Queue,用自己的LinkedList实现
 * Created by zhouliang on 2017-03-10.
 */
class Queue<E> {

    private LinkedList<E> linkedList;

    public Queue(){
        this.linkedList = new LinkedList<E>();
    }
    /**
     * 从队列头部添加元素
     * @param e 代表Element
     */
    public void enQueue(E e){
        linkedList.addFirst(e);
    }

    public E deQueue(){
        return linkedList.removeLast();
    }

    public boolean isEmpty(){
        return linkedList.size() > 0;
    }

    public int size(){
        return linkedList.size();
    }
}
