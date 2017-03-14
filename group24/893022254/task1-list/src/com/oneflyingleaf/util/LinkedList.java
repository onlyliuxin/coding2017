package com.oneflyingleaf.util;



public class LinkedList implements List {
	
	private Node head;

	//虚拟tail节点,方便add最后节点，减少成,tail为链表的最后一个元素
	private Node tail;
	
	private int size ; 
	
	public void add(Object o){
		size ++ ;
		
		if(head == null){
			head = new Node();
			
			head.data = o;
			
			tail = head;
			
			return ;
		}
		
		Node node = tail;
		tail = new Node();

		node.next = tail;
		tail.data = o;
		
	}
	public void add(int index , Object o){
		checkBound(index,true);
		if(index == 0){
			addFirst(o);
			return ;
		} 
		
		if(index == (size - 1)){
			add(o);
			return ;
		}
		
		size ++;
		Node node = head;
		while(index-- > 0){
			node = node.next;
		}
		
		Node temp = new Node();
		temp.data = o;
		temp.next = node.next;
		
		node.next = temp;
		
	}
	public Object get(int index){
		checkBound(index,false);
		
		Node node = head;
		
		while(index > 0){
			index -- ;
			node = node.next;
		}
		
		return node.data;
	}

	public Object remove(int index){
		checkBound(index,false);
		
		if(index == 0){
			removeFirst();
		}
		if(index == (size-1)){
			removeLast();
		}
		
		size --;
		
		Node node = head;
		while(--index > 0){
			node = node.next;
		}
		Object o = node.next.data;
		node.next = node.next.next; 
		
		return o;
	}
	
	public int size(){
		return size ;
	}
	
	public void addFirst(Object o){
		size ++ ;

		Node node = new Node();
		node.data = o;
		node.next = head;
		head = node;
	}
	public void addLast(Object o){
		add(o);
	}
	
	
	public Object removeFirst(){
		checkBound(0,true);

		if(size == 0){
			//处理tail节点，防止占用引用资源回收不了
			tail.data = null;
		}
		size -- ;

		Object o = head.data;
	
		head = head.next;
		
		return o;
	}

	public Object removeLast(){
		if(size == 0){
			//直接移除首节点，则无需处理尾节点
			removeFirst();
		}
		size -- ;
		
		Object o = tail.data;
		
		tail = null;
		
		Node temp = head ;
		while(temp != null){
			temp = temp.next;
		}
		
		tail = temp;
		return o;
		
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){	
		Node node = head.next;
		Node pre = head;

		Node next;
		while(node.next != null){
			next = node.next;
			
			node.next = pre;
			
			pre = node;
			node = next;
		}
		//处理尾元素
		node.next = pre;
		
		next = tail;
		tail = head;
		head = next;
		
		tail.next = null;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int size = this.size / 2;
		
		for(int i = 0;i < size ; i++){
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for(int j = i;length > 0; length -- ,j++ ){
			remove(j);
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
	public Object[] getElements(LinkedList list){
		Object []  ret = new Object[list.size];
		
		for(int i = 0 ;i < list.size;i++){
			ret[i] = get((int) list.get(i));
		}
		return ret;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		int size = this.size > list.size()?this.size : list.size();
		
		int temp = 0;
		for(int i = 0;i < size ;i++){
			if(temp == size || i == list.size){
				return ;
			}
			
			
			if((int)list.get(i) > (int)get(temp)){
				temp ++;
			}else if((int)list.get(i) < (int)get(temp)){
				continue;
			}else{
				remove(temp);
			}
			
		}
		
		

	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if(size == 0){
			return ;
		}
		
		Object o = get(0);
		
		for(int i = 0;i < (size - 1) ;i++){
			if(o.equals(get(i + 1))){
				remove(i + 1);
				i--;
			}
			o = get(i);
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		if(size == 0 || max < (int)get(0) || min > (int)get(size - 1)){
			return ;
		}
		int minIndex = getIndex(min,0,size);
		int maxIndex = getIndex(max,0,size);

		remove(minIndex,maxIndex - minIndex );		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList ret = new LinkedList();
		
		int temp = 0;
		for(int i = 0;i < size ;i++){
			if(temp == size || i == list.size){
				return ret ;
			}
			
			
			if((int)list.get(i) > (int)get(temp)){
				temp ++;
			}else if((int)list.get(i) < (int)get(temp)){
				continue;
			}else{
				ret.add(list.get(i));
			}
			
		}
		return ret ;
	}
	
	
	private class LinkedListIterator implements Iterator{
		Node node = null;
		@Override
		public boolean hasNext() {
			if(node == null){
				return head != null;
			}
			return node.next != null;
		}

		@Override
		public Object next() {
			if(node == null){
				node = head;
			}else{
				node = node.next;
			}
			return node.data;
		}
		
	}
	
	/**
	 * 数组越界提示
	 * @param index 数组下标 
	 * @param contailLast
	 */
	private void checkBound(int index , boolean containLast){
		if(containLast && index == (size + 1) ){
			return ;
		}
		
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("idnex:" + index +", size:" + size);
		}
	}
	
	/**
	 * 获取不大于val的下标
	 * @return 
	 */
	private int getIndex(int val,int min ,int max){
		int mid = (0 + size)/2 ;
		if(max <= min){
			return min;
		}
		if(val > (int)get(mid)){
			mid = (mid + 1 + max) /2 ;
			return getIndex(val,mid,max);
		}else if(val < (int)get(mid)){
			mid = (mid + min -1) /2;
			return getIndex(val,min,mid);
		}else{
			return mid;
		}
		
		
				
				
	}
}
