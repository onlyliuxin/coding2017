package com.github.mrwengq.first;

public class LinkedList implements List {
	private Node head;
	private int size =0;
	private static class Node {

		Object data;
		Node next;

		public Node(Object o) {
			data = o;
			next = null;
		}
	}


	public void add(Object o) {
		if (size == 0) {
			head = new Node(o);
		} else {
			Node node = new Node(o);
			Node lastNode = findNode(size-1);
			lastNode.next = node;
		}
		size++;
	}

	private Node findNode(int index) {
		Node no = head;
			for (; index > 0; index--)
				no = no.next;			

		return no;
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size - 1)
			throw new ArrayIndexOutOfBoundsException();
		Node node = new Node(o);
		Node indexNode = findNode(index);
		if (index - 1 < 0) {
			node.next = indexNode;
			head = node;
			size++;
			return;
		} else {
			Node lastNode = findNode(index - 1);
			lastNode.next = node;
			node.next = indexNode;
			size++;
			return;
		}
	}

	public Object get(int index) {
		if (index < 0 || index > size - 1)
			throw new ArrayIndexOutOfBoundsException();
		else
			return findNode(index).data;
	}

	public Object remove(int index) {
		if (index < 0 || index > size - 1 || size == 0)
			throw new ArrayIndexOutOfBoundsException();
		Node indexNode = findNode(index);
		if (size == 1) {
			head = null;
			size = 0;
			return indexNode.data;
		}
		Node nextNode = null;
		Node lastNode = null;
		if (index + 1 <= size - 1)     //判断是否有下一位
			nextNode = findNode(index + 1);
		if (index - 1 >= 0)			  //判断是否有上一位
			lastNode = findNode(index - 1);
		if (lastNode == null) {
			head = nextNode;
			size--;
			return indexNode.data;
		}else if (nextNode == null) {
			lastNode.next = null;
			size--;
			return indexNode.data;
		} else {
			lastNode.next = nextNode;
			size--;
			return indexNode.data;
		}
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node node = new Node(o);
		if (size == 0) {
			head = node;
			size++;
			return;
		} else {
			node.next = head;
			head = node;
			size++;
			return;
		}
	}

	public void addLast(Object o) {
		Node node = new Node(o);
		if (size == 0) {
			head = node;
			size++;
			return;
		} else {
			Node lastNode = findNode(size-1);
			lastNode.next = node;
			size++;
			return;
		}
	}

	public Object removeFirst() {
		if (size == 0) {
			return null;
		} else {
			Node nextNode = head.next;
			Object ob = head.data;
			head = nextNode;
			size--;
			return ob;
		}
	}

	public Object removeLast() {
		if (size == 0) {
			return null;
		} else {
			Node node = findNode(size-1);  //size -1 为最后一位  -2为前一位
			if(size-2>=0){
				Node lastNode = findNode(size - 2);
				lastNode.next = null;				
			}
			size--;
			return node.data;
		}
	}

	public Iterator iterator() {
		return new Iterator() {
			
			int index = -1;

			public boolean hasNext() {
				index++;
				if(index<size){
					
					Object ob = findNode(index);
					return true;
				}
				return false;
			}

			public Object next() {
				return findNode(index).data;
			}

		
		};
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
