package com.github.mrwengq.tid.list;

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

	private Node findNode(int index) {//用于查找节点
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
		if(size==0){
			throw new RuntimeException();
		}
		int cs = size/2;
		int endIndex = size -1;
		for(int i = 0;i<cs;i++){ 
			Node node1 = findNode(i);
			Node node2 = findNode(endIndex-i);
			Node BeforNode1 = null; //node1前一个节点
			Node AfterNode2 = null;//node2 后一个节点
			if(i != 0 ){
				BeforNode1 = findNode(i-1);
				AfterNode2 = findNode(endIndex-i).next;
			}
			Node AfterNode1 = findNode(i).next;
			Node BeforNode2 = findNode(endIndex-i-1);
			if(BeforNode1!= null && AfterNode2!=null){
				BeforNode1.next = node2;
				node1.next = AfterNode2; 
			}
			BeforNode2.next = node1;
			node2.next = AfterNode1; 
			if(i==0){
				head = node2;
			}
			
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if(size<2){
			throw new RuntimeException();
		}
		
		int len  = size/2;
		Node node = findNode(len-1);//len-1为删除链表的最后一位下标
		for( int j = len-2; j>=0 ; j--){
			Node temp = findNode(j);
			temp.next = null;
		}
		head = node.next;
		node.next = null;
		size -= len;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(0 == size||i > size-1){
			throw new RuntimeException();
		}
		Node beforNode = findNode(i-1); //i的前一个元素
		Node afterNode = findNode(i+length);//被删除最大下标节点的下一个节点
		for( int j = i+length-1; j<i ; j--){
			Node temp = findNode(j);
			temp.next = null;
		}
		beforNode.next = afterNode;
		size -=length;
		
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
		int temp = list.size()-1; 
		if(temp>size){
			throw new RuntimeException();
		}
		int[] b = new int[list.size()];
		for(int i = 0;i<list.size;i++){
			temp = (int)list.get(i);
			Node no = head;
			while(temp>0){
				for (; temp > 0; temp--)
					no = no.next;	
			}
			b[i] = (int)no.data;
		}
		return b;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		if(list==null){
			throw new RuntimeException();
		}
		for(int i = 0;i<list.size();i++){
			for(int j = 0;j<this.size();j++){
				if((int)this.get(j)==(int)list.get(i)){
					System.out.println(this.get(j)+"    " +j);
					this.remove(j);
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
		int len = size -1;
		for(int i = 0;i<len;i++){
			int next = i+1;
			while((int)this.get(i)==(int)this.get(next)){
				this.remove(next);
				len = this.size()-1;
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
		int lmin = 0;
		int lmax = size-1;
		int lmid = (int)size-1/2;
		int rmin = 0;//在需要删除的集合中的最小值
		int rmax = 0;//在需要删除的集合中的最大值
		while(lmin<lmax){
			lmid = (lmax+lmin)/2;
			if((int)this.get(lmid) > min){
				lmax = lmid-1;
				if((int)this.get(lmax)<min){
				 rmin = lmid;
				 break;
				}
			}else if((int)this.get(lmid) <= min){
				lmin = lmid+1;
				if((int)this.get(lmin)>min){
					rmin = lmin;
					break;
				}			
			}
		}
		while(lmin<lmax){
			lmid = (lmax+lmin)/2;
			if((int)this.get(lmid)<max){
				lmin = lmid +1;
				if((int)this.get(lmin)>max){
					rmax = lmid;
				}
			}else if((int)this.get(lmid)>=max){
				lmax = lmid -1;
				if((int)this.get(lmax)<max){
					rmax = lmax;
				}
			}
		}
		Node beforNode = findNode(rmin-1);
		Node afterNode = findNode(rmax+1);
		for(int i = rmax;i>=rmin;i--){
			Node removeNode = findNode(i);
			removeNode.next = null;
			size--;
		}
		beforNode.next = afterNode;
	}

	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		int len = size;
		int llen = list.size();
		int i = 0;
		int j = 0;
		LinkedList ll= new LinkedList();
		while(true){
			if(i == len &&j == llen ){
				break;
			}
			if(i>len-1){
				ll.add(list.get(j++));
				continue;
			}
			if(j>llen-1){
				ll.add(this.get(i++));
				continue;
			}
			if((int)get(i)<(int)list.get(j)){
				ll.add(this.get(i++));
			}else if((int)get(i)>(int)list.get(j)){
				ll.add(list.get(j++));
			}else{
				ll.add(list.get(j++));
				i++;
			}
		}
		return ll;
	}
}
