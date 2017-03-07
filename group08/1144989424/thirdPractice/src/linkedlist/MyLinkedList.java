package linkedlist;

/**
 * 实现LinkedList基本功能
 * @author Wayss
 * 2017-02-23
 */

public class MyLinkedList implements MyList {
    
    private Node head;
    private int size = 0;
    
    public void add(Object o){
        Node n = new Node(o);
        head.next = n;
        size++;
    }
    public void add(int index , Object o){
        //1.index校验
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("插入的下标越界了:"+"插入的下标为："+index+"集合大小为："+size);
        }
        //2. 查找index位置的前一个节点
        //tempNode为当前链表的第一个节点
        Node tempNode = head.next;
        for(int i = 0; i < index - 1 ; i++){
            tempNode = tempNode.next;
        }
        Node behindNode = tempNode.next;
        Node insertNode = new Node(o);
        tempNode.next = insertNode;
        insertNode.next = behindNode;
        size++;
    }
    public Object get(int index){
      //1.index校验
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("插入的下标越界了:"+"插入的下标为："+index+"集合大小为："+size);
        }
        //2. 查找当前节点
        Node tempNode = head.next;
        for(int i = 0; i < index; i++){
            tempNode = tempNode.next;
        }
        return tempNode.data;
    }
    public Object remove(int index){
        //1.index校验
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("插入的下标越界了:"+"插入的下标为："+index+"集合大小为："+size);
        }
        //2. 查找当前节点的上一个节点
        Node tempNode = head.next;
        for(int i = 0; i < index - 1; i++){
            tempNode = tempNode.next;
        }
        Node deleteNode = tempNode.next;
        Node behideNode = tempNode.next.next;
        tempNode.next = behideNode;
        size--;
        return deleteNode.data;
    }
    
    public int size(){
        return size;
    }
    
    public void addFirst(Object o){
        Node insertNode = new Node(o);
        insertNode.next = head.next;
        head.next = insertNode;
        size++;
    }
    public void addLast(Object o){
        Node insertNode = new Node(o);
        Node tempNode = head.next;
        for(int i = 0; i < size; i++){
            tempNode = tempNode.next;
        }
        tempNode.next = insertNode;
        size++;
    }
    public Object removeFirst(){
        Node firstNode = head.next;
        head = firstNode.next;
        size--;
        return firstNode;
    }
    public Object removeLast(){
        Node tempNode = head.next;
        //1.移除需要找到最后一个点的前一个点
        for(int i = 0; i < size - 1; i++){
            tempNode = tempNode.next;
        }
        Node deleteNode = tempNode.next;
        tempNode.next = null;
        size--;
        return deleteNode;
    }
    
    public MyIterator iterator(){
        return new MyLinkedListIterator(this);
    }
    
    private class MyLinkedListIterator implements MyIterator{
        private MyLinkedList list = null;
        private int index = 0;
        
        private MyLinkedListIterator(MyLinkedList list){
            this.list = list;
        }
        
        @Override
        public boolean hasNext(){
            if(index < size){
                return true;
            }
            return false;
        }
        
        @Override
        public Object next(){
            return list.get(index++);
        }
    }
    
    private static class Node{
        Object data;
        Node next;
        public Node(Object data){
            this.data = data;
        }
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
     * 假定当前链表和listB均包含已升序排列的整数
     * 从当前链表中取出那些listB所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]  
     * @param list
     */
    public  int[] getElements(MyLinkedList list){
        return null;
    }
    
    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素 
     * @param list
     */
    
    public  void subtract(MyLinkedList list){
        
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
    public  MyLinkedList intersection( MyLinkedList list){
        return null;
    }
    
}
