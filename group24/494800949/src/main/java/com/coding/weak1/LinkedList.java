package com.coding.weak1;


public class LinkedList implements List {
	
	private Node head;
	private int size;
	public void add(Object o){
        addLast(o);
	}
	public void add(int index , Object o){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("index: "+index);
        }
        if (index == size){
            addLast(o);
        }else if(index == 0) {
            addFirst(o);
        }else {
            Node preNode = node(index - 1);
            Node indexNode = node(index);
            preNode.next = new Node(o, indexNode);
            size++;
        }

	}


	public Object get(int index){
        Node node = node(index);
		return node.data;
	}

    private Node node(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index: "+index + ",size: "+size);
        }
        Node node = head;
        for (int i = 0; i < size; i++ ){
            if(i == index){
                return node;
            }
            node = node.next;
        }
        return null;
    }

	public Object remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index: "+index + ",size: "+size);
        }
        if(index == 0){
            return removeFirst();
        }else if(index == size-1){
            return removeLast();
        }
        Node indexNode = node(index);
        Node prev = node(index - 1);
        Node next = node(index + 1);
        prev.next = next;
        size--;
		return indexNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
        if (head == null)
            head = new Node(o, null);
        else {
            Node node = new Node(o, head);
            head = node;
        }
        size++;
	}


	public void addLast(Object o){
        if(head == null){
            head = new Node(o, null);
        }else {
            Node node = head;
            for(int i = 0; i < size-1; i++){
                node = node.next;
            }
            node.next =  new Node(o, null);
        }
		size++;
	}

	public Object removeFirst(){
        if(head == null)
            return null;
        Node oldFirst = head;
        head = oldFirst.next;
        size--;
		return oldFirst;
	}
	public Object removeLast(){
        if(head == null)
            return null;
        Node oldLast = node(size - 1);
        Node preLast;
        if (size - 2 >= 0){
           preLast  = node(size - 2);
            preLast.next = null;
        } else {
            head = null;
        }
        size--;
		return oldLast;
	}
	public Iterator iterator(){

		return new LinkedListIter();
	}

    private class LinkedListIter implements Iterator{

        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor++ < size;
        }

        @Override
        public Object next() {
            int index = cursor;
            if(index > size)
                throw new IndexOutOfBoundsException();
            Node node = node(index);
            cursor++;
            return node.data;
        }
    }
	
	private static  class Node{
		Object data;
		Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
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
	public  void remove(int i, int length){
		
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public static int[] getElements(LinkedList list){
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

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
