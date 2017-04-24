package dataStruct.com.coding.basic;

import java.util.NoSuchElementException;

/**
 * Created by songbao.yang on 2017/2/21.
 *
 */
public class LinkedList<T extends Comparable> implements List<T> {
	
	private Node head;
	private int elementCount;

    //head作为一个节点，其next的值指向List中真正的第一个节点
	public LinkedList() {
        head = new Node();
        head.next = null;
        head.data = null;
        elementCount = 0;
	}

	public void add(T o){
		Node newNode = new Node();
		newNode.data = o;
        newNode.next = null;

		Node cursor = head;
        while (cursor.next != null){
            cursor = cursor.next;
        }
        cursor.next = newNode;
		elementCount++;
	}


	public void add(int index , T o){
        indexRangeCheck(index);
        Node newNode = new Node();
        newNode.data = o;

        Node cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;  //将cursor移动到index-1节点处；
        }

        newNode.next = cursor.next; //将新节点指向原index处的节点
        cursor.next = newNode;//将原index-1处的节点指向新节点
        elementCount++;
    }

	private void indexRangeCheck(int index){
	    if (index < 0 || index >= size()){
	        throw new IndexOutOfBoundsException();
        }
    }

	public T get(int index){
		indexRangeCheck(index);
        Node cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        return (T) cursor.next.data;
    }

	public T remove(int index){
	    indexRangeCheck(index);
        Node cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        Node indexNode = cursor.next;
        cursor.next = indexNode.next;
        elementCount--;
		return (T) indexNode;
	}
	
	public int size(){
		return elementCount;
	}
	
	public void addFirst(T o){
        Node node = new Node();
        node.data = o;
        node.next = head.next;
        head.next = node;
        elementCount++;
    }

	public void addLast(T o){

	    Node cursor = head;
        while (cursor.next != null){
            cursor = cursor.next;
        }
        Node newNode = new Node();
        newNode.data = o;
        newNode.next = null;
        cursor.next = newNode;
        elementCount++;
    }

	public T removeFirst(){

	    if (size() == 0){
	        throw new RuntimeException("no element in list");
        }
        Node firstNode = head.next;
        head.next = head.next.next;
        elementCount--;
        return (T) firstNode;
	}

	public T removeLast(){
	    if (size() == 0){
            throw new RuntimeException("no element in list");
        }

	    Node cursor = head;
        for (int i = 0; i < size() - 1; i++) {
            cursor = cursor.next;
        }

        Node lastNode = cursor.next;
        cursor.next = null;
        elementCount--;

        return (T) lastNode;
    }

	public Iterator iterator(){
		return new Itr();
	}

	private class Itr implements Iterator {

	    private Node itrCursor = head;

        public boolean hasNext() {

            return itrCursor.next != null;
        }

        public Object next() {
            if (hasNext()){
                return itrCursor.next;
            }
            throw new NoSuchElementException();
        }
    }

	private static class Node<T extends Comparable>{
		T data;
		Node next;
	}

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public  void reverse(){
        if (elementCount <= 1){
            return;
        }

        Node first = head.next;
        Node second = head.next.next;

        first.next = null;
        while (second != null){
            Node temp = second.next;
            second.next = first;
            first = second;
            second = temp;
        }
        head.next = first;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

     */
    public  void removeFirstHalf(){

        Node cousor = head.next;
        for (int i = 0; i < elementCount / 2; i++) {
            Node temp = cousor;
            cousor = cousor.next;
            temp.data = null;
            temp.next = null;
        }
        head.next = cousor;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * @param i
     * @param length
     */
    public  void remove(int i, int length){
        if (i < 0 || length < 0){
            return;
        }
        if (i > elementCount - 1){
            throw new IndexOutOfBoundsException("index i is too big");
        }

        Node beforei = head;
        for (int j = 0; j < i; j++) {
            beforei = beforei.next;
        }
        Node cursor = beforei.next;
        for (int j = 0; j < length; j++) {
            if (cursor != null){
                Node temp = cursor;
                cursor = cursor.next;
                temp.data = null;
                temp.next = null;
            }else {
                break;
            }
        }
        beforei.next = cursor;
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

        int[] result = new int[list.size()];
        int i = 0;
        int pre = 0;
        Node cousor = head.next;
        Iterator YIterator = list.iterator();
        while (YIterator.hasNext()){
            int index = (Integer) YIterator.next();
            if (index > elementCount - 1){
                break;
            }
            moveForwardNIndex(cousor, index - pre);
            result[i++] = (Integer) cousor.data;
            pre = index;
        }

        int[] ints = new int[result.length];
        System.arraycopy(result, 0, ints, 0, result.length);
        return ints;
    }

    private void moveForwardNIndex(Node index, int n){
        for (int i = 0; i < n; i++) {
            if (index == null){
                break;
            }
            index = index.next;
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素

     * @param list
     */

    public  void subtract(LinkedList list){

        Node pre = head;
        Node node = head.next;
        while (node != null){
            if (list.contains(node.data)){
                pre.next = node.next;
                node = node.next;
            } else {
                pre = node;
                node = node.next;
            }
        }
    }

    public boolean contains(T data){

        Node cursor = this.head.next;
        while (cursor != null){
            if (cursor.data.equals(data)){
                return true;
            }
        }
        return false;
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public  void removeDuplicateValues(){

        if (elementCount <= 1){
            return;
        }
        Node pre = head.next;
        Node node = pre.next;
        while (node != null){
            if (node.data.equals(pre.data)){
                pre.next = node.next;
                node = node.next;
            } else {
                pre = node;
                node = node.next;
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * @param min
     * @param max
     */
    //TODO  这个泛型的比较没搞明白， 为什么会出现cast的问题，不应该都是T类型吗
    public  void removeRange(T min, T max){
        if (min.compareTo(max) > 0){
            return;
        }
        if (size() == 0){
            return;
        }
        Node beforeMin = head;
        //泛型化
        while (beforeMin.next != null && beforeMin.next.data.compareTo(min) <= 0){
            beforeMin = beforeMin.next;
        }
        Node afterMax = beforeMin.next;
        while (afterMax != null && afterMax.data.compareTo(max) < 0){
            afterMax = afterMax.next;
        }
        beforeMin.next = afterMax;
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * @param list
     */
    public LinkedList intersection(LinkedList list){

        if (list == null || list.size() == 0 || this.size() == 0){
            return new LinkedList();
        }

        Node cursorA = this.head.next;
        Node cursorB = list.head.next;
        LinkedList<T> listC = new LinkedList<T>();

        while (cursorA != null && cursorB != null){
            if (cursorA.data.compareTo(cursorB.data) == 0){
                listC.add((T)cursorA.data);
            } else if (cursorA.data.compareTo(cursorB.data) < 0){
                cursorA = cursorA.next;
            } else {
                cursorB = cursorB.next;
            }
        }
        return listC;
    }

    public void addAfter(Node node, T o){
        if (node == null){
            return;
        }
        Node<T> newNode = new Node<T>();
        newNode.data = o;
        addAfter(node, newNode);
    }

    public void addAfter(Node node, Node newNode){
        if (node == null || newNode == null){
            return;
        }
        newNode.next = node.next;
        node.next = newNode;
    }
}
