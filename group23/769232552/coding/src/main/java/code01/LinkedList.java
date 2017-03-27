package code01;


public class LinkedList implements List {

	private Node head;
    private Node tail; //指向链表最后一个元素的引用

    private int size; //总长度

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * 新增顺序添加一个元素
     * @param o
     */
    public void add(Object o){
        Node node = new Node();
        node.data = o;
        node.next = null;
        //空链表
        if(head == null){
            this.head = node;
            this.tail = node;
        }else { //非空链表，要先找到链表尾部，再加入新解答
            this.tail.next = node;
            this.tail = node;
        }
        this.size ++;
	}

    /**
     * 指定索引处添加node
     */
    public void add(int index, Object o) {
        assert(index >= 0);
        Node node = new Node();
        node.data = o;
        node.next = null;

        if(index == 0){
            //添加在头部
            node.next = head;
            head = node;
        }else if(index >= this.size){
            //添加在尾部
            this.tail.next = node;
        }else {
            //添加在中间
            Node cursor = this.head;
            for (int i = 0; i < index - 1; i++) {
                cursor = cursor.next;
            }
            node.next = cursor.next;
            cursor.next = node;
        }
        this.size ++;
    }

	public Object get(int index){
		assert(index < this.size);
        Node cursor = this.head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        return  cursor.data;
	}

	public Object remove(int index){
        assert(index >= 0 && index < this.size);
        Object result = null;
        //删除的是链表尾部的元素
        if(index == this.size - 1){
            Node cursor = this.head;
            for (int i = 0; i < index - 1; i++) {
                cursor = cursor.next;
            }
            result = cursor.next.data;
            tail = cursor;
            cursor.next = null;
        }else if(index == 0){
            //删除的是头部元素
            result = head.data;
            head = head.next;
        }else {
            //删除的是链表中间的元素
            Node cursor = this.head;
            for (int i = 0; i < index - 1; i++) {
                cursor = cursor.next;
            }
            result = cursor.next.data;
            cursor.next = cursor.next.next;
        }
        this.size --;
        return result;
	}

	public int size(){
		return this.size;
	}

	public void addFirst(Object o){
        Node node = new Node();
        node.data = o;
        node.next = null;
        if(this.head == null){
            this.head = node;
            this.tail = node;
        }else {
            node.next = head;
            this.head = node;
        }
        this.size ++;
	}
	public void addLast(Object o){
        Node node = new Node();
        node.data = o;
        node.next = null;
        if(this.head == null){
            this.head = node;
            this.tail = node;
        }else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size ++;
    }

	public Object removeFirst(){
		Object first = null;
        if(this.head != null){
            first = this.head.data;
            head = head.next;
            this.size --;
        }
        return first;
	}

	public Object removeLast(){
		Object last = null;
        if(this.tail != null){
            if(this.head != this.tail){
                Node cursor;
                for (cursor = head;cursor.next!=tail;cursor=cursor.next);
                last = this.tail.data;
                this.tail = cursor;
                this.tail.next = null;
            }else {
                last = this.tail.data;
                this.head = null;
                this.tail = null;
            }
            this.size --;
        }
        return last;
	}
	public Iterator iterator(){
		return null;
	}

    /**
     * 节点类
     */
    private static class Node{
		Object data;
		Node next;

	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
        if(this.head == null || this.head == this.tail){
            return;
        }

        Node pre_cursor = null;
        Node cursor = this.head;
        Node after_cursor = cursor.next;

        while(cursor != null){
            cursor.next = pre_cursor;
            pre_cursor = cursor;
            cursor = after_cursor;
            if(after_cursor != null){
                after_cursor = after_cursor.next;
            }
        }

        Node tmpNode = this.head;
        this.head = this.tail;
        this.tail = tmpNode;
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public void removeFirstHalf(){
        if(this.head == null || this.head.next == null){
            return;
        }
        if(this.head.next.next == null){
            this.head = this.head.next;
        }

        Node stepOne = this.head;
        Node stepTwo = this.head;

        while (stepTwo.next != null){
            stepOne = stepOne.next;
            stepTwo = stepTwo.next.next;
        }
        this.head = stepOne;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
        Node current = head;
        Node firstHalf = null;
        for (int k = 0; k < i; k ++){
            if(current == null){
                return;
            }
            firstHalf = current;  //记录待删除节点的前一个节点
            current = current.next;
        }

        //移动length长度
        for (int j = 0; j < length; j++) {
            if(current == null){
                return;
            }
            current = current.next;
        }

        if(i == 0){
            head = current;
        }else {
            firstHalf.next = current;
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
	public static int[] getElements(LinkedList list){
		return null;
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
	public void removeDuplicateValues(){
        if(this.head == null){
            return;
        }
        Node current = this.head;
        Node current_next = this.head;
        while (current_next != null){
            current_next = current_next.next; //如果放到下个while循环后面写，就需要判断一次current_next是不是null了
            while(current_next != null && current_next.data.equals(current.data)){
                //删除重复节点
                current.next = current_next.next;
                current_next = current_next.next;
            }
            current = current_next;
        }
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
        //怎么才能高效呢
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		return null;
	}

    /**
     * 遍历列表
     */
    public void printList(){
        System.out.println();
        for (Node cursor = this.head;cursor!=null;cursor=cursor.next){
            System.out.print(cursor.data+" ");
        }
    }
}
