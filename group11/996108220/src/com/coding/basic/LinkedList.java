package com.coding.basic;

import java.util.NoSuchElementException;


public class LinkedList <T extends Comparable> implements List {
	
	private Node head;
	private int size=0;
/**
 * 增加节点	
 */
	public void add(T o){
		if(size==0){
			head=new Node();
			head.data=o;
			head.next=null;
			size++;
			}
		else{
			addLast(o);
			}
			
	}
/**
 * 在index(0~size)处添加元素
 */
	public void add(int index , T o){
		
		if(index<0||index>size){
			System.out.println("index超出范围"+index);
			return;
			}
		if(index==0)addFirst( o);
		else if(index==size)addLast(o);
		else{
			Node ptr=head;
			for(int i=1;i<index;i++){
				ptr=ptr.next;
			}
			Node node=new Node();
			node.data=o;
			node.next=ptr.next;
			ptr.next=node;		
		}
		size++;
	}
/**
 * 获得index(0~size-1)的元素
 */
	public Object get(int index){
		if(index<0||index>=size)return null;
		else{
			Node ptr=head;
			for(int i=0;i<index;i++){
				ptr=head.next;
			}
			return ptr;
		}
	}
	public Node getNode(int index) {
		if (index<0||index>=size) {
			return null;
		}
		Node ptrNode=head;
		for (int i = 0; i < index; i++) {
			ptrNode=ptrNode.next;
		}
		return ptrNode;
	}
/**
 * 移除index(0~size-1)的元素
 */
	public Object remove(int index){
		if(index<0||index>=size)return null;
		else if(index==0)return removeFirst();
		else if(index==size-1)return removeLast();
		else{
			Node ptr=head;
			for(int i=1;i<index;i++){
				ptr=ptr.next;
				}
			Node node=ptr.next;
			ptr.next=node.next;
			size--;	
			return node.data;
			}	
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(T o){
		Node ptr=head;
		head=new Node();
		head.data=o;
		head.next=ptr;	
		size++;
	}
	public void addLast(T o){
		Node ptr=head;
		for(int i=1;i<size;i++){
			ptr=ptr.next;
		}
		Node node=new Node();
		node.data=o;
		node.next=null;	
		ptr.next=node;
		size++;
		
	}
	
	public Object removeFirst(){
		Node ptr=head.next;
		head.next=null;
		Object o=head.data;
		head=ptr;
		size--;
		return o;
	}
	public Object removeLast(){
		Node ptr=head;
		for(int i=1;i<size-1;i++){
			ptr=ptr.next;
		}
		Node node=ptr.next;
		ptr.next=null;
		size--;
		return node.data;
	}
	public Iterator iterator(){
		return new ListItr();
	}
    private class ListItr implements Iterator {
    
        private Node next=null;
        private int nextIndex;

        ListItr() {
            next = head;
            nextIndex = 0;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Node lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }
    }
	
	private static  class Node <T extends Comparable>{
		T data;
		Node next;	
	}
	public static void main(String args[]){
	LinkedList list=new LinkedList();
	list.add(1);
	list.add(2);
	list.add(2);
	list.add(3);
	list.add(4);
	list.add(4);
	list.add(5);
	list.add(6);
	list.add(6);
	LinkedList list2=new LinkedList();
	list2.add(0);
	list2.add(0);
	list2.add(1);
	list2.add(2);
	list2.add(3);
	list2.add(5);
	list2.add(6);
	list2.add(7);
	list.removeRange(5,7);
	Iterator itr=list.iterator();
	while (itr.hasNext()) {
		System.out.println(itr.next());
		
	}
	}
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		if (size==0||size==1) {
			return ;
		}
		else if (size==2) {
			 Node ptr=head.next;
			 head.next=null;
			 ptr.next=head;
			 head=ptr;
		}
		else{
			Node pre=head;
			Node ptr=head.next;
			while (ptr.next!=null) {
				Node node=ptr.next;
				ptr.next=pre;
				pre=ptr;
				ptr=node;
			}
			ptr.next=pre;
			head.next=null;
			head=ptr;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		Node ptr=head;
		for (int i = 1; i < size/2; i++) {
			ptr=ptr.next;
		}
		head=ptr.next;
		size=size-size/2;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if (i>=size||i<0||i+length-1>=size) {
			return;
		}
		Node ptr=head;
		if (i==0) {
			for (int j = 0; j < length; j++) {
				ptr=ptr.next;
			}
			head=ptr;
		}
		else {
			ptr=head;
			Node pre=null;
			for (int j = 0; j < i+length; j++) {
				if (j==i-1) {
					pre=ptr;
				}
				ptr=ptr.next;
			}
			pre.next=ptr;	
		}
		size=size-length;
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
		Node ptrNode=list.head;
		int[] array=new int[list.size()];
		int i=0;
		while (ptrNode!=null) {
			int index=Integer.parseInt(String.valueOf(ptrNode.data));
			array[i++]=Integer.parseInt(String.valueOf(this.getNode(index).data));	
			ptrNode=ptrNode.next;
		}
		return array;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList<T> list){
		Node ptr=list.head;
		if (list.size()==0||size==0) {
			return;
		}	
		while (ptr!=null) {
				delete(ptr.data);
				ptr=ptr.next;		
		}
		
	}
	
	private void delete(Comparable data) {
		if (size==0) {
			return;
		}
		else if (size==1) {
			if (head.data.compareTo(data)==0) {
				head=null;
				size--;
			}
		}
		else  {
			if (head.data.compareTo(data)==0) {
				size--;
				head=size==0?null:head.next;
				return;
				}
			Node pre=head; 
			Node ptr=head.next;
			do{
				if (ptr.data.compareTo(data)==0) {
					pre.next=ptr.next;
					size--;
					break;
				}
				else {
					pre=ptr;
					ptr=ptr.next;
				}
				}while (ptr!=null);
			}
		
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		removeDuplicateValues(head);
	}
	
	private void removeDuplicateValues(Node node) {
		if (node.next==null||node==null) {
			return;
		}
		else {
			if (node.data.compareTo(node.next.data)==0) {
				node.next=node.next.next;
				size--;
				removeDuplicateValues(node);
			}
			else {
				removeDuplicateValues(node.next);
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
		if (size==0) {
			return;
		}
		else if (size==1) {
			if (head.data.compareTo(min)>0&&head.data.compareTo(max)<0) {
				head=null;
				size--;
			}
			else {
				return;
			}
		}
		else {
			Node pre=null;
			Node ptr=head;
			Node tail=null;
			int num=0;
			
			if (ptr==head&&(head.data.compareTo(min)>0&&head.data.compareTo(max)<0)) {
				while (ptr!=null) {
					if (ptr.data.compareTo(max)>=0) {
						head=ptr;
						size=size-num;
						return;
					}
					else {
						ptr=ptr.next;
						num++;
					}	
				}
				size=0;
				head=null;
				return;
			}
			else if (head.data.compareTo(min)<=0) {
				pre=head;
				ptr=head.next;
				while (ptr!=null) {
					if (ptr.data.compareTo(min)<=0) {
						pre=ptr;
						ptr=ptr.next;
					}
					else if (ptr.data.compareTo(min)>0&&ptr.data.compareTo(max)<0) {
						ptr=ptr.next;
						num++;
					}
					else {
						tail=ptr;
						break;
					}	
				}
				if (pre==head) {
					return;
				}
				else if (pre!=head&&tail!=null) {
					size=size-num;
					pre.next=tail;
				}else {
					pre.next=null;
					size=size-num;
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
		LinkedList linkedList=new LinkedList();
		Node node1=head; Node node2=list.head;
		while (node1!=null&&node2!=null) {
			if (node1.data==node2.data) {
				linkedList.add(node1.data);
				node1=node1.next;
				node2=node2.next;
			}
			else if (Integer.parseInt(String.valueOf(node1.data))<Integer.parseInt(String.valueOf(node2.data))) {
				node1=node1.next;
			}
			else {
				node2=node2.next;
			}
			
		}
		return linkedList;
	}
	@Override
	public void add(Object o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void add(int index, Object o) {
		// TODO Auto-generated method stub
		
	}
}
