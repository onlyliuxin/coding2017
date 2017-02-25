package basic;

/**
 * 实现LinkedList基本功能
 * @author Wayss
 * 2017-02-23
 */

public class MyLinkedList implements MyList {
    
    private Node head;
    private int size = 0;
    
    public void add(Object o){
        Node n = new Node(o);
        head.next = n;
        size++;
    }
    public void add(int index , Object o){
        //1.index校验
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("插入的下标越界了:"+"插入的下标为："+index+"集合大小为："+size);
        }
        //2. 查找index位置的前一个节点
        //tempNode为当前链表的第一个节点
        Node tempNode = head.next;
        for(int i = 0; i < index - 1 ; i++){
            tempNode = tempNode.next;
        }
        Node behindNode = tempNode.next;
        Node insertNode = new Node(o);
        tempNode.next = insertNode;
        insertNode.next = behindNode;
        size++;
    }
    public Object get(int index){
      //1.index校验
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("插入的下标越界了:"+"插入的下标为："+index+"集合大小为："+size);
        }
        //2. 查找当前节点
        Node tempNode = head.next;
        for(int i = 0; i < index; i++){
            tempNode = tempNode.next;
        }
        return tempNode.data;
    }
    public Object remove(int index){
        //1.index校验
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("插入的下标越界了:"+"插入的下标为："+index+"集合大小为："+size);
        }
        //2. 查找当前节点的上一个节点
        Node tempNode = head.next;
        for(int i = 0; i < index - 1; i++){
            tempNode = tempNode.next;
        }
        Node deleteNode = tempNode.next;
        Node behideNode = tempNode.next.next;
        tempNode.next = behideNode;
        size--;
        return deleteNode.data;
    }
    
    public int size(){
        return size;
    }
    
    public void addFirst(Object o){
        Node insertNode = new Node(o);
        insertNode.next = head.next;
        head.next = insertNode;
        size++;
    }
    public void addLast(Object o){
        Node insertNode = new Node(o);
        Node tempNode = head.next;
        for(int i = 0; i < size; i++){
            tempNode = tempNode.next;
        }
        tempNode.next = insertNode;
        size++;
    }
    public Object removeFirst(){
        Node firstNode = head.next;
        head = firstNode.next;
        size--;
        return firstNode;
    }
    public Object removeLast(){
        Node tempNode = head.next;
        //1.移除需要找到最后一个点的前一个点
        for(int i = 0; i < size - 1; i++){
            tempNode = tempNode.next;
        }
        Node deleteNode = tempNode.next;
        tempNode.next = null;
        size--;
        return deleteNode;
    }
    public MyIterator iterator(){
        return null;
    }
    
    
    private static class Node{
        Object data;
        Node next;
        public Node(Object data){
            this.data = data;
        }
    }
}
