package test03;

import java.util.Stack;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public void add(Object o){
		if (head==null) {
			head=new Node(o);
		} else {
			Node p=head;
			while(p.next!=null){
				p=p.next;
			}
			p.next=new Node(o);
		}
		++size;
	}
	
	public void add(int index , Object o){
		checkPositionIndex(index);
		if (index==0) {
			Node p=head;
			head=new Node(o);
			head.next=p;
		} else {
			int i=0;
			Node p=head;
			while(i<index-1){
				p=p.next;
				++i;
			}
			Node next=p.next;
			p.next=new Node(o);
			p.next.next=next;
		}
		++size;
	}
	
	public Object get(int index){
		checkPositionIndex(index);
		if (index==0) {
			return head.data;
		} else {
			int i=0;
			Node p=head;
			while(i<index){
				p=p.next;
				++i;
			}
			return p.data;
		}
	}
	
	public Object remove(int index){
		checkPositionIndex(index);
		Node result;
		if (index==0) {
			result=head;
			head=head.next;
		} else {
			int i=0;
			Node p=head;
			while(i<index-1){
				p=p.next;
				++i;
			}
			result=p.next;
			p.next=p.next.next;
		}
		--size;
		return result.data;
	}
	
	public int size(){
		return size;
	}
	
	private void checkPositionIndex(int index){
		if (index<0||index>size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
	
	public void addFirst(Object o){
		add(0, o);
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
		return new LinkedListIterator(this);
	}
	
	private class LinkedListIterator implements Iterator{
		private LinkedList list;
		private int position;
		
		public LinkedListIterator(LinkedList list) {
			this.list=list;
		}
		
		@Override
		public boolean hasNext() {
			if (position+1>size()){
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			return list.get(position++);
		}
		
	}
	
	@Override
	public String toString(){
		for (int i = 0; i <size(); i++) {
			System.out.print(get(i)+",");
		}
		return null;
	}
//	
	private static  class Node{
		Object data;
		Node next;
		
		Node(Object data) {
			this.data=data;
			this.next=null;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		Stack<Object> stack=new Stack<>();
		while (size()>0) {
			stack.add(remove(0));
		}

		while (!stack.isEmpty()) {
			this.add(stack.pop());
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		for (int i = 0; i < size()/2; i++) {
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){	
		
		checkPositionIndex(i);
		checkPositionIndex(i+length-1);
		
		for (int j = 0; j < length; j++) {
			remove(i);	
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
	public int[] getElements(LinkedList list){
		int[] result=new int[list.size];
		
		for (int i = 0; i < list.size; i++) {
			result[i]=(int)get((Integer)list.get(i));
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		int k=0;
		for (int i = size()-1; i >=0; i--) {

			for (int j = k; j < list.size(); j++) {
				if (get(i).equals(list.get(j))) {
					remove(i);
					k=j;
					break;
				}
			}
			
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		for (int i = size()-2; i >=0; i--) {
			if (get(i).equals(get(i+1))) {
				remove(i);
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
		int start=-1;
		int end=-1;
		for (int i = 0; i < size(); i++) {
			if ((int)get(i)>min) {
				start=i;
				break;
			}
		}
		for (int i = size()-1; i >=0; i--) {
			if ((int)get(i)<max) {
				end=i;
				break;
			}
		}
		if (start!=end&&start!=-1&&end!=-1) {
			remove(start, end-start+1);
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList cList=new LinkedList();
		int m=0;
		for (int i = 0; i < size(); i++) {
			for (int j = m; j < list.size(); j++) {
				if (get(i).equals(list.get(j))) {
					cList.add(get(i));
					m=j+1;
				}
			}
		}
		return cList;
	}
}
