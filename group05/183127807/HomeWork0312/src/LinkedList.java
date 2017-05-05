public class LinkedList implements List {

	private Node head;

	private int size ;

	private Node current = head;
	public LinkedList() {
		head = null;
		size = 0;
	}

	public void add(Object o){
		Node newNode = new Node();
		newNode.data = o;
		if (current.next == null)
		{
			current.next = newNode;
		}

		while (current.next != null){
			current = current.next;
		}
		current.next = newNode;
		size++;

	}
	public void add(int index , Object o){

		Node newNode = new Node();
		newNode.data = o;
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < index - 2; i++) {

			current = current.next;
		}

		newNode.next = current.next;
		current.next = newNode;
		size++;
	}
	public Object get(int index){


		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return head;
		}
		for (int i = 0;i<index - 1; i++) {
			current = current.next;
		}

		return current;
	}

	public Object remove(int index){
		for (int i = 1; i <= index; i++) {

			if (i == index - 1) {
				current.next = current.next.next;
				size--;
			} else {
				current = current.next;
			}

		}
		return null;
	}

	public int size(){
		return size;
	}

	public void addFirst(Object o){
		Node newHead = new Node();
		newHead.data = o;
		newHead.next = head;
		head = newHead;
		size++;
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		while (current.next != null){
			current = current.next;
		}
		current.next = newNode;
		size++;
	}
	public Object removeFirst(){
		Node removeHead = head;
		head.next = head.next.next;
		size--;
		return removeHead;
	}
	public Object removeLast(){
		Node theNext = current.next;
		Node oldLast;
		while(theNext.next != null) {
			current = theNext;
			theNext = theNext.next;
		}
		oldLast=current.next;
		current.next = theNext.next;

		size--;
		return oldLast;
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}

	public Object head() {
		return head;
	}
	private class LinkedListIterator implements Iterator {
		@Override
		public boolean hasNext() {
			return current.next!=null;
		}

		@Override
		public Object next() {
			Node data = (Node) current.data;
			current.next = current.next.next;
			return data;
		}
	}


	private static  class Node{
		Object data;

		Node next;

	}
	
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		Node p = null;

		while (head != null) {
			Node next = head.next;
			head.next = p;
			p = head;
			head = next;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除后的值为7,8,10

	 */
	public  void removeFirstHalf(){
			for (int i = 0;i<size()/2;i++) {
				removeFirst();
			}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if (i < 0) {
			return;
		}
		for (int j=0;j<length;j++) {
			remove(i);
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
	public  int[] getElements(LinkedList list){
		int[] newList = new int[list.size()];
		for (int i=0;i<list.size();i++) {

			newList[i]= (int) get((Integer) list.get(i));
		}

		return newList ;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		for (int i=0;i<size();i++) {
			for (int j=0;j<list.size();j++) {
				while (current != list.current) {
					list.current = list.current.next;
				}
				list.remove(j);
			}
			current = current.next;
		}
		intersection(list);
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node next = head.next;
		for (int i=0;i<size();i++) {
			if (current.data == next.data) {
				remove(i);
				current = current.next;
				next = next.next;
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
		if (min < max) {
			return;
		}
		for (int i=0;i<size();i++) {
			if ((int) get(i) > min && (int) get(i) < max) {
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
		if (list.size() == 0) {
			return null;
		}
		LinkedList newList = new LinkedList();
		this.head = list.head;
		Node temp;

		//确定新的头结点
		if((int)this.head.data <= (int)list.head.data){
			head = this.head;
			temp = this.head;
			this.head = this.head.next;
		}else{
			head = list.head;
			temp = list.head;
			list.head = list.head.next;
		}
		//合并
		while(this.head != null && list.head!=null){
			if((int)this.head.data <= (int)list.head.data){
				temp.next = this.head;
				temp = temp.next;
				this.head = this.head.next;
			}else{
				temp.next = list.head;
				temp = temp.next;
				list.head = list.head.next;
			}
		}
		//合并剩余的元素
		if(this.head != null){
			temp.next = this.head;
		}
		if(list.head != null){
			temp.next = list.head;;
		}
		return newList;
	}
}
