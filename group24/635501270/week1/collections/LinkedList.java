package week1.collections;

public class LinkedList implements List {
	private int size = 0;
	private Node head;
	private Node last;
	
	public boolean add(Object o){
		Node newNode = new Node(o);
		if(head == null){
			last = newNode;
			head = newNode;
		}else{
			Node oldLast = last;
			last = newNode;
			oldLast.next = last;
		}
		size++;
		return true;
	}
	public void add(int index , Object o){
		outOfIndex(index);
		if(index == 0){
			Node oldHead = head;
			head = new Node(o);
			head.next = oldHead;
		}else{
			Node h = getNode(index-1);
			Node newNode = new Node(o);
			newNode.next = h.next;
			h.next = newNode;
		}
		size++;
	}
	public Object get(int index){
		Node h = getNode(index);
		return h.data;
	}
	private Node getNode(int index) {
		outOfIndex(index);
		Node h = head;
		for(int i=0;i<index;i++){
			h = h.next;
		}
		return h;
	}
	private void outOfIndex(int index) {
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index"+index+"越界");
		}
	}
	public Object remove(int index){
		outOfIndex(index);
		Object data;
		if(index==0){
			Node oldHead = head;
			head = head.next;
			data = oldHead.data;
			oldHead = null;
		}else{
			Node preNode = getNode(index-1);
			if(preNode.next==last){
				Node oldLast = last;
				last = preNode;
				data = oldLast.data;
				oldLast = null;
			}else{
				Node removeNode = preNode.next;
				preNode.next = preNode.next.next;
				data = removeNode.data;
				removeNode = null;
			}
		}
		size--;
		return data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0,o);
	}
	public void addLast(Object o){
		if(last==null){
			add(o);
		}else{
			Node oldLast = last;
			last = new Node(o);
			oldLast.next = last;
		}
		size++;
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements Iterator{
		int pos = 0;
		@Override
		public boolean hasNext() {
			return pos<size();
		}

		@Override
		public Object next() {
			return get(pos++);
		}
	}
	
	private static  class Node{
		Object data;
		Node next;
		public Node(){
			
		}
		public Node(Object o){
			this.data = o;
			this.next = null;
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
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(LinkedList list){
		return null;
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
