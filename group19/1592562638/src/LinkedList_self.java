/*范例名称：
 * 原文件名称：
 * 要点：
 * 1. 实现基本的数据结构类：LinkedList

 */
public class LinkedList_self<T> implements KIList<T> {
	// 定义一个内部类Node Node 实例代表链表的节点
	public class Node {
		public T data;// 链表节点的数据
		public Node next;// 指向下一个节点的引用

		// 构建无参构造器
		public Node() {
		};

		// 初始化全部属性构造器
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	// 链表头节点
	public Node header;
	// 链表尾节点
	public Node tail;
	// 链表的节点数
	public int size = 0;

	// 创建空链表
	public LinkedList_self() {
		header = null;
		tail = null;
	}

	// 创建包含一个指定元素的链表
	public LinkedList_self(T element) {
		header = new Node(element, tail);
		tail = header;// 只有一个节点，header tail 都指向该节点
		size++;
	}

	/**
	 * 插入一个元素到ArrayList尾部 
	 * @param item
	 */
	@Override
	public void add(T item) {
		// TODO Auto-generated method stub
		// 空链表
		if (header == null) {
			header = new Node(item, tail);
			tail = header;
		} else {
			// 创建新节点
			Node newNode = new Node(item, null);
			// 尾节点指向新节点
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	/**
	 * 插入一个元素到指定位置，从插入位置及其后面的元素往后移动一个位置
	 * 
	 * @param index
	 *            要插入的位置
	 * @param item
	 */
	@Override
	public void add(int index, T item) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// 如果是空链表
		if (header == null) {
			add(item);
		} else {
			// 当index为0时在表头插入
			if (index == 0) {
				addAtHeader(item);
			}
			else{
				//获取前置节点
				Node prev=getNodeByIndex(index-1);
				//prev指向新节点，新节点的next指向prev的next
				prev.next=new Node(item,prev.next);
				size++;
			}
		}
	}

	/**
	 * 移除指定位置的元素，后面的元素向前移动一位
	 * 
	 * @param index
	 */
	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node del = null;
		// 如果删除的是头结点
		if (index == 0) {
			del = header;
			header = header.next;
		} else {
			// 获取删除节点的前置节点
			Node prev = getNodeByIndex(index - 1);
			del = prev.next;
			prev.next = del.next;
			del.next = null;
		}
		size--;
		return del.data;
	}

	/**
	 * 更新指定位置的元素
	 * 
	 * @param index
	 * @param item
	 */
	@Override
	public void set(int index, T item) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// 从header节点开始
		Node current = header;
		for (int i = 0; i < size && current != null; i++) {
			if (i == index) {
				current.data = item;
			}
			current = current.next;
		}
	}

	/**
	 * 获取指定位置的元素
	 * 
	 * @param index
	 * @return
	 */
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return getNodeByIndex(index).data;
	}

	/**
	 * 返回链表的长度
	 * 
	 * @param item
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	// 根据索引index获取指定位置的节点
	private Node getNodeByIndex(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// 从header节点开始
		Node current = header;
		for (int i = 0; i < size && current != null; i++) {
			if (i == index) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	// 采用头插法为链表添加新节点
	private void addAtHeader(T item) {
		// TODO Auto-generated method stub
		//创建新节点，并让新节点的next 指向header
		//以新节点为header
		Node newNode=new Node(item,header);
		header=newNode;
		//如果插入前是空链表
		if(tail==null){
			tail=header;
		}
		size++;
	}
}
