package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	
	public void add(Object o){
		this.addLast(o);
		
	}
	public void add(int index , Object o){
		if(index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
		if(index==0){
			this.addFirst(o);
			size++;
			return;
		}else if(index==size){
			this.addLast(o);
			size++;
			return;
		}
		Node preNode = this.getNode(index-1);
		Node curNode = this.getNode(index);
		Node newNode = new Node(o, curNode);
		preNode.next = newNode;

		
		size++;
		
		
	}
	
	private Node getNode(int index){
		if(index <0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		if(index ==0){
			return head;
		}
		Node curNode = head;
		for(int i=1;i<=index;i++){
			curNode = curNode.next; 	
		}
		return curNode;
	}
	
	public Object get(int index){
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		
		Node temp = head;
		for(int i =1;i<=index;i++){
			temp = temp.next;
		}
			return temp.data;
	}
	public Object remove(int index){
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		Object o = null;
		if(size == 1){
			o = head.data;
			size--;
			return o;
		}
		if(index==0){
			o = head.data;
			Node afterHead = head.next;
			head = afterHead;
	
		}else if(index==size-1){
			Node preTail = getNode(index-1);
			Node tail = preTail.next;
			o = tail.data;
			preTail.next = null;
		}else{
			Node preCur = getNode(index-1);
			Node cur = preCur.next;
			Node nextCur = cur.next;
			o = cur.data;
			preCur.next = nextCur;
		
		}
		size--;
		return o;
			
			
			
		
		
		
		
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o,null);
		
		if(head == null){
			head = node;
			size++;
			return;
		}
		head = new Node(o, head);
		size++;
		
	}
	public void addLast(Object o){
		//新节点的next指针指向tail
		Node add = new Node(o, null);
		if(head==null){			
			head = add;
			size++;
			return;
		}
		Node curNode = head;
		while(curNode.next != null){
			curNode = curNode.next;
		}
		
		curNode.next = add;
		size++;
	}
	
	
	public Object removeFirst(){
		return this.remove(0);
	}
	public Object removeLast(){
		return this.remove(size-1);
	}
	
	private class Itr implements Iterator{
		int cursor = 0;
		public boolean hasNext() {
			return cursor<size();
		}
		

		public Object next() throws Exception {
			cursor++;
			return get(cursor-1);
			
			
		}
		
	}
	public Iterator iterator(){
		return new Itr();
	}
	
	
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		
		Iterator itr = this.iterator();
		while(itr.hasNext()){
			try {
				sb.append(itr.next()+",");
			} catch (Exception e) {
				return "[]";
			}
			
		}
		if(sb.indexOf(",") != -1){
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		sb.append("]");
		
		return sb.toString();
//		
	}





	private static  class Node{
		private Object data;
		private Node next;
		public Node(Object data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		
		
		
	}
	
	public static void main(String[] args) throws Exception {
		LinkedList ll  = new LinkedList();
		ll.add(1);
		ll.add(2);
		System.out.println(ll);
		
		ll.add(1, 3);
		System.out.println(ll);
		
		ll.remove(1);
		
		System.out.println(ll);
		ll.add(4);
		System.out.println(ll);
		
	
		ll.reverse();
		System.out.println(ll);
		/*System.out.println(ll.get(0));
		System.out.println(ll.get(1));
		System.out.println(ll.get(2));*/
		
		LinkedList ll2  = new LinkedList();
		ll2.add(1);
//		ll2.add(1);
		ll2.add(2);
//		ll2.add(3);
		ll2.add(3);
//		ll2.add(4);
		ll2.add(4);
		ll2.add(5);
//		ll2.removeFirstHalf();
//		ll2.remove(2,3);
//		ll2.removeDuplicateValues();
		
		System.out.println(ll2);
		
//		ll2.removeRange(2, 6);
//		ll2.remove(3);
		System.out.println(ll2);
		
		LinkedList ll3 = new LinkedList();
		ll3.add(2);
		ll3.add(4);
		ll2.subtract(ll3);
		System.out.println(ll2);
		
		
		
		
		
	}
	
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		LinkedList temp = new LinkedList();
		for(int i = size - 1;i >= 0; i--){
			temp.add(this.get(i));
		}
		System.out.println("---"+temp.toString()+"---");
		//清空原链表
		this.clear();
		
		System.out.println("---"+this.toString()+"---");
		for(int i = 0; i < temp.size();i++){
			Object o = temp.get(i);
			this.add(o);
		}

	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if(this.size() == 0){
			return;
		}
		int temp = this.size();
		for(int i = 1; i <= temp/2; i++){
			this.removeFirst();
		}
		
		
	}
	
	public void clear(){
		
		Iterator itr = this.iterator();
		while(itr.hasNext()){
			this.removeFirst();
		}
		this.head = null;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
		for(int j = 0;j < length; j++){
			this.remove(i);
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
		int[] result = new int[list.size()];
		for(int i = 0; i<list.size(); i++){
			result[i] = (Integer)this.get((Integer)list.get(i));
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		
		
		out:
		for (int i = 0; i < list.size(); i++) {
			Integer temp1 =  (Integer)list.get(i);
			for(int j = 0; j < this.size(); j++){
				Integer temp2 = (Integer)this.get(j);
				if(temp1.equals(temp2)){
					this.remove(j);
					continue out;
				}
				
				
			}
			
		}
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		LinkedList temp = new LinkedList();
		for(int i = 0; i < this.size(); i++){
			if(i == 0){
				temp.add(this.get(i));
			}else{
				if(this.get(i) != temp.get(temp.size()-1)){
					temp.add(this.get(i));
				}
			}
		}
		this.clear();
		
		for(int i = 0; i<temp.size();i++){
			this.add(temp.get(i));
		}
		
		
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		int indexMin = -1;
		int indexMax = -1;
		int countForMin = 0;
		int countForMax = 0;
		
		for(int i = 0; i < this.size(); i++){
			
			Integer eleFront = (Integer)this.get(i);
			Integer eleBack = (Integer)this.get(this.size()-1-i);
			if(eleFront > min && countForMin == 0){
				indexMin = i;
				countForMin++;
			}
			if(eleBack < max && countForMax == 0){
				indexMax = this.size()-1-i;
				countForMax++;
			}
		}
		
		if(indexMin != -1 && indexMax != -1){
			for(int i = indexMin; i <= indexMax; i++){
				this.remove(indexMin);
			}
			
		}
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList result = new LinkedList();
		for(int i = 0; i < this.size(); i++){
			Integer temp1 = (Integer)this.get(i);
			for(int j = 0; j < list.size(); j++){
				Integer temp2 = (Integer)list.get(j);
				if(temp1 == temp2){
					result.add(temp2);
				}
				
			}
		}
		return result;
	}
}
