package com.github.orajavac.coding2017.basic;

public class LinkedList implements List,Iterator {
	
	private Node head;

	private Node current;
	
	private int size=0;
	
	public void add(Object o){
		Node n = new Node();
		n.next=this.head;
		n.data=o;
		this.head=n;
	}
	public void add(int index , Object o){
		
	}
	public Object get(int index){
		Node c = this.head;
		int i=0;
		while(c!=null){
			i++;
			if (index==i){
				return c.data;
			}
			c=c.next;
		}
		return null;
	}
	public Object remove(int index){
		int s=size();
		if (s==index){
			Node n=head.next;
			head=n;
			return null;
		}
		Node n=head;
		Node p = null;
		while (n!=null){
			s--;
			if (s==index){
				p=n.next;			
				n.next=p.next;
				break;
			}
			n=n.next;
		}
		return null;
	}
	
	public void listNode(){
		Node c = this.head;
		while(c!=null){
			System.out.print(c.data+ " -> ");
			c=c.next;
		}
		System.out.println();
	}
	
	public int size(){
		size=0;
		Node c = this.head;
		while(c!=null){
			size++;
			c=c.next;
		}
		return size;
	}
	
	public void addFirst(Object o){
		if (this.head==null){
			this.head = new Node();
			this.head.data=o;
		}else{
			Node f = new Node();
			Node n=head;
			Node p=null;
			while (n!=null){
				p=n;
				n=n.next;
			}
			f.data=o;
			p.next=f;
		}
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		remove(1);
		return null;
	}
	public Object removeLast(){
		remove(size());
		return null;
	}
	
	public Iterator iterator(){
		LinkedList l = new LinkedList();
		l.head=this.head;
		return l;
	}
	
	public boolean hasNext(){
		current = head;
		if (current!=null){
			head = current.next;
			return true;
		}
		return false;
	}
	
	public Object next(){
		return current.data;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
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
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public static int[] getElements(LinkedList list){
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
