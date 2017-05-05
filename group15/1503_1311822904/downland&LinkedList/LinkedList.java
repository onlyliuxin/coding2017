package com.coding.basic;

public class LinkedList implements List {

	private int size = 0;
	private Node head=new Node(null);

	public LinkedList(int[] ints) {
		int length=ints.length;
		if(length>0){
			for(int i=0;i<length;i++){
					this.add(ints[i]);
			}
		}

	}

	public LinkedList() {

	}


	public void add(Object o){
		if(head.data==null){
			head.data=o;
			size++;
			return;
		}
		Node n=head;
		while (n.next!=null){
			n=n.next;
		}
		n.next=new Node(o);
		size++;
	}
	public void add(int index , Object o){
		Node n=getNode(index-1);
		Node newN=new Node(o);
		if(n.next!=null){
			newN.next=n.next;
		}

		n.next=newN;
		size++;
	}
	public Object get(int index){
		return getNode(index).data;
	}
	private Node getNode(int index){
		Node n=head;
		for (int i=0;i<index;i++){
			if(n==null)
				throw new IndexOutOfBoundsException();
			n=	n.next;
		}
		return n;
	}
	public Object remove(int index){
		Object o;
		if (index==0){
			o=head.data;
			head.data=null;
			size--;
			return o;
		}
		Node n=getNode(index-1);
		o=n.next.data;
		n.next=n.next.next;
		size--;
		return o;
	}
	
	public int size(){

		return size;
	}
	
	public void addFirst(Object o){
		size++;
		if(head.data==null){
			head.data=o;
			return;
		}
		Node n=new Node(o);
		n.next=head;
		head=n;

	}
	public void addLast(Object o){
		size++;
		//要是还没有值 就是头
		if (head.data==null){
			head.data=o;
		}
		Node n=head;
		//找到最后一个
		while (n.next!=null){
			n=n.next;
		}
		//接上去
		n.next=new Node(o);

	}
	public Object removeFirst(){
		//没有头
		if(head.data==null)
			throw new IndexOutOfBoundsException();
		Object o=head.data;
		head=head.next;
		size--;
		return o;
	}
	public Object removeLast(){
		//还没有值 异常
		if (head.data==null){
			throw new IndexOutOfBoundsException();
		}
		size--;
		Object o;
		//只有头有值
		if(head.next==null){
			o=head.data;
			head.data=o;
			return o;
		}
		Node n=head;
		//找到最后第二个
		while (n.next.next!=null)
			n=n.next;
		//拿到最后一个值
		o=n.next.data;
		//去掉最后一个节点
		n.next=null;
		return o;
	}
	public Iterator iterator(){
		return new  LinkedIterator();
	}
	private class LinkedIterator implements Iterator{
		private Node n=head;
		@Override
		public boolean hasNext() {
			if(n!=null){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			Object o=n.data;
			n=n.next;
			return o;
		}
	}
	
	private static  class Node{
		Object data;
		Node next;
		Node(Object o){
			data=o;
		}

		
	}

	public String toString(){
		String s="[";
		for (int i=0;i<size;i++)
			s+=get(i)+", ";
		s+="]";
		return s;
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		if(this.head.next==null)
			return;
		int[] ints=new int[size];
		Node n=head;
		for(int i=size-1;i>-1;i--) {
			ints[i] = (int) n.data;
			n=n.next;
		}
		LinkedList temp=new LinkedList(ints);
		head=temp.head;

	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8 [4 2
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10  [5 2

	 */
	public  void removeFirstHalf(){
		int middle=size/2;
		head=getNode(middle);
		size=size-middle;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length) throws Exception {
		size=size-length;
		if(i+length>size) {
			throw new Exception("不够长");
		}
		Node start;
		if(i==0){
			start=head;
		}else {
			start=getNode(i-1);
		}

		Node n=start;
		while (length>-1){
			n=n.next;
			length--;
		}
		start.next=n;

	}
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * @param list
	 */
	public  int[] getElements(LinkedList list) throws CloneNotSupportedException {
		int length=list.size();
		if (length<1)
			return null;
		int[] result=new int[length];
		LinkedList newList= this;

		Node  indexNode= list.head;//第一个坐标节点
		int index=(Integer) indexNode.data;
		Node newFirstNode=newList.getNode(index);//第一个目标节点
		result[0]= (int) newFirstNode.data;//数放到结果数组
		if(length==1)
			return result;

		int i=1;
		while (i<length){
			newList.head=newFirstNode.next;//前面的去掉
			indexNode=indexNode.next;//下一个坐标节点
			newFirstNode=newList.getNode((Integer) indexNode.data-index-1);
			index=(Integer) indexNode.data;
			result[i++]= (int) newFirstNode.data;//数放到结果数组
		}


		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list) throws Exception {
		Node delNode=list.head;
		Node n=head;
		Node prevNode=null;

		while (delNode!=null&&n!=null){
			if((int)n.data==(int)delNode.data){//删掉
				if(prevNode==null){//是头
					head=head.next;
				}else {//不是头
					prevNode.next=n.next;
				}
				prevNode=n;//this下一个
				n=n.next;
				delNode=delNode.next;//比较组下一个
				size--;
			}else if((int)n.data<(int)delNode.data){
				prevNode=n;//this下一个
				n=n.next;
			}else {//比较组下一个
				delNode=delNode.next;
			}

		}

	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node n=head.next;
		int data=(int)n.data;
		Node prevNode=head;
		Integer prevData=(int)head.data;
		while (n!=null){
			data=(int)n.data;
			if(prevData==data){//相等 去掉
				prevNode.next=n.next;
					size--;
			}else {
				prevNode=n;
				prevData=data;
			}
			n=n.next;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Node startNode=null;
		Node endNode=null;
		Node n=head.next;
		int data;
		Node prevNode=head;

		while (n!=null) {
			data=(int)n.data;
			if (data >= min) {//第一个进去范围的
				startNode = prevNode;
				break;
			}
			prevNode=n;
			n = n.next;
		}
		int count=0;
		while (n!=null) {
			data=(int)n.data;
			if (data >= max) {//第一个出范围的
				endNode = n;
				break;
			}
			count++;//没出范围就继续计数
			n = n.next;//下一个
		}
		startNode.next=endNode;
		size=size-count;
		if ((int)head.data>=min){
			size--;
			head=head.next;
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList list3=new LinkedList();
		Node aNode=this.head;
		Node bNode=list.head;
		while (aNode!=null&&bNode!=null){
			if((int)aNode.data>(int)bNode.data){
				bNode=bNode.next;
			}else if((int)bNode.data>(int)aNode.data){
				aNode=aNode.next;
			}else {
				list3.add((int)bNode.data);
				bNode=bNode.next;
				aNode=aNode.next;
			}
		}


		return list3;
	}
}
