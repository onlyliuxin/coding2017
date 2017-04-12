package com.coding.basic;

public class SingleLinkedList<T>{

    //静态内部类表示单链表的节点
    private static class Node<T>{
        public T date;  //数据域
        Node<T> next;   //后指针

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
    //清除
    public void clear(){
        theSize = 0;
        head = null;
    }

    //大小
    public int size(){
        return theSize;
    }

    //添加结点
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

    //插入节点
    public void add(int index ,T x){
        checkRange(index);
        Node<T> pNode = getNode(index);
        Node<T> newNode = new Node<T>(x);
        newNode.next = pNode.next;
        pNode.next = newNode;
        theSize++;
    }

    //加在头节点
    public void addFirst(T x){
        Node<T> newNode = new Node<T>(x);
        newNode.next = head;
        head =newNode;
        theSize++;
    }

  //检查index是否越界
    public void checkRange(int index){
        if (index<0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size();
    }

    //获取节点数据
    public T get(int index){
        Node<T> pNode = getNode(index);
        return pNode.date;
    }

    //获取节点
    public Node<T> getNode(int index){
        checkRange(index);
        Node<T> pNode = head;
        for(int i=0;i<index;i++){
            pNode = pNode.next;
        }
        return pNode;
    }

    //删除尾节点
    public T removeTail(){
        T t = remove(size()-1);
        theSize--;
        return t;
    }

    //删除节点
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
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
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
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int count=theSize/2;
		for(int i=0;i<count;i++){
			remove(0);
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for(int j=0;j<length;j++){
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
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

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
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
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
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
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
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
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

