package com.coding.basic;



public class LinkedList implements List {

	private Node head;
	private int size;
	private Node currrent;
	
	public void add(Object o) {
		add(size, o);
	}
	
	public void add(int index, Object o) {
		if (o==null) {
			return;
		}
		if (index<0||index>size) {
			throw new IndexOutOfBoundsException();
		}
		Node node=new Node(o);
		if (index==0) {
			if (head==null) {
				head.data=o;
			}
			else {
				node.next=head;
				head=node;
			}
		}
		else{
			Node current=head;
			for (int i = 0; i < index-1; i++) {
				current=current.next;
			}
			if (current.next!=null) {
				node.next=current.next;
			}
			current.next=node;
		}
		size++;
	}

	public Object get(int index) {
		if (index<0||index>=size) {
			throw new IndexOutOfBoundsException();
		}
		Node current=head;
		for (int i = 0; i < index; i++) {
			current=current.next;
		}
		return current.data;
	}

	public Object remove(int index) {
		if (index<0||index>=size) {
			throw new IndexOutOfBoundsException();
		}
		if (size==0) {
			return null;
		}
		Node current=head;
		Node node=null;
		if (index==0) {
			node=head;
			head=head.next;
		}
		else {
			for (int i = 0; i < index-1; i++) {
				current=current.next;
			}
			node=current.next;
			if (current.next.next==null) {
				current.next=null;
			}
			else {
				current.next=current.next.next;
			}
		}
		size--;
		return node.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		add(0, o);
	}

	public void addLast(Object o) {
		add(size, o);
	}

	public Object removeFirst() {
		return remove(0);
	}

	public Object removeLast() {
		return remove(size-1);
	}

	public Iterator iterator() {
		return null;
	}

	private static class Node {
		Object data;
		Node next;
		
		public Node(){
			data=null;
			next=null;
		}
		
		public Node(Object object){
			data=object;
		}
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if (size==0||size==1) {
			return;
		}
		if (size==2) {
			this.head.next.next=this.head;
			this.head=this.head.next;
			this.head.next.next=null;
		}
		Node head=null;
		Node pre=this.head;
		Node current=null;
		int length=size;
		for (int i = 0; i < size-2; i++) {
			pre=pre.next;
		}
		current=pre.next;
		current.next=pre;
		head=current;
		current=pre;
		pre=this.head;
		length--;
		while (length>2) {
			for (int i = 0; i <length-2; i++) {
				pre=pre.next;
			}
			current.next=pre;
			current=pre;
			pre=this.head;
			length--;
		}
		current.next=pre;
		pre.next=null;
		this.head=head;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {
		for (int i = 0; i < size/2; i++) {
			removeFirst();
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		for (int j = 0; j < length; j++) {
			remove(i);
			i++;
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		int size=list.size();
		int[] array=new int[size];
		for (int i = 0; i < size; i++) {
			int rank=(int) get(i);
			int value=(int) get(rank);
			array[i]=value;
		}
		return array;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		for (int i = 0; i < list.size(); i++) {
			Object object=list.get(i);
			for (int j = 0; j < size; j++) {
				if (object.toString().equals(get(j).toString())) {
					remove(j);
				}
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		for (int i = 0; i < size; i++) {
			Object object=get(i);
			int j=i+1;
			while (j<size) {
				if (!get(j).toString().equals(object.toString())) {
					if (j==i+1) {
						break;
					}
					remove(i+1,j-i );
					break;
				}
				j++;
			}
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		Node start=null;
		Node end=null;
		boolean findStart=false;
		Node current=head;
		int length=0;
		for (int i = 0; i < size; i++) {
			if (!findStart&&(int)current.next.data>min) {
				start=current;
			}
			if ((int)current.data>=max) {
				end=current;
				break;
			}
			current=current.next;
			if (findStart) {
				length++;
			}
		}
		size=size-length;
		if (start==null) {
			return;
		}
		if (end==null) {
			start.next=null;
			return;
		}
		start.next=end;
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		if (size==0) {
			return list;
		}
		if (list.size()==0) {
			return this;
		}
		LinkedList newList=new LinkedList();
		for (int i = 0; i < size; i++) {
			Object object=get(i);
			for (int j = 0; j < list.size(); j++) {
				if (object.toString().equals(list.get(j).toString())) {
					newList.add(object);
					break;
				}
			}
		}
		return newList;
	}

	public Node getCurrrent() {
		return currrent;
	}

	public void setCurrrent(Node currrent) {
		this.currrent = currrent;
	}

}
