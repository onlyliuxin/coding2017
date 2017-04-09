package com.coding.basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedList implements List {

	private Node first;

	private Node last;

	private int size;

	/**
	 * 头部添加节点方法
	 */
	private void linkFirst(Object o) {
		final Node node = first;
		final Node firstNode = new Node(o, node, null);
		this.first = firstNode;
		if (node == null) {
			last = firstNode;
		} else {
			node.prev = firstNode;
		}
		size++;
	}

	/**
	 * 末端添加节点方法
	 * 
	 * @param o
	 */
	private void linkLast(Object o) {
		final Node node = last;
		final Node lastNode = new Node(o, null, node);
		last = lastNode;
		if (node == null) {
			first = lastNode;
		} else {
			node.next = lastNode;
		}
		size++;
	}

	/**
	 * 在链表末端添加节点
	 */
	public void add(Object o) {
		if (o != null)
			linkLast(o);
	}

	/**
	 * 在指定下标处添加节点
	 */
	public void add(int index, Object o) {
		checkIndex(index);
		Node befo = getNodeByIndex(index);
		linkBefore(o, befo);
	}

	/**
	 * 根据下标获取节点
	 */
	public Object get(int index) {
		checkIndex(index);
		return getNodeByIndex(index).data;
	}

	/**
	 * 根据下标移除节点
	 */
	public Object remove(int index) {
		checkIndex(index);
		Node current = getNodeByIndex(index);
		final Node node = current;
		final Node prev = node.prev;
		final Node next = node.next;
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			next.prev = prev;
			current = null;
		}
		if (next == null) {
			last = prev;
		} else {
			prev.next = next;
			next.prev = prev;
			current = null;
		}
		size--;
		return node.data;
	}

	/**
	 * 返回链表长度
	 */
	public int size() {
		return size;
	}

	/**
	 * 在链表头添加节点
	 * 
	 * @param o
	 */
	public void addFirst(Object o) {
		linkFirst(o);
	}

	/**
	 * 在链表最后添加节点
	 * 
	 * @param o
	 */
	public void addLast(Object o) {
		linkLast(o);
	}

	/**
	 * 移除链表首个元素
	 * 
	 * @return
	 */
	public Object removeFirst() {
		Node node = first;
		if (node == null)
			throw new NoSuchElementException();
		else {
			Node next = node.next;
			if (next == null)
				first = null;
			else {
				first = next;
				first.prev = null;
			}
		}
		size--;
		return node.data;
	}

	/**
	 * 移除链表最后一个元素
	 * 
	 * @return
	 */
	public Object removeLast() {
		Node node = last;
		if (last == null)
			throw new NoSuchElementException();
		else {
			Node prev = node.prev;
			if (prev == null)
				last = null;
			else {
				last = prev;
				last.next = null;
			}
		}
		size--;
		return node.data;
	}

	/**
	 * 迭代方法
	 * 
	 * @return
	 */
	public Iterator iterator() {
		return new Iter();
	}

	/**
	 * 在节点之前插入一个新的节点
	 * 
	 * @param data
	 * @param befo
	 */
	private void linkBefore(Object data, Node befo) {
		final Node prev = befo.prev;
		final Node node = new Node(data, befo, prev);
		befo.prev = node;
		if (prev == null)
			first = node;
		else
			prev.next = node;
		size++;
	}

	/**
	 * 根据下标获取节点
	 * 
	 * @param index
	 * @return
	 */
	private Node getNodeByIndex(int index) {
		checkIndex(index);

		Node node = first;
		for (int x = 0; x < index; x++) {
			node = node.next;
		}
		return node;
	}

	/**
	 * 检查下标是否越界
	 * 
	 * @param index
	 */
	private void checkIndex(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("链表下标越界");
		}
	}

	/**
	 * 迭代器内部类
	 * 
	 * @author Adminstater
	 * 
	 */
	private class Iter implements Iterator {
		private int nextIndex = 0;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return nextIndex != size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			int i = nextIndex++;
			if (i > size - 1)
				throw new IndexOutOfBoundsException();
			return getNodeByIndex(i).data;
		}

	}

	/**
	 * 节点内部类
	 * 
	 * @author Adminstater
	 * 
	 */
	private static class Node {
		Object data;
		Node next;
		Node prev;

		private Node(Object data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		private Node() {

		}
	}
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		Node head = first;
        Node reverse = null;
        while (head != null) {
            Node second = head.next;
            head.next = reverse;
            reverse = head;
            head = second;
        }
        first = reverse;
	}
	//有问题
	@Test
	public void testReverse(){
		LinkedList list = getList();
		Iterator ite = list.iterator();
		while(ite.hasNext()){
			System.out.print(ite.next()+" ");
		}
		list.reverse();
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println("----");
			System.out.print(it.next()+" ");
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		int mid = (int) Math.ceil(size/2.0);
		for(int x=0;x<mid;x++){
			Node node =getNodeByIndex(x);
			node = null;
		}
		Node node = getNodeByIndex(mid);
		first = node;
		first.prev = null;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i>0){

			Node prev = getNodeByIndex(i-1);
			Node next = getNodeByIndex(i+length);
			for(int x=i;x<i+length;x++){
				Node node = getNodeByIndex(x);
				node = null;
			}
			prev.next = next;
			next.prev = prev;
		}else if(i==0){
			Node [] nodes = new Node[length];
			for(int x = i;x<i+length;x++){
				nodes[x] = getNodeByIndex(x);
			}
			first = getNodeByIndex(i+length);
			first.prev = null;
			for(int x=0;x<nodes.length;x++){
				nodes[x] = null;
			}
		}else{
			throw new IndexOutOfBoundsException();
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
        int[] array = new int[list.size];
        for (int i = 0; i < array.length; i++) {
            int element = (int) list.get(i);
            array[i] = ((Integer) get(element));
        }

        System.out.println(Arrays.toString(array));

        return array;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		int length = list.size();
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < length; j++) {
                if (get(i) == list.get(j)) {
                    remove(i);
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
		for (int i = size - 1; i > 0; i--) {
            if (get(i) == get(i - 1)) {
                remove(i);
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
		for (int i = size - 1; i >= 0; i--) {
            int element = ((int) get(i));
            if ((element > min) && element < max) {
                remove(i);
            }
        }
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList newList = new LinkedList();
        int length = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                if (get(i) == list.get(j)) {
                    newList.add(get(i));
                    break;
                }
            }
        }

        Iterator it = newList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        return newList;
	}

	/*------------------------------------------------------单元测试----------------------------------------------------*/

	/**
	 * 测试添加方法功能
	 */
	// add(Object o)
	@Test
	public void TestAddFunction() {

		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println("添加完毕");

	}

	// add(int index,Object o)
	@Test
	public void TestAddIndexFunction() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		for (int x = 0; x < list.size(); x++) {
			System.out.println(list.get(x));
		}
		list.add(3, 111);
		System.out.println(list.get(3));
	}

	// addFirst(Object o)
	@Test
	public void TestAddFirstFunction() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(0));
		list.addFirst(20);
		System.out.println(list.get(0));
	}

	// addLast(Object o)
	@Test
	public void TestAddLastFunction() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(list.size() - 1));
		list.addLast(111);
		System.out.println(list.get(list.size() - 1));
	}

	/**
	 * remove方法测试
	 */
	// removeFirst()
	@Test
	public void TestRemoveFirst() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(0));
		list.removeFirst();
		System.out.println(list.get(0));
	}

	// removeLast()
	@Test
	public void TestRemoveLast() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(list.size() - 1));
		list.removeLast();
		System.out.println(list.get(list.size() - 1));
	}

	// remove(int index)
	@Test
	public void TestRemoveFunction() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(50));
		list.remove(50);
		System.out.println(list.get(50));
	}

	/**
	 * Iterator测试
	 */
	@Test
	public void TestIterator() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		Iterator ite = list.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
	}
	
	private LinkedList getList(){
		LinkedList list = new LinkedList();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		return list;
	}
}
