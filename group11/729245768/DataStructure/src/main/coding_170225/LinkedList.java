
/**
 * Created by peter on 2017/2/22.
 */
public class LinkedList {
    private Node head,tail;
    private int size=0;
    public void  add(Object o){
        Node node = new Node();
        node.data = o;
        node.next=null;
        if(size==0){
            head=node;
            tail=node;
        }else{
            tail.next = node;
            tail = tail.next;
        }
        size++;
    }
    public void add(int index,Object o){
        if(index<0||index>size){
            System.out.println("插入下标越界");
            throw new ArrayIndexOutOfBoundsException();
        }
        //如果插入的位置是第一个
        if(index==0){
            Node node =new Node();
            node.data = o;
            node.next = head;
            head = node;
        }else {
            int i =0;//记录走过的节点
            Node p = head;//移动节点
            while (i<index-1){
                p=p.next;
                i++;
            }
            //寻找到要插入位置的上一个位置
            Node node = new Node();
            node.data=o;
            node.next=p.next;
            p.next=node;
        }
        size++;
    }
    public Object get(int index){
        if(index<0||index>size-1){
            System.out.println("访问下标越界");
            throw new ArrayIndexOutOfBoundsException();
        }
        Node node =head;
        int i=0;
        while (i<index){
            node=node.next;
            i++;
        }
        return node.data;
    }
    public Object remove(int index){
        //如果链表里没有数据则不能删除
        if(size==0){
            System.out.println("list is empty");
        }
        if(index<0||index>size-1){
            System.out.println("out of array");
            throw new ArrayIndexOutOfBoundsException();
        }
        Object data=null;//用来存储返回值
       if(index==0){
            //删除的是第一个节点
           data = head.data;
           head=head.next;
           size--;
           return data;
       }else{
           Node p1=head,p2=null;//p1表示移动节点，p2是上一个节点
           int i =0;//表示移动的距离
           while (i<index){
               p2=p1;
               p1=p1.next;
               i++;
           }
           data = p1.data;
           if(index==size-1){
            p2.next = null;
            tail = p2;
           }else{
               p2.next = p1.next;
           }
           size--;
           return data;
       }
    }
    public int size(){
        return size;
    }
    public void addFirst(Object o){
        add(0,o);
    }
    public Object removeFirst(){

        return remove(0);
    }
    public Object removeLast() {
        return remove(size-1);
    }
    //内部节点类
    static class Node{
        public Object data;
        public Node next;
    }
}
