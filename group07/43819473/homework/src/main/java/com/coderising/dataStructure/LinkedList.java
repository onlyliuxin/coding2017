package com.coderising.dataStructure;

/**
 * Created by LvZhenxing on 2017/2/21.
 */
public class LinkedList implements List {

	private Node head=new Node();
	private int size = 0;

	public void add(Object o) {
		addToNode(head,o);
		size++;
	}

	public void add(int index, Object o) {
		if (index <0 || index > size) {
			throw new IndexOutOfBoundsException("index out of bound");
		}

		addToNode(getLastNode(index),o);
		size++;
	}

	private Node getLastNode(int index){

		Node nowNode = head;
		for (int pos = 1; pos <= index; pos++) {
			nowNode = nowNode.next;
		}
		return nowNode;
	}

	private void addToNode(Node node,Object o){
		if (node.next == null) {
			Node newNode = new Node();
			newNode.data = o;
			node.next = newNode;
		} else {
			Node newNode = new Node();
			newNode.data = o;
			newNode.next = node.next;
			node.next = newNode;
		}
	}

	public Object get(int index) {
		if (index <0 || index > size - 1) {
			throw new IndexOutOfBoundsException("index out of bound");
		}

		Node node= getLastNode(index);
		return node.next==null?null:node.next.data;
	}

//	public Object get(Object o) {
//
//		if(head.next==null){
//			return null;
//		}
//		Node node=head.next;
//		while (node!=null){
//			if(node.equals(o)){
//				break;
//			}
//			node=node.next;
//		}
//
//		return node;
//	}

	public Object remove(int index) {
		if (index <0 || index > size - 1) {
			throw new IndexOutOfBoundsException("index out of bound");
		}

		Node node= getLastNode(index);
		Node nowNode=node.next;
		if(nowNode.next!=null){
			node.next=node.next.next;
		}else{
			node.next=null;
		}
		size--;
		return nowNode.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		add(0,o);
	}

	public void addLast(Object o) {
		add(size,o);
	}

	public Object removeFirst() {
		return remove(0);
	}

	public Object removeLast() {
		return remove(size-1);
	}

	public Iterator iterator() {
		return new LinkedListInterator();
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		LinkedList list=new LinkedList();
		for (int i = 0; i <=size-1 ; i++) {
			Node node=getLastNode(i).next;
			list.addFirst(node.data);
//			node.data=null;
//			node.next=null;
		}
		head=list.head;
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		for(int i=0;i<=size/2-1;i++){
			removeFirst();
		}
		size-=size/2-1;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		Node node=getLastNode(i);
		Node nextNode=getLastNode(i+length);
		node.next=nextNode.next;
		size-=length;
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
		int[] result=new int[list.size()];
		Iterator iterator = list.iterator();
		for (int i = 0; i <= list.size()-1; i++) {
			int BValue=Integer.parseInt(list.get(i).toString());
			if(BValue>=this.size-1){
				break;
			}
			result[i]=Integer.parseInt(get(BValue).toString());
		}

		return result;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素
	 * @param list
	 */
	public  void subtract(LinkedList list){

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if(size<2){
			return;
		}
		for (int i = 0; i <= size-2; i++) {
			if(get(i).equals(get(i+1))){
				Node lastNode=getLastNode(i);
				lastNode.next=lastNode.next.next;
				i--;
				size--;
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

		Node minLastNode=null;
		Node maxLastNode=null;
		Node nowNode=null;
		int count=0;
		for (int i = 0; i <= size-1; i++) {
			if(i==0){
				nowNode=head;
			}else{
				nowNode=nowNode.next;
			}

			int nextIntValue=Integer.parseInt(nowNode.next.data.toString());
			if(nextIntValue>min && nextIntValue<max){
				count++;
				if(minLastNode==null){
					minLastNode=nowNode;
					maxLastNode=nowNode;
				}else{
					maxLastNode=nowNode;
				}
			}
		}
		if(count>0){
			minLastNode.next=maxLastNode.next.next;
			size-=count;
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		return null;
	}

	private class LinkedListInterator implements Iterator {

		private int nowIndex = 0;

		public boolean hasNext() {
			if (nowIndex <= size - 1) {
				return true;
			}
			return false;
		}

		public Object next() {
			return get(nowIndex++);
		}
	}


	private static class Node {
		Object data;
		Node next;
	}
}
