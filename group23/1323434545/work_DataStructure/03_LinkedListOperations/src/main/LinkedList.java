package main;

import java.util.Arrays;

import utils.ListUtils;

public class LinkedList implements List{
	private Node head;
	private int size;

	private static class Node {
		private Object data;
		private Node next;
	}

	@Override
	public void add(Object o) {
		add(size, o);
	}

	@Override
	public void add(int index, Object o) {
		ListUtils.CheckIndexInRange(0, size, index);
		if (0==size) {
			head = new Node();
			head.data = o;
			size++;
			return;
		}
		if (0 == index) {
			Node node = new Node();
			node.data = o;
			node.next = head;
			head = node;
			size++;
			return;
		}
		if (index == size) {
			Node node = head;
			while (null != node.next) {
				node = node.next;
			}
			Node addNode = new Node();
			addNode.data = o;
			node.next = addNode;
			size++;
			return;
		}
		Node node = head;
		for (int i = 0; i < index - 1; i++) {
			node = node.next;
		}
		Node addNode = new Node();
		addNode.data = o;
		addNode.next = node.next;
		node.next = addNode;
		size++;
	}

	@Override
	public Object remove(int index) {
		if(size == 0){
			return null;
		}
		ListUtils.CheckIndexInRange(0, size - 1, index);
		Node node = head;
		if (0 == index) {
			head = head.next;
			size--;
			return node.data;
		}
		for (int i = 0; i < index - 1; i++) {
			node = node.next;
		}
		Node removeNode = node.next;
		node.next = removeNode.next;
		size--;
		return removeNode.data;
	}

	@Override
	public Object get(int index) {
		ListUtils.CheckIndexInRange(0, size - 1, index);
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.data;
	}

	@Override
	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new Iterator() {
			private int cursor;

			@Override
			public boolean hasNext() {
				if (size > 0) {
					return cursor < size;
				} else {
					return false;
				}

			}

			@Override
			public Object next() {
				Object tag = get(cursor);
				cursor++;
				return tag;
			}
		};
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){	
		if(this.size == 0){
			return;
		}
		LinkedList list = new LinkedList();
		for(int i =this.size-1;i>=0;i--){
			list.add(this.get(i));
		}
		this.head = list.head;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		int end = size/2;
		for(int i=0;i<end;i++){
			remove(0);
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i+length>size){
			return;
		}
		for(int j =0;j<length;j++){
			remove(i);
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
		if(null==list||list.size==0){
			return new int[0];
		}
		int[] result = new int[list.size];
		int counter = 0;
		for(int i=0;i<list.size;i++){
			if((int)list.get(i)<this.size){
				result[counter++] = (int) this.get((int)list.get(i));
			}
			
		}
		return Arrays.copyOf(result, counter);
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 
	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		if(null==list||list.size==0){
			return;
		}
		for(int i=0;i<list.size;i++){
			for(int j=0;j<this.size;j++){
				if(this.get(j).equals(list.get(i))){
					this.remove(j--);
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if(size ==0){
			return;
		}
		for(int i=0;i<this.size-1;i++){
			for(int j=i+1;j<this.size;j++){
				if(this.get(j).equals(this.get(i))){
					this.remove(j--);
				}
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
		if(min>max){
			return;
		}
		for(int i=0;i<size;i++){
			if((int)get(i)>min&&(int)get(i)<max){
				remove(i--);
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
		if(null==list||list.size==0){
			return result;
		}
		for(int i=0;i<this.size;i++){
			for(int j=0;j<list.size;j++){
				if(this.get(i).equals(list.get(j))){
					result.add(this.get(i));
				}
			}
		}
		return result;
	}

}
