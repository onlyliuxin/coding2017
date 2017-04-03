package com.coding.week1;
import java.util.List;
import java.util.ArrayList;

public class LinkedList1 {
    private Node head;
	private int size;
	public void add(Object o){
		Node node = new Node(o,null);
		node(size-1).next = node;
		size++;
	}
	public void add(int index , Object o){
		if(index<0){
			return;
		}
		Node node = new Node(o,null);
		if(index==0){
			head=node;
			node.next = node(0);
		}else{
			node(index-1).next = node;
			node.next = node(index+1);
		}
		size++;
	}
	public Object get(int index){
		if(index<0){
			return null;
		}
		return node(index);
	}
	public Object remove(int index){
		if(index<0){
			return null;
		}
		Object o =node(index).data;
		node(index-1).next = node(index+1);
		size--;
		return o;
	}
	
	public int size(){
		return -1;
	}
	
	public void addFirst(Object o){
		Node n = new Node(o,null);
		head = n;
		if(size>0){
			n.next = node(0);
		}
		size++;
		
	}
	public void addLast(Object o){
		Node n = new Node(o,null);
		if(size>0){
			node(size-1).next = n;
		}else{
			head = n;
		}
		size++;
		
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
	}
	public Iterator iterator(){
		return new Ito();
	}
	public class Ito implements Iterator{
		int cursor;
		@Override
		public boolean hasNext() {
			if(cursor!=size){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if(cursor>=size-1){
				return null;
			}
			Object o=node(cursor).data;
			cursor++;
			return o;
			
		}
		
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		public Node(Object data,Node next){
			this.data = data;
			this.next = next;
		}
		
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		Node x = node(size-1);
		head = x;
		for(int i=size-2;i>=0;i--){
			x.next = node(i);
			x = node(i);
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int newSize = size/2+size%2;
		head = node(newSize-1);
		size =  newSize;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
		if(i==0){
			head = node(length);
		}
		node(i-1).next = node(i+length);
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
		int[] temp = new int[size];
		for(int i=0;i<size;i++){
			 String o= (String)node(i).data;
			 int j=0;
			 while(j<list.size()){
				 if(o.indexOf((String)list.get(j))>-1){
					 temp[0]=Integer.parseInt(o);
					 j++;
				 } 
			 }
			 
		}
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		int[] temp = new int[size];
		int newSize = 0;
		for(int i=0;i<size;i++){
			 String o= (String)node(i).data;
			 for(int j=0;j<list.size();j++){
				 if(list.get(j).equals(o)){
					 if(i+1<size){
						 node(i-1).next = node(i+1);
						 newSize--;
					 }else{
						 node(i-1).next = null;
						 
					 }
					 
				 }
			 }
		}
		size = newSize;
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node now = head;
		while(now.next!=null){
			if(now.data.equals(now.next.data)){
				if(now.next.next!=null){
					now.next = now.next.next;
				}
			}
			if(now.next!=null){
				now = now.next;
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
		Node n = head;
		List<Node> lists = new ArrayList<Node>();
		while(true){
			if((int)n.data>min&&(int)n.data<max){
				return;
			}
			lists.add(n);
			if(n.next==null){
				break;
			}
			n = n.next;
		}
		head = lists.get(0);
		Node node = head;
		for(int i=1;i<lists.size();i++){
			node.next = lists.get(i);
			
			node = node.next;
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList1 intersection( LinkedList1 list){
		LinkedList1 l = new LinkedList1();
		Node n1 = head;
		Node n2 = (Node) list.get(0);;
		while(n1!=null){
			while(n2!=null){
				if(n1.data.equals(n2.data)){
					l.add(n1);
				}
				n2 = n2.next;
			}
			n1 = n1.next;
		}
		return l;
	}
	public Node node(int index){
		if(index<0){
			return null;
		}
		if(index==0){
			return head;
		}
		Node x =head;
		for(int i=0;i<index;i++ ){
				x = x.next;
		}
		
		return x;
	}
}
