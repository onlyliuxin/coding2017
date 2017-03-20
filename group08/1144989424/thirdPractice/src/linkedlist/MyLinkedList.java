package linkedlist;

import java.util.Comparator;

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
    public void set(int i, Object obj){
        remove(i);
        add(i,obj);
    }
    
    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public  void reverse(){
        //单链表的实现方法,遍历了一遍
        //如果是双向链表的话，逆置就简单多了
        int half = size/2;
        for(int i = 0; i < half; i ++){
            Object o1 = get(i);
            set(i,o1);
            Object o2 = get(size - 1 - i);
            set(size - 1 - i, o2);
        }
    }
    
    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public  void removeFirstHalf(){
        int half = size/2;
        Node tempNode = head.next;
        for(int i = 0; i < half; i++){
            tempNode = tempNode.next;
        }
        //通过移动head指针来实现移除前半部分
        head.next = tempNode;
    }
    
    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * @param i
     * @param length
     */
    public  void remove(int i, int length){
        if(i < 0 || i > size || i < length || length < 0 || length > size){
            return;
        }
        
        Node tempNode = head.next;
        for(int j = 0; j < i; j++){
            tempNode = tempNode.next;
        }
        Node n1 = tempNode;
        
        for(int j = i; j < length; j++){
            tempNode = tempNode.next;
        }
        Node n2 = tempNode;
        
        //移除n1到n2中间的元素
        n1.next = n2;
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
        if(list == null){
            return null;
        }
        
        Node tempNode = head.next;
        Node result = head;
        int[] res = new int[list.size()];
        
        for(int j = 0,i = 0; j < size; j++){
            if(j == (int)list.removeFirst()){
                result.next = tempNode;
                res[i++] = (int) tempNode.data;
            }else{
                tempNode = tempNode.next;
            }
        }
        
//        return result;
        return res;
    }
    
    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素 
     * @param list
     */
    
    public  void subtract(MyLinkedList list){
        
        //方法一
        //1.对list递增排序
        //2.遍历当前链表，同时和list做比较，向后移动list指针，注意是递增的
        
        //方法二
        //1.两层遍历嵌套
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < size; j++){
                if(list.get(i).equals(this.get(j))){
                    this.remove(j);
                }
            }
        }

    }
    
    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public  void removeDuplicateValues(){
        //1.遍历链表，比较后一个和当前的大小，
        //2.相等则，删除后一个，同时再比较原先删除的节点的后一个（可能需要while）
        for(int i = 0; i < size; i++){
            while(this.get(i).equals(this.get(i+1))){
                this.remove(i+1);
                //由于remove会把size的大小减一，所以，不会出现数组越界
                i++;
                //i++的目的是为了判断连续多个值相等的情况
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
        //1.找到第一个值大于min的节点，的前一个节点
        //2.找到第一个值小于max的节点
        //3.用第一步找出的节点指向第二步找出的节点的next
        Node minNode = head.next;
        Node maxNode = head.next;
        
        int first = 0;
        int last = 0;
        //循环停止的条件是，找到了第一个节点不小于min的点了。
        //所以，minNode也就没有再往后移动了。即，在不小于min的第一个点的前一个
        while((int)this.get(first++) < min){
            minNode = minNode.next;
        }
        
        while((int)this.get(last++) < max){
            maxNode = maxNode.next;
        }
        //maxNode往后再移动一个位置，表示的是第一个不小于max的点
        maxNode = maxNode.next;
        
        minNode.next = maxNode;
    }
    
    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * @param list
     */
    public  MyLinkedList intersection( MyLinkedList list){
        //1.假设当前链表节点个数是m,list中的节点个数是n
        //2.现在需要遍历m+n次，依次把值给链表C传递，同时，给C链表add的时候，注意比较大小
        MyLinkedList result = new MyLinkedList();
        int i = 0;
        int j = 0;
        while(i < size){
            while(j < list.size()){
                if((int)this.get(i) < (int)list.get(j)){
                    result.add(this.get(i));
                    i++;
                }else if((int)this.get(i) == (int)list.get(j)){
                    result.add(this.get(i));
                    i++;
                    j++;
                }else{
                    result.add(list.get(j));
                    j++;
                }
            }
        }
            
        return result;
    }
    
}
