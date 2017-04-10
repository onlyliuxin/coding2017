package com.coderising.linkedlist;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		if(head == null){
			head = new Node(o);
		}else{
			Node pos = head;
			while(pos.next != null){
				pos = pos.next;
			}
			pos.next = new Node(o);
		}
		size++;
	}
	
	public void add(int index , Object o){
		checkIndex(index);
		if(index == 0) {
			Node node = new Node(o);
			node.next = head;
			head = node;
		}
		else{
			Node pos = head;
			for(int i = 0;i < index-1;i++){
				pos = pos.next;
			}
			Node node = new Node(o);
			node.next = pos.next;
			pos.next = node;
		}
		size++;
	}

	private void checkIndex(int index) {
		if(index < 0 || index >size ) throw new IndexOutOfBoundsException("Index:"+index+",Size"+size);
	}
	
	public Object get(int index){
		checkIndexPosition(index);
		Node pos = head;
		for(int i = 0;i < index;i++){
			pos = pos.next;
		}
		return pos.data;	
	}
	
	public Object remove(int index){
		checkIndexPosition(index);
		Node element = head;
		if(index == 0){
			head = head.next;
		}else{
			Node pos = head;
		for(int i = 0;i < index - 1;i++){
				pos = pos.next;
			}
		element = pos.next;
		pos.next = pos.next.next;
		}
		size--;
		return element.data;
	}
	
	private void checkIndexPosition(int index) {
		if(index < 0 || index >=size ) throw new IndexOutOfBoundsException("Index:"+index+",Size"+size);
	}
	
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0,o);
	}
	
	public void addLast(Object o){
		add(size,o);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size-1);
	}
	
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	class LinkedListIterator implements Iterator{

		private Node node = head;
		private int pos = 0;
		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public Object next() {
			pos++;
			if(pos != 1){
				node = node.next;
			}
			return node.data;	
		}	
	}
		
	private static class Node{
		Object data;
		Node next;
		public Node(Object data){
			this.data = data;
			next = null;
		}
	}
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		if(size == 0) return;  
		
		for(int i=1;i<size;i++){
			addFirst(get(i));
			remove(i+1);
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public void removeFirstHalf(){
		if(size == 0) return;  
		
		int removeNum = size/2;
		for(int i=0;i<removeNum;i++){
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		if(length+i > size || i<0 || i>=size) return;
		
		for(int k=i;k<(length+i);k++){
			remove(i);
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
	public int[] getElements(LinkedList list){
		if(list == null) return new int[0];
		
		int[] targetList = new int[list.size];
		for(int i=0;i<list.size;i++){
			targetList[i] = (int) get((int) list.get(i));
		}
		return targetList;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public void subtract(LinkedList list){
		if(list == null) return;
		
		int decSum = 0;
		for(int i=0;i<list.size;i++){
			remove((int) (list.get(i)) - decSum);
			decSum++;
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		for(int i=0;i<size-1;i++){
			if(get(i) == get(i+1)){
				remove(i--);
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
		
		/**
		 * 普通遍历链表并一一比较
		for(int i=0;i<size;i++){
			if((int)get(i) > min && (int)get(i) < max){
				remove(i--);
			}
		}
		*/
		
		//遍历到最小值和最大值处并记录位置，最后调用remove(int i,int length)进行范围内的删除。
		int minPos = 0;
		int maxPos = 0;
		boolean exec = true;
		for(int i=0;i<size;i++){
			if( exec && (int)get(i) > min) {
				minPos = i;
				exec = false;
			} else if((int)get(i) >max){
				maxPos = i;
				break;
			}
		}
		remove(minPos, maxPos - minPos);
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList intersection( LinkedList list){
		LinkedList newList = new LinkedList();
		for(int i=0;i<size;i++){
			for(int j=0;j<list.size;j++){
				if(get(i).equals(list.get(j))){  //使用==,junit不通过,equals通过,注意是Object类型
					newList.add(list.get(j));
					break;
				}
			}
		}
		return newList;
	}
}
