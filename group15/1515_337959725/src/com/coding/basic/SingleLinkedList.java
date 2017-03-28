package com.coding.basic;

public class SingleLinkedList<T>{

    //��̬�ڲ����ʾ������Ľڵ�
    private static class Node<T>{
        public T date;  //������
        Node<T> next;   //��ָ��

        public Node(T d){
            date = d;
            next = null;
        }
    }

    private int theSize;
    private Node<T> head;

    public SingleLinkedList()
    {
        clear();
    }
    //���
    public void clear(){
        theSize = 0;
        head = null;
    }

    //��С
    public int size(){
        return theSize;
    }

    //��ӽ��
    public void add(T x){
        Node<T> newNode = new Node<T>(x);
        if(head == null){
            head = newNode ;
        }else {
            Node<T> pNode = head;
            while(pNode.next!=null){
                   pNode = pNode.next;
            }
            pNode.next = newNode;
        }
        theSize++;
    }

    //����ڵ�
    public void add(int index ,T x){
        checkRange(index);
        Node<T> pNode = getNode(index);
        Node<T> newNode = new Node<T>(x);
        newNode.next = pNode.next;
        pNode.next = newNode;
        theSize++;
    }

    //����ͷ�ڵ�
    public void addFirst(T x){
        Node<T> newNode = new Node<T>(x);
        newNode.next = head;
        head =newNode;
        theSize++;
    }

  //���index�Ƿ�Խ��
    public void checkRange(int index){
        if (index<0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size();
    }

    //��ȡ�ڵ�����
    public T get(int index){
        Node<T> pNode = getNode(index);
        return pNode.date;
    }

    //��ȡ�ڵ�
    public Node<T> getNode(int index){
        checkRange(index);
        Node<T> pNode = head;
        for(int i=0;i<index;i++){
            pNode = pNode.next;
        }
        return pNode;
    }

    //ɾ��β�ڵ�
    public T removeTail(){
        T t = remove(size()-1);
        theSize--;
        return t;
    }

    //ɾ���ڵ�
    public T remove(int index){
        checkRange(index);

        Node<T> pNode = getNode(index);
        T t=pNode.date;
        Node<T> temp = head;
        for(int i=0;i<index-1;i++){
            temp = temp.next;
        }
        temp.next = pNode.next;
        pNode.next = null;
        theSize--;
        return t;
    }
    
    /**
	 * �Ѹ���������
	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
	 */
	public void reverse(){	
		T t;
		for(int i=0;i<theSize/2;i++){
			t = get(i);
			Node<T> node1 = getNode(i);
			Node<T> node2 = getNode(theSize-1-i);
			node1.date = node2.date;
			node2.date=t;
		}
	}
	
	/**
	 * ɾ��һ���������ǰ�벿��
	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10

	 */
	public  void removeFirstHalf(){
		int count=theSize/2;
		for(int i=0;i<count;i++){
			remove(0);
		}
	}
	
	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for(int j=0;j<length;j++){
			remove(i);
		}
	}
	/**
	 * �ٶ���ǰ�����list���������������е�����
	 * �ӵ�ǰ������ȡ����Щlist��ָ����Ԫ��
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(SingleLinkedList list){
		int a;
		int length=0;
		int[] b=new int[theSize];
		for(int i=0;i<list.theSize;i++){
			 a= (int)list.get(i);
			 T t = get(a);
			 b[length]=(Integer) t;
			 length++;
		}
		return b;
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * �ӵ�ǰ��������ɾ����list�г��ֵ�Ԫ�� 

	 * @param list
	 */
	
	public  void subtract(SingleLinkedList list){
		int a;
		int b;
		for(int i=0;i<list.theSize;i++){
			 a= (int)list.get(i);
			 for(int j=0;j<theSize;j++){
				b=(Integer)get(j);
				if(a==b){
					remove(j);
					break;
				}
			 }
		}
	}
	
	/**
	 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
	 */
	public  void removeDuplicateValues(){
		int a;
		int b;
		for(int i=0;i<theSize;i++){
			a=(Integer)get(i);
			for(int j=i+1;j<theSize;j++){
				b=(Integer)get(j);
				if(a==b){
					remove(j);
				}
			}
		}
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ��дһ��Ч���㷨��ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		int a;
		for(int i=0;i<theSize;i++){
			a=(Integer)get(i);
			if(a>min||a<max){
				remove(i);
			}
		}
		
	}
	
	/**
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
	 * @param list
	 */
	public  SingleLinkedList intersection( SingleLinkedList list){
		int a;
		int b;
		SingleLinkedList<Integer> c=new SingleLinkedList<Integer>();
		for(int i=0;i<list.theSize;i++){
			 a= (int)list.get(i);
			 for(int j=0;j<theSize;j++){
				b=(Integer)get(j);
				if(a==b){
					c.add(a);
				}
			 }
		}
		return c;
	}
}

