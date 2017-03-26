package com.xiaol.study;

/**
 * @Description TODO
 * @date 创建时间：2017年3月5日 上午12:30:18
 */
public class LinkedList implements List {

	// 头指针
	private Node head;

	// 最后一个节点
	private Node lastNode;

	// 链表长度
	private int size;

	// 添加元素到最后一个节点
	public void add(Object o) {
		if (null == head) {
			head = new Node(o);
			head.next = null;
			lastNode = head;
			size++;
		} else {
			lastNode.next = new Node(o);
			lastNode = getLastNode();
			size++;
		}
	}

	// 获取最后一个节点
	private Node getLastNode() {
		Node last = head;
		while (last.next != null) {
			last = last.next;
		}
		return last;
	}

	// 获取指定位置的Node
	private Node getIndexNode(int index) {
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	// 添加元素到指定位置
	public void add(int index, Object o) {
		checkIndex(index);
		if (index == 0) {
			// 将head暂存为headNext
			Node headNext = head;
			// 创建新的head
			Node newHead = new Node(o);
			head = newHead;
			head.next = headNext;
		} else {
			// 获取第index-1个位置的Node
			Node node = getIndexNode(index - 1);
			// 暂存index位置的Node
			Node nodeNext = node.next;
			// 向index位置插入数据
			node.next = new Node(o);
			node.next.next = nodeNext;
		}
		size++;
	}

	// 获取指定位置元素
	public Object get(int index) {
		checkIndex(index);
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.data;
	}

	private void checkIndex(int index) {
		if (index < 0 || index > size) {
			throw new RuntimeException("非法参数");
		}
	}

	// 移除指定位置元素
	public Object remove(int index) {
		checkIndex(index);
		Object data = null;
		if (index == 0) {
			// 新的head
			Node newHead = head.next;
			// 获取移除元素
			data = head.data;
			// 将newHead设为head
			head = newHead;
		} else {
			// 获取第index-1个位置的Node
			Node node = getIndexNode(index - 1);
			// 获取返回的元素
			data = node.next.data;
			// 将index位置从链表删除
			node.next = node.next.next;
		}
		size--;
		return data;
	}

	// 获取链表长度
	public int size() {
		return size;
	}

	// 添加元素到第一个节点
	public void addFirst(Object o) {
		add(0, o);
	}

	public void addLast(Object o) {
		Node lastNode = getLastNode();
		lastNode.next = new Node(o);
		size++;
	}

	// 移除第一个元素
	public Object removeFirst() {
		return remove(0);
	}

	// 移除最后一个元素
	public Object removeLast() {
		Node lastNode = getLastNode();
		Object data = lastNode.data;
		lastNode = null;
		size--;
		return data;
	}

	public Iterator iterator() {
		return null;
	}

	// 用于存储数据的节点
	private static class Node {
		Object data;
		Node next;

		// 无参构造
		private Node() {

		}

		// 有参构造
		private Node(Object o) {
			this.data = o;
		}
	}
	
	//---------------下面的方法暂时先不实现
	// removeDuplicateValues（），removeRange（）可以不实现
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		if (i < 0) {
			throw new RuntimeException("非法参数");
		}
		Node indexNode = getIndexNode(i);
		Node nextNode = getIndexNode(i);
		for (int j = 0; j < length; j++) {
			nextNode = nextNode.next;
		}
		indexNode.next = nextNode;
	}
	
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		int[] retVal = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			int index = (int) list.get(i);
			Node indexNode = getIndexNode(index);
			int data = (int) indexNode.data;
			retVal[i] = data;
		}
		return retVal;
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
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}
