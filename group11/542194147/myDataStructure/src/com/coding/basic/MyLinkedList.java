package com.coding.basic;

import java.util.Iterator;

public class MyLinkedList implements MyList {

	private Node head;//头指针
	private Node last;//尾指针
	private int size;//集合大小
	
	public MyLinkedList(){
		
	}
	@Override
	public void add(Object o) {
		addLast(o);//默认插到队尾

	}

	@Override
	public void add(int index, Object o) {
		if(index>size||index<0){//检查下标是否越界
			throw new RuntimeException("下标越界");
		}
		if(index==this.size){
			addLast(o);//插到队尾
		}else{
			Node l= node(index);
			addBeforeNode(o,l);//插到指定下标节点之前
		}
	}

	@Override
	public Object get(int index) {
		if (index >= size || index < 0) {
			throw new RuntimeException("下标越界");
		}
		return node(index).data;
	}

	@Override
	public Object remove(int index) {
		if (index >= size || index < 0) {
			throw new RuntimeException("下标越界");
		}
		Node l=node(index);
		Node prevNode=l.prev;
		Node nextNode=l.next;
		if(prevNode==null){
			head=nextNode;
		}else{
			prevNode.next=nextNode;
			
		}
		if(nextNode==null){
			last=prevNode;
		}else{
			nextNode.prev=prevNode;
		}
		l.prev =null;
		l.next=null;
		l.data=null;
		return l.data;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * 获取对应节点的下标
	 * @param element
	 * @return
	 */
	public int indexOf(Object element) {
		Node current = head;
	    int count = 0;
	    while (current != null) {
	    	if (element != null) {
	    		if (element.equals(current.data)) {
	    			return count;
	    			}
	            }else{
	            	if (current.data == null) {
	            		return count;
	                }
	            }
	    	count ++;
	        current = current.next;
	        }
	    return -1;
	    }
	 
	/**
	 * 添加到对应下标的节点之前
	 * @param o
	 * @param theNode
	 */
	public void addBeforeNode(Object o,Node theNode){
		Node prevNode=theNode.prev;
		Node newNode= new Node(o,theNode,prevNode);
		theNode.prev=newNode;
		if(null==prevNode){
			this.head=newNode;
		}else{
			prevNode.next=newNode;
		}
		size++;
	}
	
	/**
	 * 默认添加到队尾
	 * @param o
	 */
	public void addLast(Object o){
		Node l=this.last;
		Node node= new Node(o,null,l);
		if(null!=l){
			l.next=node;
		}else{
			this.head=node;
		}
		size++;
	}
	
	/**
	 * 查找对应下标的节点并返回
	 * @param index
	 * @return
	 */
	private Node node(int index){
		if(index<(this.size>>1)){
			Node current=head;
			for(int i=0;i<index;i++){
				current=current.next;
			}
			return current;
		}else{
			Node current= last;
			for(int i=size-1;i>index;i--){
				current=current.prev;
			}
			return current;
		}
	}
	
	public Iterator<?> iterator(){
		return new MyLinkedListIterator();
	}
	
	private class MyLinkedListIterator implements Iterator<Object>{
		private Node current=head;
		
		@Override
		public boolean hasNext() {

			return current!=last;
		}

		@Override
		public Object next() {
			if(hasNext()==false){
				throw new RuntimeException("不存在对应元素");
			}
			Object o=current.data;
			current=current.next;
			return o;
		}

		@Override
		public void remove() {
			int index=MyLinkedList.this.indexOf(current);
			MyLinkedList.this.remove(index);
		}
		
	}
	/**
	 * 双向链表
	 * @author 小摩托
	 *
	 */
	private static  class Node{
		Object data;
		Node next;
		Node prev;
		public Node(Object d,Node n,Node p){
			this.data=d;
			this.next=n;
			this.prev=p;
		}
	}

	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		Node p=head; Node q=null; Node front=null;
		while(p!=null){
			q=p.next;
			p.next=front;
			front=p;
			p=q;
		}
		head=front;
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
	public static int[] getElements(MyLinkedList list){
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(MyLinkedList list){
		
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
	public  MyLinkedList intersection( MyLinkedList list){
		return null;
	}
}
