package org.coding.three.list.impl;

import java.util.Arrays;
import java.util.Iterator;

import org.coding.three.list.List;
/**
 * 单链表/单向链表
 */
public class LinkedList implements List {
	/**
	 * 0. head 节点存储数据
	 * 1. 这里的 head 第一次添加之后 "引用" 将不再改变；"值" 可以被修改已表示往首节点插入新的值。 
	 * 2. 可以将 head 修改为对 node 引用, 不存储任何数据。
	 */
	private Node head;
	
	public void add(Object o){
		Node node = new Node(o);
		if(head == null){ //第一次
			head = node;
		} else {
			getNode(size() - 1).next = node;
		}
	}
	
	private Node getNode(int index) {
		checkIndex(index);
		Node node = head;
		for(int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	private void checkIndex(int index) {
		int size = size();
		if(index < 0 || index > (size - 1)) {
			throw new IndexOutOfBoundsException("size = "  + size + ", index = " + index);
		}
	}
	public void add(int index , Object o){
		checkIndex(index);
		if(index == 0) {	//更新 head 的值, 将旧值创建新的Node插入到 head 后
			Object data = head.data;
			head.data = o;
			Node node = new Node(data);
			node.next = head.next;
			head.next = node;
		} else {
			Node pre = getNode(index - 1);
			Node node = new Node(o);
			node.next = pre.next;
			pre.next = node;
		}
	}
	public Object get(int index){
		checkIndex(index);
		return getNode(index).data;
	}
	public Object remove(int index){
		checkIndex(index);
		Object data = null;
		if(index == 0) {
			Node next = head.next;
			data = head.data;
			if(next == null) {
				head = null;
			} else {
				head.data = next.data;
				head.next = next.next;
				next.next = null;
			}
		} else {
			Node pre = getNode(index - 1);
			Node node = pre.next;
			pre.next = node.next;
			node.next = null;
			data = node.data;
		}
		return data;
	}
	
	public int size(){
		Node temp = head;
		int size = 0;
		while(temp != null) {
			size++;
			temp = temp.next;
		}
		return size;
	}
	
	public void addFirst(Object o){
		add(0, o);
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size() - 1); 
	}
	public Iterator<?> iterator(){
		return new LinkedIterator();
	}

	class LinkedIterator implements Iterator<Object> {
        int cursor = 0;
        int lastRet = -1;
		@Override
		public boolean hasNext() {
			return cursor != LinkedList.this.size();
		}

		@Override
		public Object next() {
			int i = cursor;
			Object data = LinkedList.this.get(i);
			lastRet = i;
			cursor = i + 1;
			return data;
		}
		
		@Override
		public void remove() {
			if(lastRet < - 1) {
				throw new RuntimeException("非法操作");
			}
			LinkedList.this.remove(lastRet);
			cursor--;
			lastRet = -1;
		}
	}
	
	private static  class Node{
		Object data;
		Node next;
		public Node(Object data) {
			super();
			this.data = data;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		int size = size();
		if(size < 2) {
			return ;
		}
		int preIndex = 0;
		int behindIndex = size - 1;
		Node preNode = head;
		while(preIndex < behindIndex) {
			Node behindNode = getNode(behindIndex);
			Object temp = preNode.data;
			preNode.data = behindNode.data;
			behindNode.data = temp;
			preIndex++;
			behindIndex--;
			preNode = preNode.next;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if(size() < 2) {
			return;
		}
		int count = size() / 2;
		Node preNode = getNode(count - 1);
		Node nextNode = preNode.next;
		preNode.next = null;
		head.data = nextNode.data;
		head.next = nextNode.next;
		nextNode.next = null;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int index, int length){
		checkIndex(index);
		int size = size();
		if(index + length > size) {
			length = size;
		}
		if(index == 0 && length == size) {
			head = null;
			return;
		}
		int tempIndex = index + length - 1;
		Node endNode = getNode(tempIndex);
		Node nextNode = endNode.next;
		endNode.next = null;
		if(index == 0) { //head
			Node nnextNode = nextNode.next;
			nextNode.next = null;
			head.data = nextNode.data;
			head.next = nnextNode;
		} else {
			Node preStartNode = getNode(index - 1);
			preStartNode.next = nextNode;
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
		Iterator<?> it = list.iterator();
		int[] array = new int[list.size()];
		int size = size();
		int length = 0;
		while(it.hasNext()) {
			int index = (int) it.next();
			if(index >= size)  {
				break;
			}
			array[length++] = (int) get(index);
		}
		if(length == array.length) {
			return array;
		} else {
			return Arrays.copyOf(array, length); 
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		Iterator<?> it = list.iterator();
		while(it.hasNext()) {
			Object next = it.next();
			Iterator<?> iterator = this.iterator();
			while(iterator.hasNext()) {
				if(next.equals(iterator.next())) {
					iterator.remove();
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if(size() < 2) {
			return;
		}
		Iterator<?> it = iterator();
		Object pre = null;
		while(it.hasNext()){
			Object data = it.next();
			if(pre != null && pre.equals(data)) {
				it.remove();
			} else {
				pre = data;
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
		int size = size();
		if(size < 1) {
			return;
		}
		int minVal = (int)get(0);
		int maxVal = (int)get(size - 1);
		if(minVal > min && maxVal < max) {	//直接清空
			this.head = null;
			return;
		}
		if(max <= minVal) {
			return;
		}
		if(min >= maxVal) {
			return;
		}
		int startIndex = getMinIndex(min, size);
		int endIndex = getMaxIndex(max, size);
		if(endIndex - startIndex < 0) {
			return;
		}
		remove(startIndex, (endIndex - startIndex) + 1);
		
	}

	private int getMaxIndex(int max, int size) {
		int start = 0;
		int end = size - 1;
		while(start < end) {
			int index = (end + start) / 2;
			int midVal = (int) get(index);
			if(midVal == max) {
				return index - 1;
//				index = index - 1;
//				Node node = getNode(index);
//				if((int)node.data < maxVal) {
//					return index ;
//				}//不考虑重复 TODO
			}
			if(midVal > max) {
				end = index - 1;
			} else {
				start = index + 1;
			}
		}
		if((int)get(end) >= max) {
			return 0;
		}
		return end;
	}

	private int getMinIndex(int min, int size) {
		int start = 0;
		int end = size - 1;
		while(start < end) {
			int index = (end + start) / 2;
			int midVal = (int) get(index);
			if(midVal == min) {
				return index + 1;
//				Node node = getNode(index);	//暂无考虑重复 TODO
//				if(node.next != null && (int)node.next.data > midVal) {
//					return index + 1;
//				} else {
//					while(node.next != null && (int)node.next.data == midVal) {	// 重复值
//						node = node.next;
//						index++;
//					}
//					return index;
//					
//				}
			}
			if(midVal > min) {
				end = index - 1;
			} else {
				start = index + 1;
			}
		}
		if((int)get(start) <= min) {
			return size;
		}
		return start;
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList linkedList = new LinkedList();
		if(list == null || size() == 0 || list.size() == 0) {
			return linkedList;
		}
		Iterator<?> it = iterator();
		int index = 0;
		boolean iseqFlag = false;
		boolean isgtFlag = false;
		while(it.hasNext()) {
			int v1 = (int) it.next();
			if(index != 0) {
				if(iseqFlag) {
					list.remove(0, index + 1);
					iseqFlag = false;
				}
				if(isgtFlag) {
					list.remove(0, index);
					isgtFlag = false;
				}
			}
			Iterator<?> it2 = list.iterator();
			while(it2.hasNext()) {
				int v2 = (int) it2.next();
				if(v2 == v1) {
					linkedList.add(v1);
					iseqFlag = true;
					break;
				} else if(v2 > v1) {
					isgtFlag = true;
					break;
				}
				index++;
			}
			if(index == list.size()) {	//第二个链表中的值是否全部小于现在第一个链表中正在进行比较的值
				break;
			}
		}
		return linkedList;
	}
}
