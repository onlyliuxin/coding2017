package basic;


public class LinkedList implements List{

	private Node head;
	
	private int size;
	
	@Override
	public void add(Object o) {
		addLast(o);
	}
	
	@Override
	public void add(int index, Object o) {
		checkIndex(index);
		Node current = findByIndex(index);
		Node newNode = new Node(o, current);
		if(index == 0){
			head = newNode;
		}else{
			Node perv = findByIndex(index-1);
			perv.next = newNode;
		}
		size++;
	}
	
	@Override
	public Object get(int index) {
		checkIndex(index + 1);
		return findByIndex(index).data;
	}
	
	@Override
	public Object remove(int index) {
		Node remove = null;
		checkIndex(index + 1);
		Node next = findByIndex(index+1);
		if(index == 0){
			remove = head;
			if(next == null){
				head = null;
			}else {
				head = next;
			}
		}else{
			Node perv = findByIndex(index-1);
			remove = perv.next;
			perv.next = next;
		}
		size--;
		return remove.data;
	}
	
	@Override
	public int size() {
		return size;
	} 
	
	public void addFirst(Object o){
		head = new Node(o, head);
		size++;
	}
	
	public void addLast(Object o){
		Node nextNode = new Node(o, null);
		if(head == null){
			head = nextNode;
		}else{
			Node lastNode = findByIndex(size-1);
			lastNode.next = nextNode;
		}
		size++;
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size-1);
	}
	
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator{

		int current = 0;
		
		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public Object next() {
			Node findByIndex = findByIndex(current);
			if(current >= size){
				throw new IndexOutOfBoundsException("current:"+current+"  size:"+size);
			}
			current++;
			return findByIndex.data;
		}
		
	}
	
	private static class Node{
		Object data;
		Node next;
		
		Node(Object data, Node next){
			this.data = data;
			this.next = next;
		}
	}
	
	private Node findByIndex(int index){
		Node lastNode = head;
		for (int i = 0; i < index; i++) {
			lastNode = lastNode.next;
		}
		return lastNode;
	}
	
	private void checkIndex(int index){
		if(index > size || index < 0){
			throw new IndexOutOfBoundsException("current:"+index+"  size:"+size);
		}
	}
	/** 
	 * 把该链表逆置 
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3 
	 */ 
	public void reverse(){
		Node current = this.head;
		for (int i = 0; i < size-1; i++) {
			removeFirst();
			add(size - i, current.data);
			current = current.next;
		}
	}
	 
	/** 
	 * 删除一个单链表的前半部分 
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8 
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10 
	 *
	 */
	public void removeFirstHalf(){
		int total = size/2;
		for (int i = 0; i < total; i++) {
			removeFirst();
		}
	}
	 
	/** 
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始 
	 * @param i 
	 * @param length 
	 */
	public void remove(int i, int length){
		if(i < 0 || length < 0){
			throw new IllegalArgumentException("参数异常");
		}
		if(i + length > size){
			throw new IndexOutOfBoundsException();
		}
		for (int j = 0; j < length; j++) {
			remove(i);
		}
	}
	/** 
	 * 假定当前链表和listB均包含已升序排列的整数 
	 * 从当前链表中取出那些listB所指定的元素 
	 * 例如当前链表 = 11->101->201->301->401->501->601->701 
	 * listB = 1->3->4->6 
	 * 返回的结果应该是[101,301,401,601]   
	 * @param list 
	 */
	public int[] getElements(LinkedList list){
		if(list == null || list.head == null){
			return new int[0];
		}
		int result[] = new int [list.size];
		Iterator iterator = list.iterator();
		int index = 0;
		while(iterator.hasNext()){
			int next = (int)iterator.next();
			if(next < size){
				result[index] = (int)this.get(next);
			}
			index++;
		}
		return result;
	}
	
	/** 
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 
	 * 从当前链表中中删除在listB中出现的元素  
	 * @param list 
	 */ 
	
	public void subtract(LinkedList list){
		if(list == null || list.head == null){
			return;
		}
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			Object next = iterator.next();
			Iterator iteratorInner = this.iterator();
			int index = 0;
			while(iteratorInner.hasNext()){
				if(next.equals(iteratorInner.next())){
					this.remove(index);
					break;
				}
				index++;
			}
		}
	}
	/** 
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同） 
	 */
	public void removeDuplicateValues(){
		for (int i = 0; i < size; i++) {
			if(findByIndex(i).data == findByIndex(i+1).data){
				remove(i);
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
		if(min >= max){
			throw new IllegalArgumentException("参数异常");
		}
		int minIndex = 0;
		int maxIndex = 0;
		boolean flag = true;
		for (int i = 0; i < size; i++) {
			int current = (int)get(i);
			if(flag && current > min){
				minIndex = i;
				flag = false;
			}else if(current >= max){
				maxIndex = i;
				break;
			}else{
				maxIndex = size;
			}
		}
		remove(minIndex, maxIndex - minIndex);
	}
		
	/** 
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同） 
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列 
	 * @param list 
	 */
	public LinkedList intersection( LinkedList list){
		if(list == null || list.head == null){
			return null;
		}
		LinkedList linkedList = new LinkedList();
		Iterator iterator = this.iterator();
		while(iterator.hasNext()){
			Object next = iterator.next();
			Iterator iterator2 = list.iterator();
			while(iterator2.hasNext()){
				if(next.equals(iterator2.next())){
					linkedList.add(next);
				}
			}
		}
		return linkedList;
	}
}
