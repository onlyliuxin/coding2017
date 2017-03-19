package com.basic;

public class LinkedList implements List
{

	private Node head;

	public void add(Object o)
	{
		if (head == null) {
			head = new Node(o, null);
		} else {
			Node nodePointer = head;
			while (nodePointer.next != null) {
				nodePointer = nodePointer.next;
			}
			nodePointer.next = new Node(o, null);
		}

	}

	public void add(int index, Object o)
	{
		int size = size();
		if (index < 0 || index > size) {
			String errorInfo = "Invalid index to add:" + index+ " out of range: [0," + size + "]";
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}
		int step = 0;
		Node nodePointer = head;
		while (step < index) {
			nodePointer = nodePointer.next;
			++step;
		}
		nodePointer.next = new Node(o, nodePointer.next);
	}

	public Object get(int index)
	{
		int size = size();
		if (index < 0 || index > size) {
			String errorInfo = "Invalid index to get:" + index+ " out of range: [0," + size + "]";
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}
		
		int step = 0;
		Node nodePointer = head;
		while (step < index) {
			nodePointer = nodePointer.next;
			++step;
		}
		
		return nodePointer.data;
	}

	public Object remove(int index)
	{
		int size = size();
		if (index < 0 || index > size) {
			String errorInfo = "Invalid index to remove:" + index+ " out of range: [0," + size + "]";
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}
		
		int step = 0;
		Node nodePointer = head;
		Node lastPointer = head;
		
		while (step < index) {
			lastPointer = nodePointer;
			nodePointer = nodePointer.next;
			++step;
		}
		
		Object o = null;
		
		if (lastPointer == nodePointer) {
			Node toDelete = head;
			o = toDelete.data;
			head = head.next;
			toDelete = null;
		} else {
			o = nodePointer.data;
			lastPointer.next = nodePointer.next;
			nodePointer = null;
		}		
		
		return o;
	}

	public int size()
	{
		int size = 0;
		if (head != null) {
			++size;
			Node nodePointer = head;
			while (nodePointer.next != null) {
				++size;
				nodePointer = nodePointer.next;
			}
		}
		return size;
	}

	public void addFirst(Object o)
	{
		if (head == null) {
			head = new Node(o, null);
			return;
		}
		head = new Node(o, head);
	}

	public void addLast(Object o)
	{
		if (head == null) {
			head = new Node(o, null);
			return;
		}
		
		Node nodePointer = head;
		while (nodePointer.next != null) {
			nodePointer = nodePointer.next;
		}
		nodePointer.next = new Node(o, null);
	}

	public Object removeFirst()
	{
		if (head == null) {
			return null;
		}
		
		Node toDelete = head;
		Object o = head.data;
		head = head.next;
		toDelete = null;
		
		return o;
	}

	public Object removeLast()
	{
		if (head == null) {
			return null;
		}

		Node nodePointer = head;
		Node lastPointer = head;
		
		while (nodePointer.next != null) {
			lastPointer = nodePointer;
			nodePointer = nodePointer.next;
		}
		lastPointer.next = null;
		Object o = nodePointer.data;
		nodePointer = null;
		
		return o;
	}

	public Iterator iterator()
	{
		return new Iterator() {
			
			private Node nodePointer = head;
			
			public boolean hasNext()
			{
				// TODO Auto-generated method stub
				return (nodePointer != null);
			}

			public Object next()
			{
				// TODO Auto-generated method stub
				if (hasNext()) {
					Object o = nodePointer.data;
					nodePointer = nodePointer.next;
					return o;
				}
				return null;
			}			
		};
	}

	private static class Node
	{
		Object data;
		Node next;
		public Node(Object o, Node n) {
			this.data = o;
			this.next = n;
		}
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse()
	{
		if (head == null) {
			return;
		}
		
		Node reverse = null;
		while (size() > 0) {
			reverse = new Node(removeFirst(), reverse);
		}
		
		head = reverse;
	}


	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf()
	{
		
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length)
	{

	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public static int[] getElements(LinkedList list)
	{
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list)
	{

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues()
	{

	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max)
	{

	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list)
	{
		return null;
	}
}
