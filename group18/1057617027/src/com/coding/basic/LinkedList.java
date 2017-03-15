package com.coding.basic;

public class LinkedList implements List {
	
	private Node head = new Node(null,null);
	private Node last;
	private int size;
	private static class Node{
		Object data;
		Node next;
		Node(Object data,Node next){
			this.data = data;
			this.next = next;
		}
	}
	public void add(Object o){
		if(size==0){
		Node node = new Node(o,null);
		head = node;
		last = node;
		size++;
		}else{
			Node node = new Node(o,null);
			last.next = node;
			last = node;
			size++;
		}

	}
	public void add(int index , Object o){
		if(index>size){
			System.out.println("�����"+index+"���ڵ�ǰ��"+size);
		}
		Node n = head;
		Node n1 = head;
		for(int i=0;i<index-1;i++){
			n1 = n1.next;
		}
		for(int i=0;i<index;i++){
			n = n.next;
		}
		Node newnode = new Node(o,n);
		n1.next = newnode;
		newnode.next = n;
//		if(index+1<=size){
//			last = n;
//		}
		size++;
	}
	public Object get(int index){
		Node n =head;
		if(index==size){
			return last.data;
		}
		if(index==0){
			return n.data;
		}
		for(int i=0;i<index;i++){
			n =n.next;
		}
		return n.data;
	}
	public Node getnode(int index){
		Node n =head;
		for(int i=0;i<index;i++){
			n =n.next;
		}
		return n;
	}
	public Object remove(int index){
		Node n =head;
		if(index == 0){
			return removeFirst();
		}
		if(index == size){
			return removeLast();
		}
		for(int i =0 ;i<index-1;i++){
			n=n.next;
		}
		n.next=n.next.next;
		size--;
		return n.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o,null);
		node.next = head;
		head = node;
		size++;
	}
	public void addLast(Object o){
		Node node = new Node(o,null);
		last.next = node;
		last =node;
		
	}
	public Object removeFirst(){
	    head=head.next;
	    size--;
		return null;
	}
	public Object removeLast(){
		Node nn = head;
		for(int i = 0;i<size-2;i++){
			nn = nn.next;
		}
		nn.next = null;
		last = nn;
		size--;
		return null;
		
	}
	public Iterator iterator(){
		return new Iter();
	}
	private int nextnum; 
	public class Iter implements Iterator{
		
		@Override
		public boolean hasNext() {
			
			return nextnum!=size;
		}

		@Override
		public Object next() {
			return get(nextnum++);
		}
	}
		/**
		 * 把该链表逆置
		 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
		 */
		public  void reverse(){
			int count = 0;
			for(int i = 0;i<size/2;i++){
				Object temp = getnode(i).data;
				getnode(i).data = getnode(size-i-1).data;
				getnode(size-i-1).data = temp;
			}
		}
		
		/**
		 * 删除一个单链表的前半部分
		 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
		 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
		 */
		public  void removeFirstHalf(){
			head.data = get(size/2);
			head.next = getnode(size/2+1);
			size = size - size/2;
		}
		
		/**
		 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
		 * @param i
		 * @param length
		 */
		public  void remove(int i, int length){
			getnode(i-1).next = getnode(i+length);
			size = size -length;
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
			int[] ret = new int[list.size()];
			for(int i = 0;i<list.size();i++){
				if((Integer)list.get(i)<size){
				ret[i] = (Integer) get((Integer)list.get(i));
				}else{
					System.out.println("越界");
				}
			}
			return ret;
		}
		
		/**
		 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
		 * 从当前链表中中删除在listB中出现的元素 
		 * @param list
		 */
		
		public  void subtract(LinkedList list){
			int count = 0;
			for(int i = 0;i<list.size();i++){
				remove((Integer)list.get(i)-count++);
			}
		}
		
		/**
		 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
		 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
		 */
		public  void removeDuplicateValues(){
			System.out.println(get(3)==get(4));
			for(int i = 0;i<size-1;i++){
				if((Integer)get(i)-(Integer)get(i+1)==0){
					remove(i+1);
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
			if(max<(Integer)get(0)){	
			}else if(min>(Integer)get(size-1)){
			}else{
			}
			
		}
		//300
		//11->101->201->301->401->501->601->701
		public void binary(int n){
			int i = size/2;
			int p=0;
			Boolean flag = true;
			while(flag){
			if(n>(Integer)get(i)){
				p =i;
				i =i+i/2;
			}else if(n<(Integer)get(i)){
				p = i;
				i = i-i/2;
			}else{
				flag = false;
			}
			System.out.println(i+"fdafda"+p);
			if(Math.abs(p-i)==1){
				flag = false;
				if(n<(Integer)get(i)){
					i--;
				}else if(n>(Integer)get(i)){
					i++;
				}
			}
		}
			System.out.println(i);
		}
		
		/**
		 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
		 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
		 * @param list
		 */
		public  LinkedList intersection( LinkedList list){
			return null;
		}

	public static void main(String args[]){
		LinkedList l = new LinkedList();
//		l.add(1);
//		l.add(2);
//		l.add(3);
//		l.add(4);
//		l.add(5);
//		l.add(2, "wo");
//		l.removeFirst();
//		l.addFirst(1);
//		l.removeLast();
//		l.addLast(4);
//		l.reverse();
//		l.removeFirstHalf();
//		l.remove(1,2);
		
//		 * 例如当前链表 = 11->101->201->301->401->501->601->701
//		 * listB = 1->3->4->6
//		 * 返回的结果应该是[101,301,401,601]  
//		l.add(11);
//		l.add(101);
//		l.add(201);
//		l.add(301);
//		l.add(401);
//		l.add(501);
//		l.add(601);
//		l.add(701);
//		LinkedList list = new LinkedList();
//		list.add(1);
//		list.add(3);
//		list.add(4);
//		list.add(6);
//		l.subtract(list);
//		while(l.iterator().hasNext()){
//			System.out.println(l.iterator().next());
//		}
//		for(int i = 0;i<l.getElements(list).length;i++){
//			System.out.println(l.getElements(list)[i]);
//		}
		l.add(11);
		l.add(101);
		l.add(201);
		l.add(301);
		l.add(401);
		l.add(401);
		l.add(601);
		l.add(701);
		l.binary(600);
//		l.removeDuplicateValues();
//		while(l.iterator().hasNext()){
//		System.out.println(l.iterator().next());
//	}
	}
}
