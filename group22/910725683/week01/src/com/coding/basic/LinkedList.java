package com.coding.basic;


public class LinkedList implements List {
	
	//链表头//
	private Node head;
	
	//链表尾，为了方便加上的//
	private Node tail;
	
	//链表长度，为了方便加上的//
	private int size=0;
	
	private static class Node{
		Object data;
		Node next=null;
	}
	
	public void add(Object o){
		/*如果是空的则从头开始增加，因为要标记链表头，链表尾额外的标记下
		 *如果不是空的则从尾巴开始增加，因为要标记链表尾
		 */
		if (head==null){
			addFirst(o);
			tail=head;
		}else{
			addLast(o);
		}
	}
	
	public void add(int index , Object o){
		//头尾单独增加，方便维护标记//
		if (index==0){
			addFirst(o);
			return;
		}
		if (index==size){
			addLast(o);
			return;
		}
		checkIndex(index);
		Node current = getNode(index-1);
		Node node = new Node();
		node.data=o;
		node.next=current.next;
		current.next=node;
		size++;
	}
	
	//按照下标获取节点，方便跟下标有关的增查删改//
	private Node getNode(int index){
		checkIndex(index);
		Node current = head;
		for (int i=0;i<index;i++){
			current=current.next;
		}
		return current;
	}
	
	public Object get(int index){
		return getNode(index).data;
	}
	
	public Object remove(int index){
		//头尾单独处理，方便维护标记//
		if (index==0){
			return removeFirst();
		}
		if (index==size-1){
			return removeLast();			
		}
		checkIndex(index);
		Node current = getNode(index-1);
		Node temp = current.next;
		current.next=temp.next;
		size--;
		return temp.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node();
		node.data=o;
		node.next=head;
		head=node;//标记链表头//
		size++;			
	}
	public void addLast(Object o){
		Node node = new Node();
		node.data=o;
		tail.next=node;
		tail=node;//标记链表尾//
		size++;		
	}
	public Object removeFirst(){
		Node current = head;
		head=current.next;//标记链表头//
		current.next=null;//切断关联//
		size--;
		return current.data;
	}
	
	public Object removeLast(){
		Node current=getNode(size-2);
		Node temp = current.next;
		current.next=null;//切断关联//
		tail=current;//标记链表尾//
		size--;
		return temp.data;
	}
	
	public Iterator iterator(){
		return new Iterator();
	}
	
	private class Iterator{
		Node current=head;
		public boolean hasNext(){
			return current!=null;
		}
		public Object next(){
			if (current==null){
				throw new IndexOutOfBoundsException("no node");
			}
			Node temp =current;
			current=current.next;
			return temp.data;
		}
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		/*两个指针，开始的时候指向头跟头后面的一个(前指针、后指针)
		 *循环：每次向后移动步长1，至后指针移到链表尾，size-1个节点需要移动(size-1)-1次
		 *先保留前指针的值temp，即当前逆序链表的头，然后再移动前、后指针
		 *移动后，将前指针的节点连接到逆序链表的头，开始下一次移动
		 *循环结束后，注意到实际重连的只有旧链表的第二个节点到倒数第个节点，需要单独处理旧链表的头尾节点
		 *旧链表的尾节点需要链接到逆序链表的头，旧链表的头节点的指针置空，不然会1<->2
		 *维护头尾标记
		 */
		Node current=head;
		Node currentAfter=current.next;
		Node temp;
		for (int i=0;i<size-2;i++){
			temp=current;
			current=currentAfter;
			currentAfter=currentAfter.next;
			current.next=temp;
		}
		currentAfter.next=current;
		tail=head;
		tail.next=null;
		head=currentAfter;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		//int截断，不会有小数//
		int removeLength = size / 2;
		for (int i=1;i<=removeLength;i++){
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		checkIndex(i);
		length=length+i-1;
		if (i+length-1>size){
			length=size-i;
		}
		//从后往前删除，防止越界//
		for (int k=length;k>=i;k--){
			remove(k);
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
		int indexLength=list.size();
		int[] result=new int[indexLength];
		for (int i=0;i<indexLength;i++){
			int index=(int)list.get(i);
			if (index>size){
				result[i]=0;
			}else{
				result[i]=(int)list.get(index);
			}
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	//注意到递增，在外层循环list的时候，保留内层循环的被比较链表的节点的下标并递增即可//
	public void subtract(LinkedList list){
		int startIndex=0;
		Iterator iter=list.iterator();
		while(iter.hasNext()){
			int src =(int) iter.next();
			while(startIndex<size){
				if (src==(int) get(startIndex)){
					remove(startIndex);
					break;
				}
				else{
					startIndex++;
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	//注意到递增，保留不需要删除的节点的下标并递增即可//
	public  void removeDuplicateValues(){
		int startIndex=1;
		int scr=(int)head.data;
		while(startIndex<size){
			if (scr==(int) get(startIndex)){
				remove(startIndex);
			}else{
				scr=(int) get(startIndex);
				startIndex++;
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	//这个，想用二分法但是是单链表，没想到高效的算法（网上说是B树，然而不会。。。），一个一个比较的//
	public  void removeRange(int min, int max){
		Node current=head;
		Node temp=head;
		boolean isHeadNoed=true;
		while(current!=null){
			if ((int)current.data<min || (int)current.data>max){
				if (isHeadNoed){
					current=current.next;
					removeFirst();
				}else{
					temp.next=current.next;
					current=current.next;
					size--;
				}
			}else{
				temp=current;
				current=current.next;
				isHeadNoed=false;
			}
			
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	//注意到递增，保留内循环下标位置递增即可//
	public LinkedList intersection( LinkedList list){
		LinkedList result = new LinkedList();
		int startIndex = 0;
		for (Iterator iter = list.iterator();iter.hasNext();){
			int src = (int) iter.next();
			while (startIndex<size){
				Object tar = get(startIndex);
				if (src == (int) tar){
					result.add(tar);
					break;
				}else{
					startIndex++;
				}
			}
		}
		return result;
	}
	
	private void checkIndex(int index){
		if (index<0 || index >=size){
			throw new IndexOutOfBoundsException("get " + index+" in "+size);
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Node current = head;
		for (int i=0;i<size;i++){
			sb.append(current.data);
			if (current.next!=null){
				sb.append("->");
			}
			current=current.next;
		}
		return sb.toString();
	}
}