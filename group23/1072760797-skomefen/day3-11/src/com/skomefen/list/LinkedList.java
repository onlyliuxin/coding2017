package com.skomefen.list;


public class LinkedList implements List {
	private int size = 0;
	private Node head;
	private Node node;
	private int point = 0;
	private LinkedListiterator iterator;

	public void add(Object o){
		insertoflist(size, o);
	}
	
	public void add(int index , Object o){
		if(index>size||index<0){
			throw new IndexOutOfBoundsException("index:"+index+"size:"+size);
		}
		insertoflist(index, o);
		
		
	}
	public Object get(int index){
		if(index>=size||index<0){
			throw new IndexOutOfBoundsException("index:"+index+"size:"+size);
		}
		if(head==null){
			return null;
		}
		if(index==point){
			return node.data;
		}
		this.point=0;
		node = head;
		while(index>=this.point){
			
			if(index==point){
				return node.data;
			}
			point++;
			node = node.next;
		}
		return null;
	}
	public Object remove(int index){
		if(index>=size||index<0){
			throw new IndexOutOfBoundsException("index:"+index+"size:"+size);
		}
		size--;
		if(index==(this.point+1)){
			Object o =node.next.data;
			node.next = node.next.next;
			return o;
		}
		this.point = 0;
		if(index==0){
			head = head.next;
			Object o =head.data;
			node = head;
			return o;
		}
		
		Object o = null;
		while(index<=(this.point+1)){
			if(index==(this.point+1)){
				o =node.next.data;
				node.next = node.next.next;
				
			}
			point++;
			node = node.next;
		}
		return o;
	}
	
	public int size(){
		
		return size;
	}
	
	public void addFirst(Object o){
		add(0,o);
	}
	public void addLast(Object o){
		add(size,o);
	}
	public Object removeFirst(){
		if(head==null){
			return null;
		}
		remove(0);
		return null;
	}
	public Object removeLast(){
		if(head==null){
			return null;
		}
		remove(size-1);
		return null;
	}
	public Iterator iterator(){
		iterator = new LinkedListiterator(this);
		return iterator;	
	}
	
	
	private static  class Node{
		private Object data;
		private Node next;
		
		
	}
	
	private void insertoflist(int index,Object o){
		
		if(index>size||index<0){
			throw new IndexOutOfBoundsException("index:"+index+"size:"+size);
		}
		
		if(head==null){
			head = new Node();
			head.data=o;
			head.next=new Node();
			size++;
			node = head;
			this.point = 0;
			return;
		}
		if(index==(this.point+1)){
			pointlast(index, o);
			return;
		}
		//head不等于空，先从head顺序往下找
		this.point = 0;
		if(index == this.point){
			Node next = head;
			head = new Node();
			head.next = next;
			head.data = o;
			node = head;//当前节点为head
			this.point = index;
			size++;
			return;
		}
		do{
			if(index==(this.point+1)){
				pointlast(index, o);
				return;
			}
			node = node.next;
			this.point++;
		}while(index>(this.point+1));
		
	}

	private void pointlast(int index, Object o) {
		if(index==(this.point+1)){
			if(index==size){//index插入List结尾
				this.point = this.point+1;
				node = node.next;
				node.data=o;
				node.next = new Node();
				size++;
				return;
			}
			this.point = this.point+1;
			Node next = node.next;//从上一个node获取下一个node
			node.next = new Node();//上一个节点指向新建节点
			node = node.next;//获取新建节点
			node.data=o;//新建节点获取值
			node.next = next;//新建节点指向下一个节点
			size++;
			return;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
	}
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(LinkedList list){
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}
