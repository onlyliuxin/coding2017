package main.week01.data_structure;

import java.util.NoSuchElementException;

import main.week01.data_structure.api.Iterator;
import main.week01.data_structure.api.List;

public class LinkedList implements List {

	private Node head;
	private int size = 0;

	public void add(Object o) {
		if (isEmpty()) {
			addFirst(o);
		} else {
			addLast(o);
		}
	}

	public boolean isEmpty() {
		return (size == 0) ? true : false;
	}

	public void add(int index, Object o) {
		rangeCheck(index);
		if (index == 0) {
			addFirst(o);
		} else if (index == size) {
			addLast(o);
		} else {
			Node pre = getNode(index - 1);
			Node node = new Node(o);
			node.next = pre.next;
			pre.next = node;
			size++;
		}
	}

	private void rangeCheck(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	public Object get(int index) {
		rangeCheck(index);
		Node dest = head;
		for (int i = 0; i < index; i++) {
			dest = dest.next;
		}
		return dest.data;
	}

	private Node getNode(int index) {
		rangeCheck(index);
		Node dest = head;
		for (int i = 0; i < index; i++) {
			dest = dest.next;
		}
		return dest;
	}

	public Object remove(int index) {
		rangeCheck(index);
		if (index == 0) {
			return removeFirst();
		} else if (index == size) {
			return removeLast();
		}
		Node pre = getNode(index - 1);
		Node dest = pre.next;
		pre.next = dest.next;
		size--;
		return dest.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node node = new Node(o);
		node.next = head;
		head = node;
		size++;
	}

	public void addLast(Object o) {
		Node last = getNode(size - 1);
		Node node = new Node(o);
		last.next = node;
		size++;
	}

	public Object removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node newhead = head;
		Node dest = head;
		head = head.next;
		size--;
		return dest.data;
	}

	public Object removeLast() {
		Node newLastNode = getNode(size - 2);
		Node oldLastNode = newLastNode.next;
		newLastNode.next = null;
		size--;
		return oldLastNode;
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data) {
			this.data = data;
			next = null;
		}
	}

	public class LinkedListIterator implements Iterator {

		private LinkedList list;

		private int position = 0;

		private LinkedListIterator() {
		}

		private LinkedListIterator(LinkedList list) {
			this.list = list;
		}

		public void reset() {
			position = 0;
		}

		@Override
		public boolean hasNext() {
			return position + 1 <= list.size;
		}

		@Override
		public Object next() {
			return list.get(position++);
		}

	}

	public LinkedListIterator iterator() {
		return new LinkedListIterator(this);
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if (size <= 1) {
			return;
		}
		Node a = head, b = head.next;
		head.next = null;
		Node temp;
		while (null != b) {
			temp = b.next;
			b.next = a;
			a = b;
			b = temp;
		}
		head = a;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {
		if (size <= 1) {
			return;
		}
		size = size % 2 == 0 ? size / 2 : size / 2 + 1;
		head = getNode(size - 1);
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		rangeCheck(i);
		rangeCheck(i + length - 1);//或者当length超出长度，直接认为删除i后面的所有部分。
		if (i == 0) {
			head = getNode(length);
			size -= length;
		} else {
			Node pre = getNode(i - 1);
			pre.next = getNode(i + length - 1).next;
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 * @throws Exception
	 */
	public int[] getElements(LinkedList list) throws Exception {
		if (list == null) {
			throw new Exception("传入链表为空？");
		}
		
		int[] res = new int[list.size];
		for (int i = 0; i < list.size; i++) {
			//这个list里的值不一定合法的。可以跳过那些不合法的值。
			if(i > size - 1){
				continue;
			}
			res[i] = Integer.parseInt(get(
					Integer.parseInt(list.get(i).toString()) - 1).toString());
		}
		return res;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 * @throws Exception
	 */

	public void subtract(LinkedList list) throws Exception {
		if (list == null) {
			throw new Exception("传入链表为空？");
		}
		LinkedListIterator beSub = this.iterator(), sub = list.iterator();
		while (sub.hasNext()) {
			Object a = sub.next();
			while (beSub.hasNext()) {
				Object b = beSub.next();
				if (a.equals(b)) {
					this.remove(beSub.position - 1);
				}
			}
			beSub.reset();
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		LinkedListIterator iter = this.iterator();
		if (size <= 1) {
			return;
		}
		Object a = iter.next();
		while (iter.hasNext()) {
			Object b = iter.next();
			if (b.equals(a)) {
				remove(iter.position - 1);
				continue;
			} else {
				a = b;
			}
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 * @throws Exception
	 */
	public void removeRange(int min, int max) throws Exception {
		if(min > max){
			throw new Exception("输入有问题！");
		}
		if(max < Integer.parseInt(get(0).toString())){
			throw new Exception("全部太小！");
		}
		if(min > Integer.parseInt(get(size-1).toString())){
			throw new Exception("全部太大！");
		}
		int firstRemove = -1,lastRemove = -1;
		LinkedListIterator iter = this.iterator();
		boolean hasmin = false;
		while(iter.hasNext()){
			int n = Integer.parseInt(iter.next().toString());
			if(n>min && !hasmin){
			 firstRemove = iter.position - 1;
			 hasmin = true;
			} 
			if(n<max){
			 lastRemove = iter.position - 1;
			}
		}
		//移动指针的时候，注意不要留下指空的指针。不然相关node会无法被gc
		if(hasmin && firstRemove == 0){
			head = getNode(lastRemove);
			size -= lastRemove-firstRemove+1;
			head = head.next;
		}else{
			Node pre = getNode(firstRemove-1);
			pre.next = getNode(lastRemove);
			size -= lastRemove-firstRemove+1;
			pre.next = pre.next.next;
		}
		
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		if(0 == list.size || 0 == size){
			return new LinkedList();
		}
		
		LinkedList res = new LinkedList();
		Node node1 = this.head;
		Node node2 = list.head;
		while(node1 != null && node2 != null){
			if((int)node1.data<(int)node2.data){
				node1 = node1.next;
			}else if((int)node1.data>(int)node2.data){
				node2 = node2.next;
			}else{
				res.add(node1.data);
				node1 = node1.next;
				node2 = node2.next;
			}
		}
		return res;
	}

	public String toString() {
		LinkedListIterator iter = this.iterator();
		StringBuilder sb = new StringBuilder();
		while (iter.hasNext()) {
			sb.append(iter.next());
			sb.append("->");
		}
		sb.append("null");
		return sb.toString();
	}
}
