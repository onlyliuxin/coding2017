package linkList;

/**
 * 单向链表：其中头结点（head）中没有数据，第一个数据是head结点的下一个结点存贮
 * @author 12946
 *
 */
public class LinkedList implements List {
	
	private Node head;//头结点
	private int size;//链表中元素的个数
	
	public LinkedList(){
		this.head = new Node(null);
		this.size = 0;
	}
	
	
	public void add(Object o){
		Node newNode = new Node(o);
		Node tempNode = head;
		while(tempNode.next != null){
			tempNode = tempNode.next;
		}
		tempNode.next = newNode;
		size++;
	}
	
	public void add(int index , Object o){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("索引越界");
		}
		Node newNode = new Node(o);
		Node tempNode = null;
		if(index == 0){
			tempNode = head;
		}else{
			tempNode = getNode(index - 1);
		}
		newNode.next = tempNode.next;
		tempNode.next = newNode;
		
		size++;
	}
	
	public Object get(int index){
		
		return getNode(index).data;
	}
	
	public Node getNode(int index){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("索引越界");
		}
		Node tempNode = head;
		for(int i = -1; i < index; i++){
			tempNode = tempNode.next;
		}
		return tempNode;
	}
	
	public Object remove(int index){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("索引越界");
		}
		Node tempNode = null;
		if(index == 0){
			tempNode = head;
		}else{
			tempNode = getNode(index - 1);
		}
		tempNode.next = tempNode.next.next;
		size--;
		return tempNode.next.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(o);
		newNode.next = getNode(0);
		head.next = newNode;
		size++;
	}
	public void addLast(Object o){
		getNode(size-1).next = new Node(o);
		size++;
	}
	public Object removeFirst(){
		if(size < 1){
			throw new IndexOutOfBoundsException("链表为null,不能删除元素");
		}
		head.next = head.next.next;
		size--;
		
		return head.next.data;
	}
	public Object removeLast(){
		if(size < 1){
			throw new IndexOutOfBoundsException("链表为null,不能删除元素");
		}
		
		Object data = null;
		if(size == 1){
			data = head.next.data;
			head.next = null;
		}else{
			Node tempNode = getNode(size-2);
			data = tempNode.next.data;
			tempNode.next = null;
		}
		size--;
		return data;
	}
	
	/**
	 * 根据元素的值查找元素在链表中的位置，找不到则返回-1
	 * @param o
	 * @return
	 */
	public int findIndex(Object o){
		for(int i = 0; i < size; i++){
			if(get(i).equals(o)){
				return i;
			}
		}
		
		return -1;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < size; i++){
			sb.append(get(i));
			if(i != size-1){
				sb.append(",");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	private static class Node{
		Object data;
		Node next = null;
		
		public Node(Object data){
			this.data = data;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		int[] arr = new int[size];
		for(int i = 0; i < size; i++){
			arr[i] = (Integer) get(size - i - 1);
		}
		LinkedList newLinkedList = new LinkedList();
		for(int i = 0; i < size; i++){
			newLinkedList.add(arr[i]);
		}
		
		head = newLinkedList.head;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int index = size/2;
		head.next = getNode(index);
		size = size - index;
	}
	
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i < 0 ){
			throw new IndexOutOfBoundsException("索引越界");
		}
		
		Node startNode = null;
		Node endNode = null;
		
		if(i == 0){
			startNode = head;
		}else{
			startNode = getNode(i - 1);
		}
		
		if(i + length >= size){
			endNode = null;
			size = i;
		}else{
			endNode = getNode(i + length);
			size = size - length;
		}
		startNode.next = endNode;
		
		
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
		if((Integer)list.get(list.size-1) > this.size - 1){
			throw new IndexOutOfBoundsException("list指定的元素角标超过当前链表的范围");
		}
		int[] arr = new int[list.size];
		for(int i = 0; i < arr.length; i++){
			int index = (Integer) list.get(i);
			arr[i] = (Integer) this.get(index);
		}
		
		return arr;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		for(int i = 0; i < list.size; i++){
			Object value = list.get(i);
			int index = this.findIndex(value);
			if(index != -1){
				this.remove(index);
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		LinkedList newLinkedList = new LinkedList();
		for(int i = 0; i < size; i++){
			Object value = this.get(i);
			if(newLinkedList.findIndex(value) == -1){
				newLinkedList.add(value);
			}
		}
		this.head = newLinkedList.head;
		this.size = newLinkedList.size;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		int start = 0;
		int end = size - 1;
		for(int i = 0; i < size; i++){
			int value = (Integer) get(i);
			if(value <= min){
				start++;
			}
		}
		
		for(int j = size-1; j > -1; j--){
			int value = (Integer) get(j);
			if(value >= max){
				end--;
			}
		}
		
		Node tempNode = null;
		if(start == 0){
			tempNode = head;
		}else{
			tempNode = getNode(start - 1);
		}
		tempNode.next = getNode(end).next;
		size = size - (end - start + 1);
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList newLinkedList = new LinkedList();
		for(int i = 0; i < list.size; i++){
			int value = (Integer) list.get(i);
			int index = findIndex(value);
			if(index != -1){
				newLinkedList.add(value);
			}
		}
		
		return newLinkedList;
	}
}
