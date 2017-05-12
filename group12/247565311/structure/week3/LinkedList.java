package structure.week3;


import java.util.NoSuchElementException;

import structure.week1.Iterator;
import structure.week1.List;

public class LinkedList implements List {
	private Node head = new Node();
	private int size = 0;
	private void checkAddIndex(int index){
        if(index > size || index<0) throw new IndexOutOfBoundsException("Index:"+index+", Size:"+size);
	}
	private void checkGetIndex(int index){
        if(index >= size || index<0) throw new IndexOutOfBoundsException("Index:"+index+", Size:"+size);
	}
	public void add(Object o){    
        Node newNode = new Node(o),p = head;
        while(p.next!=null)
        	p = p.next;
        p.next = newNode;
        size += 1;
	}
	/**
	 * 
	 * */
	public void add(int index , Object o){
		checkAddIndex(index);
        Node p = head;
	    for(int i=0;i<index;i++)
	    	p = p.next;
	    Node newNode = new Node(o);
	    newNode.next = p.next;
	    p.next = newNode;
	    size += 1;
	}
	/**
	 * 
	 * */
	public Object get(int index){
		checkGetIndex(index);
		Node p = head;
		for(int i=0;i<index;i++){
			p = p.next;
		}
		return p.next.data;
	}
	public Object remove(int index){
		checkGetIndex(index);
		Node p = head;
		for(int i=0;i<index;i++)
			p = p.next;
		Node tar = p.next;
		Object res = tar.data;
		p.next = p.next.next;
		size -= 1;
		tar.next = null;
		tar.data = null;
		return res;
	}

	public int size(){
		return size;
	}

	public void addFirst(Object o){
        add(o);
	}
	public void addLast(Object o){
        add(size, o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Iterator iterator(){
		return null;
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		Node rhead = new Node(),p=head;
		for(int i=0;i<size;i++){
			p = p.next;
			Node newNode = new Node(p.data);
			newNode.next = rhead.next;
			rhead.next = newNode;
		}
		p = head;
		for(int i=0;i<=size;i++){ // head 也要被删掉
			Node delNode = p;
			p = p.next;
			delNode.next = null;
			delNode.data = null;
		}
		head = rhead;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int numToRemove = size/2;
		for(int i=0;i<numToRemove;i++)
			remove(0);
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for(int j=0;j<length;j++)
			remove(i);
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list){
		int []res = new int[list.size()];
		Node p = head.next,q = list.head.next;
		int lenl = list.size(),index=0;
		for(int i=0;i<lenl && p!=null;i++){
			int tar =( (Integer)q.data).intValue();
			checkGetIndex(tar);
			q = q.next;
			while(index<tar && p!=null){
				p = p.next;
				index += 1;
			}
			if(p!=null) res[i] = ((Integer)(p.data)).intValue(); // 感觉当前链表是有序的这个条件多余
		}
		return res;
	}
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	public  void subtract(LinkedList list){
		Node p = head,q = list.head.next;
		int lenl = list.size();
		for(int i=0;i<lenl && p.next!=null;i++){
			int tar = ((Integer) q.data).intValue();
			q = q.next;
			int cur = ((Integer) p.next.data).intValue(); //  这行代码放在循环外会减少操作，但是放在循环里面会使程序简洁
			while(p.next!=null && cur != tar){
				cur = ((Integer) p.next.data).intValue();
				p = p.next;
			}
			while(p.next!=null && cur == tar){
				Node delNode = p.next;
				p.next = delNode.next;
				size -= 1;
				delNode.data=null;
				delNode.next = null;
				if(p.next!=null) cur = ((Integer) p.next.data).intValue();
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node p = head;
		while(p.next !=null){
			int cur = ((Integer) p.next.data).intValue();
			p = p.next;
			while(p.next !=null && ((Integer)p.next.data).intValue() == cur){
				Node delNode = p.next;
				p.next=delNode.next;
				size -= 1;
				delNode.data=null;
				delNode.next = null;
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		if(min+2>max) return; // 目标区间 (min,max)
		Node p = head;
		while(p.next != null && ((Integer)p.next.data).intValue()<=min) // 跳过不相干的元素
			p = p.next;
		while(p.next != null && ((Integer)p.next.data).intValue()<max){ // 删掉满足条件的
			Node delNode = p.next;
			p.next = delNode.next;
			size -= 1;
			delNode.next = null;
			delNode.data = null;
		}
	}
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList res = new LinkedList();
		Node p = head.next,q = list.head.next;
		while(q != null && p != null){
			int tar = ((Integer)q.data).intValue();
			q = q.next;
		    int cur = ((Integer)p.data).intValue();
		    while(p != null && cur < tar){
		    	p = p.next;
		    	cur =  ((Integer)p.data).intValue();
		    }
		    if (cur == tar){
		    	res.add(cur);
		    	p = p.next;
		    }
		}
		return res;
	}
	@Override
	public boolean hasNext() {
		return false;
	}
	@Override
	public Object next() {
		return null;
	}
	private static  class Node{
		Object data;
		Node next;
		public Node(Object o){data = o;next=null;}
		public Node(){data=null;next=null;}
	}
}
