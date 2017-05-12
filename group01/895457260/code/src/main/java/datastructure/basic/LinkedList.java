package datastructure.basic;

import datastructure.exception.EmptyListException;

import java.util.Objects;

public class LinkedList implements List {
	
	private Node head;
	private int size;

	public LinkedList() {
	    head = new Node();
    }

    @Override
	public void add(Object o) {
        addLast(o);
	}

	@Override
	public void add(int index , Object o) {
	    Node pre = findNode(index - 1);
	    Node node = new Node();
	    node.data = o;
	    addNode(node, pre);
	}

	@Override
	public Object get(int index) {
	    checkIndex(index);
		return findNode(index).data;
	}

	@Override
	public Object remove(int index) {
	    checkIndex(index);
	    Node pre = findNode(index - 1);
	    Node removed = pre.next;
	    removeNode(removed, pre);
		return removed.data;
	}

	@Override
	public int size() {
		return size;
	}
	
	public void addFirst(Object o) {
		Node node = new Node();
		node.data = o;
		addNode(node, head);
	}

	public void addLast(Object o) {
        Node node = new Node();
        node.data = o;
        Node pre = findNode(size() - 1);
        addNode(node, pre);
	}

	public Object removeFirst() {
		if (size() == 0) {
			throw new EmptyListException();
		}
	    Node removed = head.next;
		removeNode(head.next, head);
		return removed.data;
	}

	public Object removeLast() {
		if (size() == 0) {
			throw new EmptyListException();
		}
	    return remove(size() - 1);
	}

	@Override
	public Iterator iterator() {
		return new Iterator() {
			Node node = head;
			@Override
			public boolean hasNext() {
				return node.next != null;
			}

			@Override
			public Object next() {
				node = node.next;
				return node.data;
			}
		};
	}

	private static class Node{
		Object data;
		Node next;
	}

	private Node findNode(int index) {
	    if (index == -1) {
	        return head;
        } else {
	        checkIndex(index);
        }
        Node node = head.next;
        for (int i = 0; i < index; ++i) {
	        node = node.next;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException(indexOutOfBoundMessage(index));
        }
    }

    private String indexOutOfBoundMessage(int index) {
        return "index: " + index + ", size: " + size();
    }

    private void addNode(Node node, Node pre) {
	    node.next = pre.next;
	    pre.next = node;
	    size++;
    }

    private void removeNode(Node node, Node pre) {
	    pre.next = node.next;
	    node.next = null;
	    size--;
    }

	//清空整条链，返回链中结点数量
	private int clearLink(Node start) {
		int count = 0;
		while (start != null) {
			Node node = start;
			start = start.next;
			node.data = null;
			node.next = null;
			count++;
		}
		return count;
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		Stack stack = new Stack();
		for (Node node = head.next; node != null; node = node.next) {
			stack.push(node);
		}
		head.next = (Node) stack.peek();
		while (stack.size() > 1) {
			Node top = (Node) stack.pop();
			top.next = (Node) stack.peek();
		}
		((Node) stack.peek()).next = null;
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public void removeFirstHalf(){
		int half = size() / 2;
		if (half > 1) {
			Node first = head.next;

			Node preHalf = findNode(half - 1);
			head.next = preHalf.next;
			preHalf.next = null;
			size -= clearLink(first);
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		if (length <= 0) {
			return;
		}
		Node preI = findNode(i - 1);
		Node removeTo = findNode(i + length - 1);
		Node removeFrom = preI.next;
		preI.next = removeTo.next;
		removeTo.next = null;
		size -= clearLink(removeFrom);
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * @param list
	 */
	public Object[] getElements(LinkedList list){
		Object[] result = new Object[list.size()];
		int count = 0;

		Node nodeI = head.next;
		Node nodeJ = list.head.next;
		for (int i = 0; nodeI != null && nodeJ != null; ++i) {
			int compare = i - (int) nodeJ.data;
			if (compare == 0) {
				result[count] = nodeI.data;
				count++;
				nodeI = nodeI.next;
				nodeJ = nodeJ.next;
			} else if (compare < 0) {
				nodeI = nodeI.next;
			} else {
				nodeJ = nodeJ.next;
				i--;
			}
		}
		Object[] trueResult = new Object[count];
		System.arraycopy(result, 0, trueResult, 0, count);
		return trueResult;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素

	 * @param list
	 */

	public void subtract(LinkedList list){
		Node pre = head;
		Node node = list.head.next;
		while (pre.next != null && node != null) {
			if ((int) pre.next.data < (int) node.data) {
				pre = pre.next;
			} else if ((int) pre.next.data > (int) node.data) {
				node = node.next;
			} else {
				removeNode(pre.next, pre);
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		Node node = head;
		while (node.next != null) {
			if (Objects.equals(node.data, node.next.data)) {
				removeNode(node.next, node);
			} else {
				node = node.next;
			}
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max){
		Node node = head;
		while (node.next != null) {
			int value = (int) node.next.data;
			if (value <= min) { // 还未进入范围
				node = node.next;
			} else if (value >= max) { // 超出范围，停止遍历
				break;
			} else { // 在范围内，删除之
				removeNode(node.next, node);
			}
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList intersection(LinkedList list){
		LinkedList result = new LinkedList();
		Node inThis = head.next;
		Node node = list.head.next;
		int[] temp = new int[Math.min(size(), list.size())];
		int count = 0;
		while (inThis != null && node != null) {
			int compare = (int) inThis.data - (int) node.data;
			if (compare < 0) {
				inThis = inThis.next;
			} else if (compare > 0) {
				node = node.next;
			} else {
				temp[count] = (int) node.data;
				count++;
				inThis = inThis.next;
				node = node.next;
			}
		}
		for (int i = count - 1; i >= 0; --i) {
			result.addFirst(temp[i]);
		}
		return result;
	}
}
