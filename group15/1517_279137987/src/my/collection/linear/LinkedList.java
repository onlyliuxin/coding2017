package my.collection.linear;

public class LinkedList implements MyList {
	
	private Node head;
	
	private int size = 0;

	public void add(Object obj) {
		add(this.size, obj);
	}

	public void add(int index, Object obj) {
		Node curNode = head;
		Node addNode = new Node(obj);
		if(index == 0){
			addNode.next = head;
			head = addNode;
		}else{
			for(int i=0; i<index-1; i++){
				curNode = curNode.next;
			}
			addNode.next = curNode.next;
			curNode.next = addNode;
		}
		size++;
	}

	public Object remove(int index) {
		Node curNode = head;
		Node remNode = new Node();
		if(index == 0){
			remNode = head;
			head = head.next;
		}else{
			for(int i=0; i<index-1; i++){
				curNode = curNode.next;
			}
			curNode.next = curNode.next.next;
		}
		size--;
		return remNode;
	}

	public int size() {
		return this.size;
	}
	
	public Object get(int index) {
		Node curNode = head;
		for(int i=0; i<index; i++){
			curNode = curNode.next;
		}
		return curNode.data;
	}
	
	public void addFirst(Object obj){
		add(0,obj);
	}
	
	public void addLast(Object obj){
		add(size,obj);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size-1);
	}
	
	public String toString(){
		Node curNode = head;
		String str = "\n" + "toString():";
		while(curNode != null){
			str += curNode.data + "\t";
			curNode = curNode.next;
		}
		return str;
	}
	
	private static class Node{
		Object data;
		Node next;
		
		private Node(){
		}
		
		private Node(Object obj){
			this.data = obj;
			this.next = null;
		}
	}
	
	/**
	 * 链表逆置，用指针
	 */
	/*public void reverseByNode(){
		Node tmp = head;
		if(tmp == null || tmp.next == null){
			return;
		}
		

	}*/
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		for(int i=size; i>0; i--){
			this.add(get(i-1));
		}
		this.removeFirstHalf();
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		int removeCount = this.size()/2;
		for(int i=0; i<removeCount; i++){
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for(int j=i; j<length+i; j++){
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
	public int[] getElements(LinkedList list){
		int[] result = new int[list.size()]; 
		for(int i=0; i<list.size(); i++){
			result[i] = (int) get(Integer.parseInt(list.get(i).toString()));
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	public  void subtract(LinkedList list){
		for(int i=0; i<list.size(); i++){
			for(int j=0; j<size(); j++){
				if(get(j).equals(list.get(i))){
					remove(j);
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		for(int i=0; i<size(); i++){
			for(int j=i+1; j<size(); j++){
				if(get(i).equals(get(j))){
					remove(j--);
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
		for(int i=0; i<size(); i++){
			if(Integer.parseInt(get(i).toString()) > min && Integer.parseInt(get(i).toString()) < max){
				remove(i);
				i--;
			}
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection(LinkedList list){
		LinkedList c = new LinkedList();
		for(int i=0; i<size(); i++){
			for(int j=0; j<list.size(); j++){
				if(get(i).equals(list.get(j))){
					c.add(get(i));
				}
			}
		}
		return c;
	}
}
