package com.coding.basic;

import java.util.NoSuchElementException;


public class MyLinkedList implements List {
	
	//用内部类定义链表中的节点
		private class Node{
			//节点中包含数据和引用
			 Object data;
			 Node next;
			
			
			//每个节点包含数据和引
			public Node (Object data,Node next){
				this.data =data;
				this.next =next;
			}
		}
		//定义头节点和尾节
		public Node head;
		public Node tail;
		public int size;
		
		//无参数构造函数创建空链表
		public MyLinkedList(){
			head =null;
			tail =null;
		}
		
		//链表中传入元
		public MyLinkedList(Object element){
			head.data =element;
			head.next =tail;
			size++;
		}
		
		public void add(Object o){
			addLast(o);
		}
		 public void  addFirst(Object element) {

			 head =new Node(element,head);		 
		    if(tail == null){ 		  
				tail=head;
			 }
			   size++;
		 }
		 
		 public void addLast(Object element) {
			 if(head == null) {
				 head =new Node (element,null);
				 tail =head;
			 }else{
				 Node newNode =new Node(element,null);
				 tail.next =newNode;
				 tail=newNode;
			 }
			 size++;
			 
		 }
		 
		 public void add(int index,Object element){
			 
			 if(index < 0 || index > size) {
				 throw new IndexOutOfBoundsException("索引越界");
			 }
			 if(index == 0) {
				 head =new Node(element,head);			 
			 }
			 Node frontNode =getNode(index-1); 
			 frontNode.next =new Node(element,frontNode.next);
			 size++;
		 }
		 public Node  getNode(int index)
		 {
			 if(index < 0 || index > size-1) {
				 
				 throw new IndexOutOfBoundsException("索引越界");
			 }
			 Node current=head;
			 for(int i=0;i < size; i++,current =current.next) {
				 if(i == index) {
					  return current;
				 }		
			 }
			 return null;
		 }
		 public int indexOf(Object o){
			 
			 for(int i=0; i<size; i++){
				 if(o.equals(this.get(i))){
					 return i;
				 }
			 }
			 
			 return -1;
		 }
		 public Object get(int index){
			 return getNode(index).data;
		 }
		 
		 public Object remove(int index){
			 if(index < 0 || index > size-1) {
				 throw new IndexOutOfBoundsException("索引越界");
			 }
			 Node delNode =null;
			 if(index == 0) {
				 delNode =head;
				 head =head.next;
			 }else{			
				 Node frontNode =getNode(index-1);
				 delNode =frontNode.next;
				 frontNode.next =delNode.next;
				 delNode.next =null;		 
			 }
			 size--;
			 return delNode.data;
		 }
		 
		 public Object removeFirst(){
			 if(head == null || head.next == null)
				 throw new NoSuchElementException();
			 Node oldhead =head; 
			 head =head.next;
			 oldhead.next =null;
			 size--;
			 return oldhead.data;
					 
		 }
		 
		 public Object removeLast(){
			 return remove(size - 1);
			
		 }

		 
		 public int size() {
			 return size;
		 }
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 * @return 
	 */
	public void reverse(){	
		Node tail =null,a,b;
		a = head;
		while(a!= null){
			b=a.next;
			a.next=tail;
			tail =a;
			a=b;
		}
		head=tail;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		int len =size;
		
		for(int i=0;i<len/2;i++){
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if( i>=0 && i+length <= size){
			int len=0;
			if(i == 0){
				len =i+length;
			}else{
				len =i+length-1;
			}
			for(int j=1; j <=len; j++){
				remove(i);
			}
		}else{
				System.out.println("参数错误");
			}
		
	}
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(MyLinkedList list){
		
		int [] array  =new int [list.size];
		
		for(int j=0;j<list.size;j++){
			int index =(Integer) list.get(j);
			if(index <= size-1){
				array [j]=(Integer) get(index);
			}else{
				System.out.println("角标越界");
			}
		}
		return array;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 
	 * @param list
	 */
	
	public  void subtract(MyLinkedList list){
		
		int len=list.size;
		for(int j=0;j<len;j++){
			int index =(Integer) list.get(j);
			if(j != 0){
				index=index-j*1;
			}
			if(index <= size-1){
				remove(index);
			}else{
				System.out.println("角标越界");
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		
		for(int i=0; i<size; i++){
			for(int j=i+1; j<size; j++){
				if(this.get(i).equals(this.get(j))){
					remove(j);
				}
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
		
		for(int i=0; i<size; i++){
			int num = (Integer) this.get(i);
			if(min < num && num < max){
				remove(i);
				i=0;
			}
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  MyLinkedList intersection( MyLinkedList list){
		MyLinkedList newL=new MyLinkedList();

		for(int i=0; i<size; i++){
			for(int j=0; j<list.size; j++){
				if(this.get(i).equals(list.get(j))){
					newL.add(list.get(j));
				}
			}
		}
		return newL;
	}
}