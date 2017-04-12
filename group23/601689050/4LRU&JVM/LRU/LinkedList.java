import java.util.Objects;
import java.util.Stack;

public class LinkedList implements List {
	
	private Node head;
	private Node rear;
	public boolean isEmpty(){
		return true;
	}
	
	public void add(Object o){
		if(isEmpty())
		addFirst(o);
		else
			addLast(o);
	}
	public void add(int index , Object o){
		if(index<0 || o ==null){
			throw new IllegalArgumentException("不合法");
		} else if (index == 0 && head == null){
			addFirst(o);
		}else if(index>0 && head == null){
			throw new IllegalArgumentException("不合法");
		}else{
			Node srcNode = (Node) this.get(index);
			Node newNode = new Node();
			newNode.data = o;
			newNode.next = srcNode.next;
			srcNode.next.previous = newNode;
			srcNode.next = newNode;
			newNode.previous = srcNode;
		}
	}
	public Object get(int index){
		Node newNode = new Node();
		for(int i = 0;i<index;i++){
			newNode = newNode.next;
		}
		return newNode;
	}
	public Object remove(int index){
		Node srcNode = (Node)this.get(index);
		srcNode.previous.next = srcNode.next;
		srcNode.next.previous = srcNode.previous;
		return srcNode.data;
	}
	
	public int size(){
		Node newNode = new Node();
		newNode = head;
		int size = 0;
		while (newNode != rear){
			newNode = newNode.next;
			size++;
		}
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data = o;
		if(isEmpty()){
			rear = newNode;
		}else{
			head.previous = newNode;
		}
		newNode.next = head;
		head = newNode;
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		if(isEmpty()){
			head = newNode;
		} else {
			rear.next = newNode;
		}
		newNode.previous = rear;
		rear = newNode;
	}
	public Object removeFirst(){
		if(isEmpty())
		return null;
		Node temp = head;
		head = head.next;
		if (head != null){
			head.previous = null;
		}else{
			rear = null;
		}
		return temp.data;
	}
	public Object removeLast(){
		if(isEmpty())
		return null;
		Node temp = rear;
		rear = rear.previous;
		if(rear != null){
			rear.next = null;
		} else {
			head = null;
		}
		return  temp.data;
	}
	public Iterator iterator(){
		Iterator ite = new Iterator() {
			private Node temp = head;
			@Override
			public boolean hasNext() {
				return temp != null;
			}

			@Override
			public Object next() {
				Object data = temp.data;
				temp = temp.next;
				return data;
			}
		};
		return ite;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		Node previous;
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		Node p1,p2 = null;
		p1 = head;
		while (head.next != null){
			p2 = head.next;
			head.next = p2.next;
			p2.next = p1;
			p1 = p2;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int size = this.size();
		Node newNode = new Node();
		newNode = head;
		if(size%2 == 0){
			int length = size/2;
			for(int i = 0;i<length;i++){
				newNode = newNode.next;
			}
			head.next = newNode.next;
			head = newNode;
		}else{
			int length = (size+1)/2;
			for(int i = 0;i<length;i++){
				newNode = newNode.next;
			}
			head.next = newNode.next;
			head = newNode;
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		Node newNode = (Node) this.get(i-1);
		Node remNode = (Node) this.get(i);
		for(int j=0;j<length-1;j++){
			remNode = remNode.next;
		}
		newNode.next = remNode;
		remNode.previous = newNode;
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
		int[] listB = new int[list.size()-1];
		int[] listA = new int[list.size()-1];
		int size = list.size();
		for(int i=0;i<size;i++){
			Node newNode = (Node) this.get(i);
			listB[i] = (int) newNode.data;
		}
		for(int i=0;i<size;i++){
			Node newNode = (Node) this.get(listB[i]);
			listA[i] = (int) newNode.data;
		}
		return listA;
	}
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		int[] listB = new int[list.size()-1];
		int[] listA = new int[this.size()-1];
		int sizeB = list.size();
		int sizeA = listA.length;
		for(int i=0;i<sizeB;i++){
			Node newNode = (Node) this.get(i);
			listB[i] = (int) newNode.data;
		}
	    for(int i=0;i<sizeA;i++){
			Node newNode = (Node) this.get(i);
			listA[i] = (int) newNode.data;
		}
		for(int i = 0;i<sizeB ;i++){
		for(int j = 0;j<sizeA ;j++){
			if(listB[i] == listA [j]){
				this.remove(j);
			}else if(listB[i] < listA [j])
				break;
		}

		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		int size = this.size();
		Node newNode1 = new Node();
		Node newNode2 = new Node();
		for (int i = 0;i<size;i++){
			newNode1 = (Node) this.get(i);
			newNode2 = (Node) this.get(i+1);
			if(newNode1.data == newNode2.data){
				this.remove(i);
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
		Node newNode = new Node();
		int size = this.size();
		for (int i = 0;i<size;i++){
			newNode = (Node) this.get(i);
			if((int)newNode.data > min && (int)newNode.data <max){
				this.remove(i);
			}else if ((int)newNode.data > min && (int)newNode.data > max){
				break;
			}
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList listA = this;
		LinkedList listB = list;
		LinkedList listC = new LinkedList();
		if(listA == null){
			return list;
		}
		if(listB == null){
			return this;
		}
		Node p1 = listA.head;
		Node p2 = listB.head;
		Node p3 = listC.head;
		while(p1 != null && p2 !=null) {
			if ((int) p1.data <= (int) p2.data) {
				p3.next = p1;
				p1 = p1.next;
			} else {
				p3.next = p2;
				p2 = p2.next;
			}
			p3 = p3.next;
		}
		if(p1 == null){
			p3.next = p2;
		}
		if(p2 == null){
			p3.next = p1;
		}
		return listC;
	}
}
