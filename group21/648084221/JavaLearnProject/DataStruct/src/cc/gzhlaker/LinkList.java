package cc.gzhlaker;

public class LinkList implements List {
	//------------------------------
	//-
	//------------------------------
	//-
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
	//-
	//------------------------------
	private int size;
	Node first;
	//------------------------------
	//-
	//------------------------------
	public LinkList(){
		first = new Node();
		size = 0;
	}
	//------------------------------
	//-
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
	public void removeEnd(){
		Node now = first;
		while(now.next.next!=null){
			now = now.next;
		}
		now.next=null;
		
		
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
