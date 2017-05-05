package com.coding.basic;



public class LinkedList implements List {
	
	private Node head;
	private static int size=0;
	
	public void add(Object o){
		if(size==0){
			head=new Node(o);
		}else{
			Node node=head;
			while(node.next!=null){
				node=node.next;
			}
			node.next=new Node(o);
		}
		size++;
	}
	public void add(int index , Object o){
		if(index<=size){
			if(index==0){
				addFirst(o);
			}else if(index==size){
				addLast(o);
			}else{
				Node node=head;
				for(int i=0;i<size-1;i++){
					Node behindNode=node.next;
					if(i==index-1){
						node.next=new Node(o);
						node.next.next=behindNode;
					}
					node=node.next;
				}
			}
			size++;
		}else{
			throw  new IndexOutOfBoundsException("越界！");
		}
		
	}
	public Object get(int index){
		if(index<size){
			Node node=head;
			for(int i=0;i<index;i++){
				node=node.next;
			}
			return node.data;
		}else{
			throw new IndexOutOfBoundsException("越界");
		}
		
	}
	public Object remove(int index){
		if(index<size){
			if(index==0){
				removeFirst();
			}else if(index==size-1){
				removeLast();
			}else{
				Node node=head;
				for(int i=0;i<size-1;i++){
					if(i==index-1){
						node.next=node.next.next;
					}
					node=node.next;
				}
				size--;
				return node.next.data;
			}
			return null;
		}else{
			throw new IndexOutOfBoundsException("越界");
		}
		
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node firstNode=new Node(o);
		firstNode.next=head.next;
		head=firstNode;
		size++;
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		Node node=head;
		head=head.next;
		size--;
		return node.data;
	}
	public Object removeLast(){
		Node node=head;
		Node frontNode=null;
		for(int i=0;i<size-1;i++){
			if(i==size-2){
				frontNode=node;
			}
			node=node.next;
			
		}
		frontNode.next=null;
		size--;
		return node.data;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		Node(Object o){
			this.data=o;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		for(int i=0;i<(size/2);i++){
			Object data1=get(i);
			Object data2=get(size-1);
			Object object=data1;
			data1=data2;
			data2=object;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		for(int i=0;i<(size/2);i++){
			remove(i);
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for(int j=i;j<(i+length);j++){
			remove(j);
		}
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(LinkedList list){
		int[] array=new int[list.size];
		int i=0;
		for(Node head=list.head;head!=null;head=head.next){
			array[i]=(int) this.get((int)head.data);
		}
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

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
