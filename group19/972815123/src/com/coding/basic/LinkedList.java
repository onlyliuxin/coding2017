package com.coding.basic;

public class LinkedList implements List,Iterator {
	
	private Node head;
	private Node last;
	private int size = 0;
	
	public LinkedList() {
		head = new Node();
	}

	@Override
	public void add(Object o) {
		
		
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		
		if(size == 0){
			head = newNode;
			size = 1;
			return;
		}
		
		Node _last = head;
		while(_last.next != null){
			_last = _last.next;
		}
		_last.next = newNode;
		newNode.prev = _last;
		last = newNode;
		size++;
	}

	@Override
	public void add(int index, Object o) {
		Node newNode = new Node();
		Node indexNode = head ;
		int i = 0;
		while(i == index){
			indexNode = indexNode.next;
			i++;
		}
		Node indexNextNode = indexNode.next;
		indexNode.next = newNode;
		newNode.prev = indexNode;
		newNode.next = indexNextNode;
		indexNextNode.prev = newNode;
		size ++;
	}

	@Override
	public Object get(int index) {
		Node indexNode = head;
		int i = 0;
		while(i == index){
			indexNode = indexNode.next;
			i++;
		}
		return indexNode;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object remove(int index) {
		Node indexNode = head ;
		int i = 0;
		while(i != index){
			
			indexNode = indexNode.next;
			i++;
		}
		Object o = indexNode.prev;
		Node indexNextNode = indexNode.next;
		Node indexPrevNode = indexNode.prev;
		
		indexNextNode.prev = indexPrevNode;
		indexPrevNode.next = indexNextNode;
		
		indexNode.next = null;
		indexNode.prev = null;
		size--;
		return o;
	}

	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
		size ++;
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.prev = last;
		last.next = newNode;
		last = newNode;
		size ++;
	}
	public Object removeFirst(){
		Node ret = head;
		head = head.next;
		head.prev = null;
		size--;
		return ret;
	}
	public Object removeLast(){
		Node ret = last;
		last = last.prev;
		last.next = null;
		size--;
		return ret;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		Node prev;
	}

	private Node index = head;
	@Override
	public boolean hasNext() {
		return index != null;
	}

	@Override
	public Object next() {
		Node tem = index;
		index = index.next;
		return tem;
	}
	
	@Override
	public String toString(){
		Node node = head;
		StringBuffer sb = new StringBuffer();
		while(node.next != null){
			sb.append(node.data.toString() + ",");
			node = node.next;
		}
		sb.append(node.data.toString());
		return sb.toString();
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){		
		Node index = head;
		Node temPre = null;
		while(true){
			Node tem = index.next;
			if(index.next == null){
				head = index;
				index.next = temPre;
				break;
			}
			index.next = temPre;
			temPre = index;
			index = tem;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		int i = 0;
		Node node = head;
		while(i < size/2 + 1){
			head = node;
			node = node.next;
			i++;
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		//省略了数据合法性检查；
		Node indexStart = head;
		int index = 0;
		for(int j = 0; j < i - 1; j++){
			indexStart= indexStart.next;
		}
		Node indexEnd = indexStart;
		Node tem = null;
		for(int k = 0 ; k <= length; k++){
			tem = indexEnd;
			indexEnd = indexEnd.next;
		}
		tem.next = null;
		indexStart.next = indexEnd;
	}
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  Object[] getElements(LinkedList list){
		//省略了数据合法性检查；
		Node pointHead = list.head;
		Node node = head;
		Object[] result = new Object[list.size];
		int point = 0;
		int resultPoint = 0;
		while(true){
			int temPoint = (int)pointHead.data;
			if(point == temPoint){
				result[resultPoint] = node.data;
				if(pointHead.next == null){
					break;
				}
				resultPoint++;
				pointHead = pointHead.next;
			}
			if(node.next == null ){
				break;
			}
			node = node.next;
			point++;
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	//m * n 复杂度的算法，没有利用原链表已排序的特性；
	public  void subtract(LinkedList list){
		Node indexNode = head;
		Node indexPreNode = null;
		while(null != indexNode){
			Node pointNode = list.head;
			while(null != pointNode){
				if((int)pointNode.data == (int)(indexNode.data)){
					if(indexPreNode == null){
						head = indexNode.next;
					}else{
						indexPreNode.next = indexNode.next;
					}
				}
				pointNode = pointNode.next;
			}
			indexPreNode = indexNode;
			indexNode = indexNode.next;
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node indexNode = head;
		Node indexPreNode = null;
		while(null != indexNode){
			if(null == indexPreNode){
				indexPreNode = indexNode;
				indexNode = indexNode.next;
				continue;
			}
			if((int)indexPreNode.data == (int)indexNode.data){
				indexPreNode.next = indexNode.next;
			}else{
				indexPreNode = indexNode;
			}
			indexNode = indexNode.next;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Node indexNode = head;
		Node indexPreNode = null;
		Node minNode = null;
		Node maxNode = null;
		boolean getMin = false, getMax = false;
		while(indexNode != null){
			if((int)indexNode.data >= min){
				if(!getMin){
					minNode = indexPreNode;
					getMin = true;
				}
			}
			
			if((int)indexNode.data > max){
				if(!getMax){
					maxNode = indexNode;
					break;
				}
			}
			indexPreNode = indexNode;
			indexNode = indexNode.next;
		}
		if(null == minNode && null == maxNode){
			head.data = null;
			head.next = null;
		}else if(null != minNode && null != maxNode){
			minNode.next = maxNode;
		}
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList result = new LinkedList();
		Node indexNode = head;
		while(null != indexNode){
			Node pointNode = list.head;
			while(null != pointNode){
				if((int)pointNode.data == (int)(indexNode.data)){
					result.add(indexNode.data);
				}
				pointNode = pointNode.next;
			}
			indexNode = indexNode.next;
		}
		return result;
	}
	
}
