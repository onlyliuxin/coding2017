package com.coding.basic;



public class LinkedList implements List {
	
	private Node head;
    private int thesize;


    public void add(Object o){
        if (head == null) {
            head = new Node(o,null);
        /*    head.data = o;
            head.next = null;*/
            thesize++;
        } else {
            addLast(o);
        }
	}
	public void add(int index , Object o){
        if (index > thesize) {
            throw new IndexOutOfBoundsException();
        } else if (index == thesize) {
            addLast(o);
        } else if(index<thesize){
            Node x = head;
            int i=0;
            do {
                x = x.next;
                i++;
            } while (i == index);
            Node old = x;
            Node newindex = new Node(o,null);

            thesize++;
        }
		
	}
	public Object get(int index){
        if (index >= thesize) {
            throw new IndexOutOfBoundsException();
        } else if(index==0){
            return head.data;
        } else
        {
            Node x = head;
            for (int j = 1; j <thesize ; j++) {
                x = x.next;
                if (j == index) {
                    break;

                }

            }
            return x.data;
        }

	}
	public Object remove(int index){
        Node x = head;
        for (int i = 1; i <index ; i++) {
            x = x.next;
        }
        Node fus = x.next.next;
        Node fu = x.next;
        x.next = fus;
        Object s = fu.data;
        fu = null;
        thesize--;
        return s;
	}
	
	public int size(){
		return thesize;
	}
	
	public void addFirst(Object o){
        Node oldfirst = head;
        head = new Node(o,oldfirst);
        thesize++;
	}
	public void addLast(Object o){
        if (head == null) {
            add(o);
        }
        if (thesize == 1) {
            Node s = new Node(o,null);
            head.next = s;
            thesize++;
        } else {
            Node x = head;

            for (int i = 1; i <size() ; i++) {
                x = x.next;
            }

            Node oldlast=x;
            x = new Node(o,null);
            oldlast.next = x;
            thesize++;
        }
		
	}
	public Object removeFirst(){

        head = head.next;
        thesize--;
        return head.data;
	}
	public Object removeLast(){
        Node x = head;

        for (int i = 1; i <thesize; i++) {
            x = x.next;
        }
        Node fu = new Node(x.data,null);
        fu.data = x.data;
        x = null;
        thesize--;
        return fu.data;
	}
	public Iterator iterator(){
        return new Iterator() {
            Node x = head;
            int index=0;
            @Override
            public boolean hasNext() {
                if(index<thesize){
                    index++;
                    return true;
                }else {
                    return false;
                }

            }

            @Override
            public Object next() {
                if (index == 1) {
                    return head.data;
                }else {
                    x = x.next;
                    return x.data;
                }

            }
        };
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		//Node pre;
		public Node(Object data,Node n) {
            this.data = data;
            //this.pre = p;
            this.next = n;
        }

    }
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
        int cusize = thesize;
        for (int i = 1; i <cusize ; i++) {
            this.addFirst(get(i));
        }
        Node node=head;
        for (int i = thesize/2; i <=thesize ; i++) {
            remove(i);
        }
        //node.next = null;
    }
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
        Node node=head;
        for (int i = 1; i <thesize/2 ; i++) {
            node = node.next;
        }
        node.next = null;

    }
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
        Node node = head;
        if (length > thesize) {
            node = null;
            node.next = null;
        } else {
            if (i == 0) {
                for (int j = 1; j <=length ; j++) {
                    node = node.next;
                }
                head = node;
            } else if (i != 0 || (i + length + 1) < thesize) {
                for (int j = 0; j <thesize ; j++) {
                    node = node.next;
                    if (j == i) {

                        head = node;
                    }
                }

            }else {
                for (int j = 0; j <i+1 ; j++) {
                    node = node.next;
                }
                head = node;
            }
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
	public  int[] getElements(LinkedList list){
        int[] res = new int[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            res[i] = (int) this.get(i);
        }
        return res;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}
