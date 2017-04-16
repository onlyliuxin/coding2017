package com.coding.basic;

import java.util.ArrayList;



public class LinkedList implements List {
	
	private Node head ;
	
	private static class Node{
		Object data;
		Node next;

	}
	/**
	 * @param head
	 */
	public LinkedList() {
//		super();// Incorrect
//		head = null; //Incorrect
		head=new Node();
	}
	
	public LinkedList(int[] array){
		this.head = new Node();
		if(array.length==0){
			return;
		}
		if(array.length==1){
			this.head.data = array[0];
			this.head.next = null;
		}
		else{
			Node curNode = new Node();
			for(int i=0;i<array.length;i++){
				if(i==0){
					this.head.data = array[i];
					this.head.next = curNode;
				}
				else if(i==array.length-1){
					curNode.data = array[i];
				}
				else{
					curNode.data = array[i];//newNode.next 这个对象在堆上不存在，会引起InvocationTargetException
					Node newNode = new Node();
					curNode.next = newNode;
					curNode = newNode;
				}
			}	
		}//else end
	}
	
	public String printLinkedList(){
		if(head.data == null ){
			return "";
		}
		//不用判断head.data==null
		Node curNode = head;
		String str = String.valueOf(curNode.data);//注意!!(String)curNode.data会引起InvocationTargetException
		while(curNode.next!=null){
			str = str + "->";
			curNode = curNode.next;
			str = str + String.valueOf(curNode.data);
		}
		System.out.println(str);
		return str;
	}

	
	
	//Add在整个linklist的尾部
	public void add(Object o){
//		if(head==null){ //It is always "False" because of Constructor 
		if(head.data==null){
			head.data=o;
			head.next=null;
		}else{
			Node newNode = new Node();
			Node preNode = new Node();
			preNode = head;
			while(preNode.next!=null){
				preNode = preNode.next;
			}
			preNode.next = newNode;
			newNode.data = o;
			newNode.next = null;
		}
		
		
	}
	
	//把返回类型改成Boolean,觉得Boolean更好，且适合单元测试。
	public Boolean add(int index , Object o){
		if(index==0){
			this.addFirst(o);
			return true;
		}
		if(index==this.size()){
			this.addLast(o);
			return true;
		}
		if(index>this.size()){
			System.out.println("Error:index should not be larger than size of the linklist!!");
			return false;
		}
		else{
			Node newNode = new Node();
			newNode.data = o;
			Node preNode = head;
			Node postNode = preNode.next;
			Boolean flag = true;
			while(flag){
				if(--index==0){
					break;
				}
				preNode = postNode;
				postNode = preNode.next;
			}
			preNode.next = newNode;
			newNode.next = postNode;
			return true;
		}
		
	}
	
	
	public Object get(int index){
		if(index>=this.size()){
			return null;
		}else{
			Boolean flag = true;
			Node node = head;
			while(index--!=0){
				node=node.next;
			}
			return node.data;
		}
		
		
	}
	
	
	public Boolean remove(int index){
		if(index==0){
			this.removeFirst();
			return true;
		}
		if(index>=this.size()){
			return false;
		}else{
			Node preNode = head;
			Node postNode = preNode.next;
			Boolean flag = true;
			while(flag){
				if(--index==0){
					break;
				}
				preNode = postNode;
				postNode = preNode.next;
			}
			preNode.next = postNode.next;
			return true;
		}
	}
	
	public int size(){
		if(head.data==null){
			return 0;
		}
		else
		{
			int size = 1;
			Node node = head;
			while(node.next!=null){
			size++;
			node = node.next;
			}
			return size;
		}
	}
	
	public void addFirst(Object o){
		if(head.data == null){
			head.data = o;
			head.next = null;
		}
		else{
			Node addItem = new Node();
			addItem.data = o;
			addItem.next = head;
			head = addItem;
		}
		
	}
	public void addLast(Object o){
		this.add(o);
	}
	
	public Boolean removeFirst(){
		if(head.data == null){
			return false;
		}
		if(head.next == null){
			head = null;
			return true;
		}else{
			head = head.next;
			return true;
		}
	}
	
	public Boolean removeLast(){
		if(head.data == null){
			return false;
		}
		if(head.next == null){
			head = null;
			return true;
		}else{
			Node preNode = head;
			Node postNode = head.next;
			while(postNode.next!=null){
				preNode = postNode;
				postNode = postNode.next;
			}
			preNode.next = null;
			return true;
		}
	}
	
	public Iterator iterator(){
		return null;
	}
	
	
	
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){		
		if(head.data==null){
			System.out.println("This is an empty linklist!!");
			return;
		}
		if(this.size()==1){
			System.out.println("It is itself!!");
			return;
		}
		else{
			Node preNode = head;
			Node curNode = head.next;
			Node postNode = curNode.next;
			head.next = null;
			while(postNode!=null){
				curNode.next = preNode;
				preNode = curNode;
				curNode = postNode;
				postNode = postNode.next;//如果while循环判断的是curNode!=null,那这里有问题，最后一轮循环postNode已经是null,所以postNode.next就会InvocationException
			}//while end
			curNode.next = preNode;
			head = curNode;
		}//else end
	}
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int index = (int)this.size()/2;
		Node curNode = new Node();
//		Node postNode = new Node();
		curNode = head;
//		postNode = head.next;
		while(index--!=0){
			curNode = curNode.next;
//			postNode = postNode.next;
		}
		head = curNode;
		head.next = curNode.next;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		if(i>=this.size() || i+length>this.size()){
			System.out.println("Error!!");
			return;
		}
		if(head.next==null){
			if(i+length == 0){//remove(0,0)则不删
				return;
			}else //remove(0,1) 删
				head.data = null;
				return;
		}
		else{
			Node curNode = head;
			Node postNode = head.next;
			while(--i!=0 && i>0){
				curNode = postNode;
				postNode = postNode.next;
			}//while end - loop 1
			Node delNode = postNode;
			Node delpostNode = postNode.next;
			while(--length!=0 && length>0){
				delNode = delpostNode;
				delpostNode = delNode.next;
			}//while end - loop 2
			delNode.next = null;
			curNode.next = delpostNode;
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
	
	//Why set this function as static? I change it to non-static.
	public int[] getElements(LinkedList list){
		int sublistSize = list.size();
		int listSize = this.size();
		if(Integer.parseInt(list.get(sublistSize-1).toString())>=listSize){
			System.out.println("Out of boundary!");
			return null;
		}//判断越界
		
		int[] results = new int[sublistSize];
		for(int i=0;i<sublistSize;i++){
			results[i] = Integer.parseInt(this.get((Integer.parseInt(list.get(i).toString()))).toString());
			System.out.println(results[i]);
		}
		return results;
		
	}


	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public void subtract(LinkedList list){
		Node aPoint = this.head;
		Node bPoint = list.head;
		Node aPreNode = aPoint;
		Boolean flag = true;
		while(flag){
			if((int)aPoint.data<(int)bPoint.data){
				aPreNode = aPoint;
				aPoint = aPoint.next;
			}
			if((int)aPoint.data>(int)bPoint.data){
				bPoint = bPoint.next;
				if(bPoint==null)
					break;
			}
			if((int)aPoint.data==(int)bPoint.data){
				if(aPoint.equals(this.head)){
					this.head = aPoint.next;
				}else{
				aPoint = aPoint.next;
				aPreNode.next = aPoint;
				bPoint = bPoint.next;
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node preNode = this.head;
		Node curNode = preNode.next;
		while(curNode!=null){
			if(Integer.parseInt(preNode.data.toString()) == Integer.parseInt(curNode.data.toString())){
				//思考，这里为什么不能用Integer.valueof(String s)?
				curNode = curNode.next;
				preNode.next = curNode;
				
			}else{
				preNode = curNode;
				curNode = curNode.next;
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
		if(min>max||min<0){
			return;
		}
		else{
			Node preNode = this.head;
			Node curNode = preNode.next;
			Node fixNode = new Node();
			while(Integer.parseInt(preNode.data.toString())<min&&Integer.parseInt(curNode.data.toString())<min){
				preNode = curNode;
				curNode = curNode.next;
			}
			if(Integer.parseInt(curNode.data.toString())>=min){
				fixNode = preNode;
				while(Integer.parseInt(preNode.data.toString())<=max&&Integer.parseInt(curNode.data.toString())<=max){
					preNode = curNode;
					curNode = curNode.next;
				}
				if(Integer.parseInt(curNode.data.toString())>max){
					fixNode.next = curNode;
				}
			}
		}
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		if(list.head==null){
			return this;
		}
		if(this.head==null){
			return list;
		}else{
			LinkedList clist = new LinkedList(); 
			Node aPoint = this.head;
			Node bPoint = list.head;
			while(aPoint != null && bPoint != null){
				if(Integer.parseInt(aPoint.data.toString())<Integer.parseInt(bPoint.data.toString())){
					clist.add(aPoint.data);
					aPoint = aPoint.next;
					continue;
				}
				if(Integer.parseInt(aPoint.data.toString())>Integer.parseInt(bPoint.data.toString())){
					clist.add(bPoint.data);
					bPoint = bPoint.next;
					continue;
				}
				if(Integer.parseInt(aPoint.data.toString())==Integer.parseInt(bPoint.data.toString())){
					clist.add(aPoint.data);
					aPoint = aPoint.next;
					bPoint = bPoint.next;
					continue;
				}
			}//end While
			while(aPoint!=null){
				clist.add(aPoint.data);
				aPoint = aPoint.next;
			}
			while(bPoint!=null){
				clist.add(bPoint.data);
				bPoint = bPoint.next;
			}
		return clist;
		}
		
	}
}
