package cc.gzhlaker.blog;

public class Linkedlist implements List {
	//------------------------------
	//-头结点编号为0
	//------------------------------
	//-声明内部类
	//------------------------------	
	public static class Node{
		public int data;
		public Node next;
		public Node(){
			data = 0;
			next = null;
		}
		public Node(int data){
			this.data = data;
			next = null;
		}
	}
	//------------------------------
	//-声明成员变量
	//------------------------------
	private int size;
	Node first;
	//------------------------------
	//-构造函数
	//------------------------------
	public Linkedlist(){
		first = new Node();
		size = 0;
	}
	//------------------------------
	//-查询长度
	//------------------------------
	public int getSize(){
		size = first.data;
		return size;
	}
	
	@Override
	public void add(int data) {
		Node now = first;
		while(now.next!=null){
			now = now.next;
		}
		now.next = new Node(data);
		first.data++;
	}

	@Override
	public void add(int data, int index) {
		Node now = first;
		int i = 0;
		while(i<index)
		{
			if(now.next==null)
			{
				add(0);
			}
			now = now.next;
			i++;
		}
		now.data = data;
		first.data++;
		
		
	}

	@Override
	public void addFirst(int data) {
		Node now = new Node(data);
		now.next = first.next;
		first.next = now;		
	}

	@Override
	public void remove(int index) {
		int i = 0;
		Node now = first;
		while(i<index-1){
			now = now.next;
			i++;
		}
		now = now.next.next;
		
		
	}

	@Override
	public void removeFirst() {
		first.next = first.next.next;
		
	}

	@Override
	public void setData(int data,int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getData(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
