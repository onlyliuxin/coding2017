package com.coding.basic;

import java.util.Stack;

public class LinkedList implements List {
	private int size;
	
	private Node head;
	
	public LinkedList(){
		size = 0;
		
		head = null;
	}
	
	public void add(Object o){
		Node nd = new Node(o);
		if(head == null){
			head = nd;
		}else{
			Node p = head;
			while(p.next != null){
				p = p.next;
			}
			p.next = nd;
		}
		size ++;
		
	}
	//3 > 2 > 1 > 5 > 4 > 5改变之前
	//0   1   2   3   4   5index
	//3 > 2 > 1 > x > 5 > 4 > 5插入之后
	public void add(int index , Object o){
		if(head != null){
			int k = 0;
			Node p = head;
			while(k < index - 1 && p.next != null){
				k++;
				p = p.next;//当前p为要插入位置的前一个节点
			}
			
			if(p != null){
				Node nd = new Node(o);
				nd.next = p.next;
				p.next = nd;
			}
			
			size++;
			
		}
	}
	public Object get(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		Node p = head;
		int k = 0;
		while(k < index && p.next !=null){
			k++;
			p = p.next;
		}
		return p.data;
	}
	//3 > 2 > 1 > 5 > 4 > 5改变之前
	//0   1   2   3   4   5index
	//3 > 2 > 1 > 4 > 5插入之后
	public Object remove(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		if(head == null){
			return null;
		}
		if(index == 0){
			head = head.next;
			size--;
			return head.data;
		}else{
			if(head != null){
				int k = 0;
				Node p = head;
				while(k < index - 1 && p != null){
					k++;
					p = p.next;
				}
				Node pn = p.next;
				if(pn != null){
					p.next = pn.next;
					size--;
					return pn.data;
				}
				
			}
		}
		return null;
	}
	
	public int size(){
		
		return size;
	}
	
	public void addFirst(Object o){
		if(head != null){
			Node nd = new Node(o);
			Node first = head;
			head = nd;
			first = nd.next;
		}
	}
	public void addLast(Object o){
		if(head != null){
			int k = 0;
			Node p = head;
			while(p.next != null && k < size - 1){
				p = p.next;
				k++;
			}
			Node newNode = new Node(o);
			p.next = newNode;
		}
	}
	public Object removeFirst(){
		Node node = head;
		if(head != null){
			head = head.next;
		}
		return node.data;
	} 
	public Object removeLast(){
		Node p = head;
		int k = 0;
		while(p.next != null && k < size - 2){
			k++;
			p = p.next;
		}
		
		p.next = null;
		return p.next;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
		private Node(Object o){
			this.data = o;
			this.next = null;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 			head   				 head
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		/**
		 * 长度超过1的单链表需要逆转
		 */
		if(head == null || head.next == null){
			return;
		}
		Stack<Node> st = new Stack<Node>();
		Node currentNode = head;
		while(currentNode != null){
			st.push(currentNode);
			Node nextNode = currentNode.next;
			currentNode.next = null;//断开连接
			currentNode = nextNode;
		}
		
		head = (Node) st.pop();
		currentNode = head;
		while(!st.isEmpty()){
			Node nextNode = (Node) st.pop();
			currentNode.next = nextNode;
			currentNode = nextNode;
		}
	}
	
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public void removeFirstHalf(){
		int num = size / 2;
		for(int i = 0; i < num; i++){
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始,删除length个元素 ,注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i < 0 || i >= size){
			throw new IndexOutOfBoundsException();
		}
		int len = size - i >= length ? length : size - i;
		int k = 0;
		while(k < len){
			remove(i);
			k++;
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
	public  int[] getElements(LinkedList list){
		int[] newList = new int[list.size()];
		for(int i = 0;i < list.size(); i++){
			newList[i] = Integer.parseInt(this.get(Integer.parseInt(list.get(i).toString())).toString());   
		}
		return newList;
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public void subtract(LinkedList list){
		for(int j = 0;j < list.size();j++){
			this.remove(list.get(j));
		}
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		if(head == null){
			throw new RuntimeException("LinkedList is null");
			
		}
		Node currentNode = head;
		Node preNode = head;
		while(currentNode.next != null){
			currentNode = currentNode.next;
			Object data = preNode.data;
			while(currentNode.data == data){
				if(currentNode.next == null){
					preNode.next = null;
					break;
				}
				preNode.next = currentNode.next;
				size--;
				currentNode = currentNode.next;
				if(currentNode == null){
					break;
				}
			}
			preNode = preNode.next;
		}
	}
	/**
	 * 传入删除数据节点
	 */
	public void remove(Object obj){
		if(head == null){
			throw new RuntimeException("linkedlist is nuull");
			
		}	
		if(head.data.equals(obj)){
			head = head.next;
			size--;
		}else{
			Node pre = head;
			Node currentNode = head.next;
			while(currentNode != null){
				if(currentNode.data.equals(obj)){
					pre.next = currentNode.next;
					size--;
				}
				pre = pre.next;
				currentNode = currentNode.next;
			}
		}
	}
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max){
		Node node = head;
		int start = -1;
		int end = -1;
		int i = 0;
		while(node != null){
			if((Integer)node.data <= min){
				start = i;
			}
			if((Integer)node.data >= max){
				end = i;
				break;                                                   
			}
			node = node.next;
			i++;
		}
		if(start == -1){
			start = 0;
		}
		if(end == -1){
			end = size;
		}
		this.remove(start+1, end-start-1);
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList intersection(LinkedList list){
		if(list == null){
			return null;
		}
		
		LinkedList newList = new LinkedList();
		int fi = 0;
		int se = 0;
		while(fi < this.size && se < list.size()){
			int val1 = (Integer) this.get(fi);
			int val2 = (Integer) list.get(se);
			if(val1 == val2){
				newList.add(val1);
				fi++;
				se++;
			}else if(val1 < val2){
				fi++;
			}else{
				se++;
			}
			
		}
		return newList;
	}
	
	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.add(11);
		linkedList.add(22);
		linkedList.add(33);
		linkedList.add(44);
		linkedList.add(55);
		linkedList.reverse();
		for(int i = 0; i < linkedList.size; i++){
			System.out.println(linkedList.get(i));
		}
	}
}
