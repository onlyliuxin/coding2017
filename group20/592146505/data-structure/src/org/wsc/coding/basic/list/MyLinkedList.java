package org.wsc.coding.basic.list;

public class MyLinkedList<E> implements List<E> {
	private int size;
	private Node<E> head;

	private static class Node<E> {
		E data;
		Node<E> next;

		Node(E data) {
			super();
			this.data = data;
		}

		Node(E data, Node<E> next) {
			super();
			this.data = data;
			this.next = next;
		}

	}

	public boolean add(E e) {
		linkLast(e);
		return true;
	}

	public boolean add(int index, E e) {
		checkPositionIndex(index);
		if (index == 0)
			linkFirst(e);
		else if (index == size)
			linkLast(e);
		else
			linkAfter(e, node(index - 1));
		return false;
	}
	
	/**
	 * 向头部添加元素
	 * 
	 * @param e
	 */
	private void linkFirst(E e) {
		Node<E> oldHead = head;
		Node<E> newHead = new Node<E>(e);
		head = newHead;
		// 将原头节点作为新头节点的下一个节点
		head.next = oldHead;
		size++;
	}

	/**
	 * 向尾部添加元素
	 * 
	 * @param e
	 */
	void linkLast(E e) {
		Node<E> newNode = new Node<E>(e);
		if (head == null) {
			head = newNode;
		} else {
			Node<E> node = head;
			while (true) {
				if (node.next == null) {
					node.next = newNode;
					break;
				}
				node = node.next;
			}
		}
		size++;
	}

	/**
	 * 在指定节点之后插入新节点
	 * 
	 * @param e
	 * @param node
	 */
	void linkAfter(E e, Node<E> node) {
		Node<E> oldNext = head;
		node.next = new Node<E>(e, oldNext);
		size++;
	}

	public E get(int index) {
		checkElementIndex(index);
		return node(index).data;
	}
	
	/**
	 * 获取指定索引处元素,调用此方法请确保索引范围正确
	 * 
	 * @param index
	 * @return
	 */
	Node<E> node(int index) {
		Node<E> node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	public E remove(int index) {
		checkElementIndex(index);
		Node<E> node = null;
		if(index == 0){
			node = head;
			head = node.next;
			node.next = null;
		}else{
			node = unlinkNext(node(index-1));
		}
		return node == null?null:node.data;
	}
	
	/**
	 * 删除此节点的下一个节点
	 * @param node
	 * @return
	 */
	Node<E> unlinkNext(Node<E> node){
		Node<E> nextNode  = node.next;
		node.next = nextNode.next;
		nextNode.next = null;
		size--;
		return nextNode;
	}

	public int size() {
		return size;
	}

	public void addFirst(E e) {
		linkFirst(e);
	}

	public void addLast(E e) {
		linkLast(e);
	}

	public Object removeFirst() {
		return null;
	}

	public Object removeLast() {
		return null;
	}

	public Iterator iterator() {
		return null;
	}

	/**
	 * 现有节点索引范围
	 * 
	 * @param index
	 * @return
	 */
	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	/**
	 * 可插入索引范围
	 * 
	 * @param index
	 * @return
	 */
	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size;
	}

	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	/**
	 * 现有节点索引范围检查
	 * 
	 * @param index
	 */
	private void checkElementIndex(int index) {
		if (!isElementIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	/**
	 * 可插入索引范围检查
	 * 
	 * @param index
	 */
	private void checkPositionIndex(int index) {
		if (!isPositionIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {

	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {

	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {

	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public static int[] getElements(LinkedList list) {
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {

	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {

	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E e) {
		// TODO Auto-generated method stub
		return null;
	}
}
