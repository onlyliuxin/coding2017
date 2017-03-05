/**
 * Created with IntelliJ IDEA.
 * User: guohairui
 * Date: 17-2-26
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
public class MyLinkedList    {
     private Node head=new Node(null,null,null);
    public int size = 0;

    public MyLinkedList(){
        head.next=head.previous=head;
    }
    public void add(Object o){
         if(null==head){
             head = new Node(null,null,null);
         }
        Node newNode = new Node(o,head,head.previous); //放在最后
        newNode.previous.next=newNode;
        newNode.next.previous=newNode;
        size++;
    }
    public void add(int index,Object o ) {
        Node newNode = new Node(o,getNode(index),getNode(index).previous); //放在最后
        newNode.previous.next=newNode;
        newNode.next.previous=newNode;
        size++;
    }
    public Object get(int index){
          return     getNode(index).data;
    }
    public Object remove(int index){
        remove(getNode(index));
           return null;
    }
    public void addFirst(Object o){

    }
    public void removeFirst(){
         remove(head.next);
    }
    public void removeLast(){
        remove(head.previous);
    }
    public void remove(Node node){
        node.previous.next=node.next;
        node.next.previous=node.previous;
        size--;
    }


    public int size(){
        return size();
    }
    public Object[] toArray(){
        return null;
    }


    public Node getNode(int index){
        if(index<0||index>=size)
            throw new RuntimeException("超出范围了");
        Node node = head;
        if(index<(size>>1)){//当偏向于前一半时从头找，否则从尾找
            for ( int i=0;i<=index;i++) {
                node = node.next;
            }
        }else {
            for (int i=size;i>index;i--){
                 node=node.previous;
            }
        }
        return node;
    }

    private static class Node{
       Object data;//当前Entry
       Node next;//下一个
       Node previous;//前一个
        public Node(Object data,Node next,Node previous){
            this.data=data;
            this.next=next;
            this.previous=previous;
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node n =getNode(0) ;
        for (int i=0;i<size;i++) {
            sb.append(n.data);
            if(i<size-1)
                sb.append(",");
            n=n.next;
        }
        return sb.append(']').toString();
    }
}
