package collections;


/**
 * 
 * @author Mahone Wu
 * @date:2017-03-11
 * @description:链表实现
 * @version:1.0.0
 */
public class LinkedList implements List {

	//定义头节点
	private Node head;
	
	//最后一个节点
	private Node last;
	
	//链表大小
	private int size;

	/**
	 * 添加元素
	 */
	public void add(Object o) {
		addLast(o);
	}

	/**
	 * 指定位置添加元素
	 */
	public void add(int index, Object o) {
		if(!(index >= 0 && index <= size)){
			//超出索引范围
		}
		Node currentNode =null;
		
		
	}
	
	
	

	/**
	 * 获取指定元素
	 */
	public Object get(int index) {
		return null;
	}

	public Object remove(int index) {
		return null;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node fistNode = head;
		Node newNode = new Node(null, o, head);
		head = newNode;
		if(null == fistNode){
			last = newNode;
		}else{//将之前的头节点的前一个指向现在的头节点
			fistNode.prev = newNode;
		}
		size++;
	}

	/**
	 * 向尾添加元素
	 * @param o
	 */
	public void addLast(Object o) {
		Node lastNode = last;
		Node newNode = new Node(lastNode, o, null);
		last = newNode;//将新增的节点设置为最后一个节点
		if(null == lastNode){//如果之前最后一个节点为空，则代表这是第一次增加节点,进行头节点设置
			head = newNode;
		}else{
			lastNode.next = newNode;
		}
		size++;
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

	//参照LinkedList源码实现
	private static class Node {
		Object data;
		Node prev;
		Node next;
		
		Node(Node prev,Object object,Node next){
			this.prev = prev;
			this.data = object;
			this.next = next;
		}
	}

	/**
	 * 查找指定位置的Node节点
	 * @param index
	 * @return
	 */
	Node findNode(int index){	
		Node currentNode = head;	
		
		
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
	 * 假定当前链表和listB均包含已升序排列的整数 从当前链表中取出那些listB所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在listB中出现的元素
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
}
