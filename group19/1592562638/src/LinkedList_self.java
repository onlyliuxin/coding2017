/*�������ƣ�
 * ԭ�ļ����ƣ�
 * Ҫ�㣺
 * 1. ʵ�ֻ��������ݽṹ�ࣺLinkedList

 */
public class LinkedList_self<T> implements KIList<T> {
	// ����һ���ڲ���Node Node ʵ����������Ľڵ�
	public class Node {
		public T data;// ����ڵ������
		public Node next;// ָ����һ���ڵ������

		// �����޲ι�����
		public Node() {
		};

		// ��ʼ��ȫ�����Թ�����
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	// ����ͷ�ڵ�
	public Node header;
	// ����β�ڵ�
	public Node tail;
	// ����Ľڵ���
	public int size = 0;

	// ����������
	public LinkedList_self() {
		header = null;
		tail = null;
	}

	// ��������һ��ָ��Ԫ�ص�����
	public LinkedList_self(T element) {
		header = new Node(element, tail);
		tail = header;// ֻ��һ���ڵ㣬header tail ��ָ��ýڵ�
		size++;
	}

	/**
	 * ����һ��Ԫ�ص�ArrayListβ�� 
	 * @param item
	 */
	@Override
	public void add(T item) {
		// TODO Auto-generated method stub
		// ������
		if (header == null) {
			header = new Node(item, tail);
			tail = header;
		} else {
			// �����½ڵ�
			Node newNode = new Node(item, null);
			// β�ڵ�ָ���½ڵ�
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	/**
	 * ����һ��Ԫ�ص�ָ��λ�ã��Ӳ���λ�ü�������Ԫ�������ƶ�һ��λ��
	 * 
	 * @param index
	 *            Ҫ�����λ��
	 * @param item
	 */
	@Override
	public void add(int index, T item) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// ����ǿ�����
		if (header == null) {
			add(item);
		} else {
			// ��indexΪ0ʱ�ڱ�ͷ����
			if (index == 0) {
				addAtHeader(item);
			}
			else{
				//��ȡǰ�ýڵ�
				Node prev=getNodeByIndex(index-1);
				//prevָ���½ڵ㣬�½ڵ��nextָ��prev��next
				prev.next=new Node(item,prev.next);
				size++;
			}
		}
	}

	/**
	 * �Ƴ�ָ��λ�õ�Ԫ�أ������Ԫ����ǰ�ƶ�һλ
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
		// ���ɾ������ͷ���
		if (index == 0) {
			del = header;
			header = header.next;
		} else {
			// ��ȡɾ���ڵ��ǰ�ýڵ�
			Node prev = getNodeByIndex(index - 1);
			del = prev.next;
			prev.next = del.next;
			del.next = null;
		}
		size--;
		return del.data;
	}

	/**
	 * ����ָ��λ�õ�Ԫ��
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
		// ��header�ڵ㿪ʼ
		Node current = header;
		for (int i = 0; i < size && current != null; i++) {
			if (i == index) {
				current.data = item;
			}
			current = current.next;
		}
	}

	/**
	 * ��ȡָ��λ�õ�Ԫ��
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
	 * ��������ĳ���
	 * 
	 * @param item
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	// ��������index��ȡָ��λ�õĽڵ�
	private Node getNodeByIndex(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// ��header�ڵ㿪ʼ
		Node current = header;
		for (int i = 0; i < size && current != null; i++) {
			if (i == index) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	// ����ͷ�巨Ϊ��������½ڵ�
	private void addAtHeader(T item) {
		// TODO Auto-generated method stub
		//�����½ڵ㣬�����½ڵ��next ָ��header
		//���½ڵ�Ϊheader
		Node newNode=new Node(item,header);
		header=newNode;
		//�������ǰ�ǿ�����
		if(tail==null){
			tail=header;
		}
		size++;
	}
}
