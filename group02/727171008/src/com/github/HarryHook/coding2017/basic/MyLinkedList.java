/*
 * created by Harry 2017-2-21 14:43:41
 * 实现简单的LinkedList
 */

package com.github.HarryHook.coding2017.basic;

public class MyLinkedList implements List {
    private Node head = null; // 头指针
    private int size = 0;

    private static class Node {
	Object data;
	Node next;
    }

    public void add(Object o) {
	addLast(o);
    }

    // 在指定位置添加元素
    public void add(int index, Object o) {

	if (index > size || index < 0) {
	    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}

	// 存在插入头结点的情况
	if (index == 0) {
	    addFirst(o);
	} else { // 即 index != 0 的情况
		 // p保存待插入节点的前一节点，x指向要插入的节点
	    Node x = head;
	    Node p = null;
	    int i = 0;
	    while (i < index) {
		p = x;
		x = x.next;
		i++;
	    }
	    Node n = new Node();
	    p.next = n;
	    n.next = x;
	    n.data = o;
	    size++;
	}

    }

    // 返回指定位置元素
    public Object get(int index) {
	if (index >= size) {
	    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}
	Node x = head;
	int i = 0;
	while (i < index && x != null) {
	    x = x.next;
	    i++;
	}
	return x.data;
    }

    // 移除指定位置节点
    public Object remove(int index) {
	if (index > size || index < 0) {
	    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}
	// 先判断是否是头节点
	if (index == 0) {
	    return removeFirst();
	} else {
	    Node x = head;
	    Node pre = null;
	    int i = 0;
	    while (i < index) {
		pre = x;
		x = x.next;
		i++;
	    }
	    Object Data = pre.next.data;
	    pre.next = x.next;
	    x = null;
	    size--;
	    return Data;
	}

    }

    // 头部添加节点
    public void addFirst(Object o) {
	Node n = new Node();
	n.next = head;
	head = n;
	n.data = o;
	size++;
    }

    // 尾部添加节点
    public void addLast(Object o) {
	if (head == null) {
	    head = new Node();
	    head.data = o;
	} else {
	    Node x = head;
	    while (x.next != null) {
		x = x.next;
	    }
	    Node n = new Node();
	    x.next = n;
	    n.next = null;
	    n.data = o;
	}
	size++;
    }

    // 移除第一个节点
    public Object removeFirst() {
	Node n = head;
	Object Data = n.data;
	head = head.next;
	n = null;
	size--;
	return Data;
    }

    // 移除最后一个节点
    public Object removeLast() {
	Node x = head;
	Node p = null;
	if (x.next == null) {
	    return removeFirst();
	} else {
	    while (x.next != null) {
		p = x;
		x = x.next;
	    }
	    Object Data = x.data;
	    p.next = null;
	    x = null; // 删除最后一个节点
	    size--;
	    return Data;
	}
    }

    public int size() {
	return size;
    }

    public Iterator iterator() {
	return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator {
	private int cursor = 0; // 记录索引位置

	public boolean hasNext() {
	    return cursor != size;
	}

	public Object next() {
	    Object next = get(cursor);
	    cursor++;
	    return next;
	}
    }

    /**
     * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
     */
    public void reverse() {
	if (head == null) {
	    return;
	}
	Node p1, p2, p3;
	p1 = head;
	p2 = p1.next;
	while (p2 != null) {
	    p3 = p2.next;
	    p2.next = p1;
	    p1 = p2;
	    p2 = p3;
	}
	head.next = null;
	head = p1;
    }

    /**
     * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
     * ,删除以后的值为7,8,10
     * 
     */
    public void removeFirstHalf() {
	if (size == 0) {
	    return;
	}
	Node p = head;
	Node q = null;
	int i = size / 2;
	int j = 0;
	while (j < i) {
	    j++;
	    q = p;
	    p = p.next;
	}
	head = p;
	q.next = null;
	size = size - i;

    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * 
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
	Node p = head;
	Node q = null;
	int index = 0;
	int j = 0;

	while (index < i) {
	    q = p;
	    p = p.next;
	    index++;
	}

	while (p != null && j < length) {
	    p = p.next;
	    j++;
	}
	if (i == 0) // 从头开始移除元素
	{
	    if (p == null) // 元素全被删光
	    {
		head = null;
		size = 0;
	    } else // 从头删length个元素
	    {
		head = p;
		size = size - length;
	    }
	} else // 从中间开始移除元素
	{
	    if (p == null) {
		q.next = null;
		size = size - j;
	    } else {
		q.next = p;
		size = size - length;
	    }
	}

    }

    /**
     * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
     * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     * 
     * @param list
     */
    public int[] getElements(MyLinkedList list) {
	if (list == null) {
	    return new int[0];
	}
	int i = 0;
	int[] array = new int[list.size()];
	while (i < list.size()) {
	    array[i] = (int) this.get((int) list.get(i));
	    i++;
	}
	return array;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
     * 
     * @param list
     */

    public void subtract(MyLinkedList list) {
	int i = 0;
	if (list == null) {
	    return;
	}
	while (i < list.size()) {
	    for (int index = 0; index < this.size(); index++) {
		Node p = head; // 当前节点
		Node q = null; // 前驱节点
		while (list.get(i) != p.data && p.next != null) {
		    q = p;
		    p = p.next;
		}
		if (p.data == list.get(i)) { // 删除找到的节点
		
		    if (p == head) {
			head = head.next;
		    } else {
			q.next = p.next;
		    }
		    size--;
		}
	    }

	    i++;
	}
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
	if (head == null) {
	    return;
	}
	Node p = head;
	Node q = head; // 前驱
	while (p.next != null) {
	    p = p.next;

	    while (p.data == q.data) {
		size--;
		if (p.next == null) {
		    q.next = null;
		    break;
		}
		q.next = p.next;
		p = p.next;
		if (p == null)
		    break;
	    }
	    q = q.next;
	}
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * 
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {

	Node p = head; // 当前节点
	Node q = head; // 前驱节点
	while (p.next != null && ((int) p.data <= min || (int) p.data >= max)) { // 未找到继续遍历
	    q = p;
	    p = p.next;
	}
	while ((int) p.data > min && (int) p.data < max) { // 删除找到的节点
	    p = p.next;
	    size--;

	    if (size == 0) // 删完所有元素
		break;
	}
	if (q == head) { // 头结点被删掉
	
	    head = p;
	} else {
	    q.next = p;
	}

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * 
     * @param list
     */
    public MyLinkedList intersection(MyLinkedList list) {
	if (list == null || this == null) {
	    return null;
	}
	Node p1 = list.head;
	Node p2 = this.head;
	MyLinkedList newList = new MyLinkedList();

	while (p1 != null && p2 != null) {
	    while (((int) p1.data < (int) p2.data) && p1.next != null) {
		p1 = p1.next;
	    }
	    while (((int) p1.data > (int) p2.data) && p2.next != null) {
		p2 = p2.next;
	    }
	    if (p1.data == p2.data) {
		newList.add(p1.data);
		p1 = p1.next;
		p2 = p2.next;
	    }

	    if (p1 == null && p2 == null)  { // 若最后两个元素相等
		break;
	    }
	}
	return newList;
    }

    public static void Print(MyLinkedList myList) {
	Iterator it = myList.iterator();
	System.out.println("对链表中的元素进行打印：");
	while (it.hasNext())
	    System.out.print(it.next() + " ");
	System.out.println("");
	System.out.println("当前元素个数： " + myList.size());
	System.out.println("");
    }

}
