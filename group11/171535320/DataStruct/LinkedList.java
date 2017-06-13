package DataStruct;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		if(head == null) {
			head = new Node();
			head.data = o;
		} else {
			Node temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node();
			temp.next.data = o;
		}
	}

	public void add(int index , Object o){
		if(index > size()) {
			return ;
		}
		if(index == 0) {
			Node newNode = new Node();
			newNode.data = o;
			newNode.next = head;
			head = newNode;
		} else {
			int temp = 0;
			Node newNode = new Node();
			newNode.data = o;
			Node tempNode = head;
			while(temp != index - 1) {
				tempNode = tempNode.next;
				++temp;
			}
			Node tempNode2 = tempNode.next;
			tempNode.next = newNode;
			newNode.next = tempNode2;
		}
	}

	public Object get(int index){
		if(index > size() || size() == 0) {
			return null;
		}
		Node temp = head;
		for(int i = 0; i < index; ++i) {
			temp = temp.next;
		}

		return temp.data;
	}


	public Object remove(int index){
		if(size() == 0) {
			return null;
		}
		if(size() == 1) {
			Object obj = head.data;
			head = null;
			return obj;
		}
		if(index == 0) {
			Node temp = head;
			head = head.next;
			temp.next = null;
		} else if(index > size()) {
			return null;
		} else {
			int t = 0;
			Node temp = head;
			while(t != index - 1) {
				temp = temp.next;
				++t;
			}
			Node result = temp.next;
			temp.next = temp.next.next;
			return result.data;
		}
		return null;
	}
	
	public int size(){
		int len = 0;
		Node temp = head;
		while(temp != null) {
			temp = temp.next;
			++len;
		}
		return len;
	}
	
	public void addFirst(Object o){
		Node temp = new Node();
		temp.data = o;
		temp.next = head;
		head = temp;
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;

		if(size() == 0) {
			head = newNode;
		}
		Node temp = head;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = newNode;
	}
	public Object removeFirst(){
		if(size() == 0) {
			return null;
		}
		Node temp = head;
		head = head.next;
		return temp.data;
	}
	public Object removeLast(){
		if(size() == 0) {
			return null;
		}
		if(size() == 1) {
			Node temp = head.next;
			head = head.next;
			return temp.data;
		}
		Node temp1 = head;
		Node temp2 = head.next;
		while(temp2.next != null) {
			temp1 = temp1.next;
			temp2 = temp2.next;
		}
		temp1.next = null;
		return temp2.data;
	}
	public Iterator iterator(){
		return null;
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
		Node first = head;
		Node last = head.next;
		while(last != null) {
			Node temp = last;
			last = last.next;
			temp.next = first;
			first = temp;
		}
		head = first;
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		Node temp = head;
		int len = 1;
		while(temp.next != null) {
			temp = temp.next;
			len++;
		}

		int num = len / 2;

		for(int i = 0; i < num; ++i) {
			head = head.next;
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i == 0) {
			head = null;
		}
		Node temp = head;
		for(int n = 1; n < i; ++n) {
			temp = temp.next;
		}
		Node last = temp;
		for(int n = 0; n < length; ++n) {
			if(last != null) {
				last = last.next;
			}
		}
		temp.next = last;

	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * @param list
	 */
	public int[] getElements(LinkedList list){
			ArrayList<Integer> tempList = new ArrayList<Integer>();
			Node t = head;
			int i = 0;
			while(t != null) {
				tempList.add((Integer) t.data);
				i++;
			}
			int k = 0;
			int[] result = new int[i];
			for(int j = 0; j < list.size(); ++j) {
				result[k++] = (Integer)tempList.get((Integer) list.get(j));
			}
		return result;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素
	 * @param list
	 */

	public  void subtract(java.util.LinkedList<Integer> list){
		Node temp = head;
		Node temp2 = head;
		int i = 0;

		while(i < list.size() || temp != null) {
			if(i == list.size() || temp == null) {
				break;
			}
			if(list.get(i) > (Integer)temp.data) {
				temp2 = temp;
				temp = temp.next;
			} else if(list.get(i) < (Integer)temp.data) {
				i++;
			} else {
				temp2.next = temp.next;
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node temp1 = head;
		Node temp2 = head.next;
		while(temp2 != null) {
			if(temp1.data == temp2.data) {
				temp2 = temp2.next;
			} else {
				temp1.next = temp2;
				temp1 = temp2;
				temp2 = temp2.next;
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
		Node first = head;
		Node first2 = first;
		Node last = head.next;
		Node last2 = last;
		while(last != null) {
			if((Integer)last.data < max && (Integer)first.data < min) {
				first2 = first;
				first = first.next;
				last2 = last;
				last = last.next;
			} else if((Integer)last.data > max && (Integer)first.data > min) {
				break;
			} else if((Integer)last.data < max && (Integer)first.data > min) {
				last2 = last;
				last = last.next;
			} else {
				first2 = first;
				first = first.next;
			}
		}
		first2.next = last;
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList result = new LinkedList();
		Node temp = head;
		Node temp2 = list.head;
		while(temp != null && temp2 != null) {
			if((Integer)temp.data == (Integer)temp2.data) {
				result.add(temp.data);
				temp = temp.next;
				temp2 = temp2.next;
			} else if((Integer)temp.data > (Integer)temp2.data) {
				temp2 = temp2.next;
			} else {
				temp = temp.next;
			}
		}

		return result;
	}
}
