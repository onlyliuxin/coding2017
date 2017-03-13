package datastructure;



public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
        if(this.head == null){
            Node tmp = new Node();
            tmp.data = o;
            tmp.next = null;
            this.head = tmp;
        }else {
            Node newNode = new Node();
            newNode.data = o;
            newNode.next = this.head;
            this.head = newNode;
        }
	}
	public void add(int index , Object o){
        Node tmp = this.head;
        Node newNode = new Node();
        newNode.data = o;
        int i;
		for(i=0; i<index-1&&(tmp.next!=null); i++){
            tmp = tmp.next;
        }
        if((i+1)==index){
            newNode.next = tmp.next;
            tmp.next = newNode;
        }else {
            System.out.println("out of range");
        }
	}
	public Object get(int index){
        Node tmp = this.head;
        int i;
        for(i=0; i<index&&(tmp.next!=null); i++){
            tmp = tmp.next;
        }
        if(i==index){
            return tmp.data;
        }
		return null;
	}
	public Object remove(int index){
        Node returnResult = null;
        Node tmp = this.head;
        int i;
        for(i=0; i<index-1&&(tmp.next!=null); i++){
            tmp = tmp.next;
        }
        if((i+1)==index&&(tmp.next!=null)){
            returnResult = tmp.next;
            tmp.next = tmp.next.next;
            return returnResult;
        }
		return null;
	}
	
	public int size(){
        if(this.head == null) {
            return 0;
        }
        int i = 1;
        Node tmp = this.head;
        while(tmp.next!=null){
            i++;
        }
		return i;
	}
	
	public void addFirst(Object o){
		
	}
	public void addLast(Object o){
		
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
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
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
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
