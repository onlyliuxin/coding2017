package cmj.datastructure.list;

public class LinkedList implements List {

	private Node head;// 头结点
	private Node current;// 尾结点
	private int size;

	public LinkedList() {
		// 头指针和尾指针都指向头结点
		head = new Node(null, null);
		current = head;
	}

	/**
	 * 添加元素
	 * 
	 * @param o——用于添加的元素
	 */
	public void add(Object o) {
		Node node = new Node(o, null);// 新建一个结点
		current.next = node;// 尾指针指向它
		current = current.next;// 尾指针指向最后一个元素
		size++;
	}

	/**
	 * 在第index个位置插入元素
	 * 
	 * @param index——要插入的位置
	 * @param o——用于插入的对象
	 */
	public void add(int index, Object o) {
		Node node = new Node(o, null);// 新建一个结点
		if (index == 0) {
			addFirst(o);
		} else {
			Node curr = (Node) this.get(index - 1);// 获得前一个结点
			Node behind = (Node) this.get(index);// 获得后一个结点
			// 在这两个结点之间插入新的元素,修改引用指向
			curr.next = node;
			node.next = behind;
			size++;
		}

	}

	/**
	 * 随机访问index位置上的元素
	 * 
	 * @param index——元素的位置
	 * @return——对应的元素
	 */
	public Object get(int index) {
		RangeCheck(index);// 检查索引是否越界
		Node curr = head;// 得到头结点的引用
		// 从头结点开始遍历到第index个元素
		for (int i = 0; i <= index; i++)
			curr = curr.next;
		return curr;
	}

	/**
	 * 删除第index个位置上的元素
	 * 
	 * @param index
	 * @return
	 */
	public Object remove(int index) {
		RangeCheck(index);// 检查索引是否越界
		if (0 == index) {
			return removeFirst();
		} else {
			Node toRemove = (Node) this.get(index);// 获得要删除的结点
			Node preRemove = (Node) this.get(index - 1);// 获得前一个结点
			preRemove.next = toRemove.next;// 将前一个结点指向要删除的结点的下一个结点
			size--;
			return toRemove;
		}

	}

	/**
	 * 获取元素的大小
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 在链表头部增加元素
	 * 
	 * @param o——要增加的元素
	 */
	public void addFirst(Object o) {
		Node node = new Node(o, null);// 新建一个结点
		node.next = head.next;// 结点指向第一个元素
		head.next = node;// 将头结点指向它
		size++;
	}

	/**
	 * 在链表末尾添加元素
	 * 
	 * @param o——要添加的元素
	 */
	public void addLast(Object o) {
		Node node = new Node(o, null);// 新建一个结点
		current.next.next = node;// 尾结点的next指向新建的结点
		current.next = node;// 尾结点引用指向向新结点
		size++;
	}

	/**
	 * 移除第一个元素
	 * 
	 * @return——移除元素
	 */
	public Object removeFirst() {
		Node curr = head.next;// 新建一个引用记录第一个结点
		head.next = curr.next;// 头指针移动到原第二个元素上
		size--;
		return curr;
	}

	/**
	 * 移除最后一个元素
	 * 
	 * @return——移除元素
	 */
	public Object removeLast() {
		Node remove = current.next;
		Node pre = (Node) this.get(size - 2);// 获得倒数第二个结点
		current.next = pre;
		pre.next = null;
		size--;
		return remove;
	}

	/**
	 * 就是检查一下是不是超出数组界限了，超出了就抛出IndexOutBoundsException异常。
	 * 
	 * @param index要用于检查的索引
	 */
	private void RangeCheck(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + " 超出访问范围");
	}

	/**
	 * 重写toString()方法
	 */
	@Override
	public String toString() {
		String linkedlist = "[";
		Node visit = head;
		while (visit.next != null) {
			visit = visit.next;
			if (visit.next == null) {
				linkedlist += visit.data.toString() + "]";
			} else {
				linkedlist += visit.data.toString() + "--->";
			}
		}
		return linkedlist;
	}

	/**
	 * 结点内部类，主要要声明为static的
	 * 
	 * @author think
	 *
	 */
	private static class Node {
		Object data;
		Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}

	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		System.out.println(list);
		System.out.println(((Node) list.get(3)).data);
		list.add(4, "4");
		System.out.println(list);
		list.add(0, "0");
		System.out.println(list);
		list.addLast("last");
		System.out.println(list);
		System.out.println("Removed:" + ((Node) list.remove(1)).data);
		System.out.println(list);
		System.out.println("Removed:" + ((Node) list.removeFirst()).data);
		System.out.println(list);
		System.out.println("Removed:" + ((Node) list.removeLast()).data);
		System.out.println(list);
	}
}
