package cn.task1;

public class LinkedList{

	Node head;
	int size;
	
	public LinkedList(){
		head = new Node();
		this.size = 0;
	}

	public void add(Object data) {
		Node temp = head;
		while(temp.next != null){
			temp = temp.next;
		}
		temp.next = new Node(data);
		size++;
	}
	
	public void set(int index,Object obj){
		try {
			Node temp = new Node(obj);
			Node pre = null;
			if (index > 0) {
				pre = getNode(index - 1);
			} else {
				pre = head;
			}
			Node last = getNode(index);
			pre.next = temp;
			temp.next = last;
			size++;
		} catch (Exception e) {
			// TODO: handle exception
			try {
				throw new Exception("存在异常错误！");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}


	public Object get(int index) {
		Node temp = head;
		if(index>=size){
			throw new ArrayIndexOutOfBoundsException("超出范围！");
		}
		for(int k=0;k<index;k++){
			temp = temp.next;
		}
		return temp.next.obj;
	}
	
	public Node getNode(int index) {
		Node temp = head;
		if(index>=size){
			throw new ArrayIndexOutOfBoundsException("超出范围！");
		}
		for(int k=0;k<index;k++){
			temp = temp.next;
		}
		return temp.next;
	}

	
	public void remove(int index) {
		// TODO Auto-generated method stub
		Node temp = head;
		if(index>=size){
			throw new ArrayIndexOutOfBoundsException("超出范围！");
		}
		for(int k=0;k<size;k++){
			if(k==index){
				temp.next = temp.next.next;
				size--;
				break;
			}else{
				temp = temp.next;
			}
		}
	}

	
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	
	public boolean isEmpty() {
		if(size>0){
			return true;
		}else{
			return false;			
		}
	}
	
	public void clear(){
		head.next = null;
		size=0;
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.add("dd");
		list.add("ff");
		list.set(4, "4546");
//		list.remove(2);list.remove(2);
		System.out.println(list.size());
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.get(3));
		System.out.println(list.get(4));
		System.out.println(list.get(5));
		System.out.println(list.isEmpty());
		list.clear();
		System.out.println(list.isEmpty());
	}
	
}


class Node{
	
	Object obj;
	Node next;
	
	public Node(){
		this.obj = null;
		this.next = null;
	}
	
	public Node(Object obj){
		this.obj = obj;
		this.next = null;
	}
	
}
