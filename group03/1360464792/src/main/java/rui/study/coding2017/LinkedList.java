package rui.study.coding2017;

/**
 * 单向链表
 */
public class LinkedList {
    private Node head;

    private Node current;

    private int size;

    public LinkedList(){
    }

    public int size(){
        return size;
    }

    public void add(Object o){
        Node newNode=new Node(o,null);
        if(size==0){
            head=current=newNode;
        }
        current.next=newNode;
        current=newNode;
        size++;
    }

    public void add(int index , Object o){
        checkIndexForAdd(index);
        if(index==size){
           add(o);
        }else{
           Node newNode=new Node(o,null);
            if(index==0){
                newNode.next=head;
                head=newNode;
            }else{
                Node after=getIndexNode(index);
                Node before=getIndexNode(index-1);
                before.next=newNode;
                newNode.next=after;
            }
            size++;
       }
    }


    public Object get(int index){
        return getIndexNode(index).data;
    }

    public void addFirst(Object obj){
        add(0,obj);
    }
    public void addLast(Object obj){
        if(size==0){
            add(obj);
        }else {
            add(size,obj);
        }
    }

    public Object remove(int index){
        checkIndex(index);
        Node needRemove;
        if(index==0){
            needRemove=head;
            if(size==1){
                head=null;
            }else{
                head=head.next;
            }
        }else{
            needRemove=getIndexNode(index);
            Node before=getIndexNode(index-1);
            before.next=needRemove.next;
            if(index==size-1){
                current=before;
            }
        }
        size--;
        return needRemove.data;
    }
    public Object removeFirst(){
        return remove(0);
    }
    public Object removeLast(){
        return remove(size-1);
    }
    public Iterator iterator(){
        return new LinkedListIterator();
    }


    public class LinkedListIterator implements Iterator{

       private int cursor=0;

       private Node cursorNode=head;

        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public Object next() {
            Object object=cursorNode.data;
            cursorNode=cursorNode.next;
            cursor++;
            return object;
        }
    }

    private void checkIndexForAdd(int index){
        if(!(index>=0&&index<=size)){
            throw new IndexOutOfBoundsException("索引"+index+"越界！");
        }
    }
    private void checkIndex(int index){
        if(!(index>=0&&index<size)){
            throw new IndexOutOfBoundsException("索引"+index+"越界！");
        }
    }

    private Node getIndexNode(int index){
        checkIndex(index);
        if(index==0){
            return head;
        }else if(index+1==size){
            return current;
        }else{
            boolean flag=false;
            Node nodes=head;
            int cursor=1;
            while(!flag){
                nodes=nodes.next;
                flag=cursor==index;
                cursor++;
            }
            return nodes;
        }
    }

    private static class Node{
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
