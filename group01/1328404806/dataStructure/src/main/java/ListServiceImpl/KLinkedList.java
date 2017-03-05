package ListServiceImpl;

import java.util.Iterator;

import ListService.KILinkedList;

public class KLinkedList<T> implements Iterable<T>, KILinkedList<T> {

	// 记录链表的长度
	private int mSize = 0;
	// private int mActionCount = 0;
	// 开始和结束节点
	private Node<T> mBeginNode, mLastNode;

	public KLinkedList() {
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * 初始化一个只有开始节点和结束节点的空链表
	 */
	private void init() {
		// 将首位节点链接起来
		mBeginNode = new Node<T>(null, null, null);
		mLastNode = new Node<T>(null, mBeginNode, null);
		mBeginNode.nextNode = mLastNode;
		mSize = 0;
		// mActionCount++;
	}

	public int size() {
		return mSize;
	}

	public boolean isEmpty() {
		return mSize == 0 ? true : false;
	}

	/**
	 * 在链表的pos位置之前放置t_node这个节点
	 * 
	 * @param t_node
	 *            需要放置的节点
	 * @param pos
	 *            放置节点在pos之前
	 */
	private void add(Node<T> newNode, int pos) {
		// 抛出不合法的位置
		if (pos < 0 || pos > mSize) {
			throw new IndexOutOfBoundsException();
		}

		// 链接新节点
		newNode.nextNode = getNode(pos);
		getNode(pos - 1).nextNode = newNode;
		getNode(pos).preNode = newNode;
		// mActionCount++;
		mSize++;

	}

	/**
	 * t 供外部调用，直接在链表的末尾添加，即在mLastNode节点之前
	 * 
	 * @param
	 */
	public void add(T t) {
		add(new Node<T>(t, null, null), mSize);
	}

	/**
	 * 往链表pos位置之前添加数据t
	 * 
	 * @param t
	 *            添加的数据
	 * @param pos
	 *            添加在pos位置之前
	 */
	public void add(T t, int pos) {
		add(new Node<T>(t, null, null), pos);
	}

	/**
	 * 
	 * @param pos
	 *            链表中的某个位置
	 * @return 翻去pos位置的节点 （此处的pos的范围是［－1，mSize］,此方法是私有方法，外部访问不了，只共此类中呢个访问）
	 */
	private Node<T> getNode(int pos) {
		Node<T> node;
		int currentPos;
		if (pos == -1) {
			// -1的位置是开始节点
			return mBeginNode;
		} else if (pos == mSize) {
			// mSize的位置是结束的节点
			return mLastNode;
		}
		// 因为这是双向节点，所以判断一下能提高搜索效率
		if (pos < mSize / 2) {
			currentPos = 0;
			node = mBeginNode.nextNode;
			while (currentPos < pos) {
				node = node.nextNode;
				currentPos++;
			}
		} else {
			node = mLastNode.preNode;
			currentPos = mSize - 1;
			while (currentPos > pos) {
				node = node.preNode;
				currentPos--;
			}
		}
		return node;
	}

	public T get(int pos) {
		return getNode(pos).t;
	}

	public void set(T t, int pos) {
		if (pos < 0 || pos >= mSize) {
			throw new IndexOutOfBoundsException();
		}
		getNode(pos).t = t;
	}

	/**
	 * 删除特定位置的节点
	 * 
	 * @param t_node
	 *            需要删除节点的位置
	 * @return
	 */
	private T remove(Node<T> t_node) {

		t_node.preNode.nextNode = t_node.nextNode;
		t_node.nextNode.preNode = t_node.preNode;
		// 最好在此处给其设置为空，不要其链接到其他节点，因为已经被销毁，不再持有其他的节点的引用
		t_node.nextNode = null;
		t_node.preNode = null;
		mSize--;
		// mActionCount++;
		return t_node.t;
	}

	public T remove(int pos) {
		if (pos < 0 || pos >= mSize) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> tempNode = getNode(pos);
		remove(tempNode);
		return tempNode.t;
	}

	public Iterator<T> iterator() {

		return new MyLinkedListIterator<T>();
	}

	private class MyLinkedListIterator<T> implements Iterator<T> {

		private int currentPos = 0;

		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (currentPos < mSize) {
				return true;
			}
			return false;
		}

		public T next() {
			// TODO Auto-generated method stub
			return (T) getNode(currentPos++).t;
		}

		public void remove() {
			// TODO Auto-generated method stub
			KLinkedList.this.remove(getNode(--currentPos));
			;
		}

	}

	// 静态内部类，定义的节点，双向链表，需要一个指向前面一项的引用域和一个指向后面一项的引用域，方便查找
	private static class Node<T> {
		public T t;
		public Node<T> preNode;
		public Node<T> nextNode;

		public Node(T t, Node<T> preNode, Node<T> nextNode) {
			this.preNode = preNode;
			this.nextNode = nextNode;
			this.t = t;
		}
	}

}
