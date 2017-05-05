package djj;

public class LinkedList implements List {
	//头节点
	private Node head;
    //尾节点
//    private Node tail;
    //当前游标节点
    private Node curNode;
    public int size=0;

	
	public void add(Object o){
		if(head==null){
            head=new Node(o);
            head.next=null;
        }else{
            curNode=head;
            while(curNode.next!=null){
                curNode=curNode.next;
            }
            curNode.next=new Node(o);
        }
        size++;
	}
	public void add(int index , Object o){
        if(index>size||index<=0){
            throw new RuntimeException("越界");
        }else{
            curNode=head;
            for(int i=0;i<index;i++){
                curNode=curNode.next;
            }
            Node newNode=new Node(o);
            newNode.next=curNode.next;
            curNode.next=newNode;
            size++;
        }
	}
	public Object get(int index){
        if(index>size||index<=0){
            throw new RuntimeException("越界");
        }
        Node temp=head;
        for(int i=0;i<index;i++){
            temp=temp.next;
        }
		return temp.data;
	}
	public Object remove(int index){
        if(index>size||index<=0){
            throw new RuntimeException("越界");
        }
        Node temp=head;
        for(int i=0;i<index-1;i++){
            temp=temp.next;
        }
        Node delNode=temp.next;
        temp.next=delNode.next;
        delNode.next=null;
        size--;
		return delNode.data;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
        add(1,o);
	}
	public void addLast(Object o){
		add(size,o);
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static class Node{
		Object data;
		Node next;

        public Node(Object o){
            data=o;
            next=null;
        }

	}
}
