import java.net.IDN;

public class LinkedList implements List {

    private Node head;

    private Node firstNode;
    private Node lastNode;
    private int size = 0;


    public void add(Object o){
        linkLast(o);

    }
    public void add(int index , Object o){
        if(index > size())
        {
            throw new IndexOutOfBoundsException("index:"+ index + ",size:"+ size());
        }
        if(size == 0)
        {
            linkLast(o);
        }
        else
        {
            Node node = node(index);
            Node newNode = new Node(o,node.prev,node);
            node.setPrev(newNode);
        }
        ++size;
    }
    public Object get(int index){
        return node(index).getData();
    }
    public Object remove(int index){
        if(index >size())
        {
            throw new IndexOutOfBoundsException("index:"+ index + ",size:"+ size());
        }
        Node node = node(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        --size;
        return node;
    }

    public int size(){
        return size;
    }

    public void addFirst(Object o){
        linkLast(o);
    }
    public void addLast(Object o){
        if(lastNode == null)
        {
            lastNode = new Node(o,firstNode,null);
        }
        else
        {
            Node node = lastNode;
            node.next = new Node(o,node,null);
            lastNode = node.next;
        }
        ++size;
    }
    public Object removeFirst(){
        if(size > 0 )
        {
            Node node = firstNode;
            firstNode = node.next;
            --size;
            return node.getData();
        }
        return null;
    }
    public Object removeLast(){
        if(size > 0 )
        {
            Node node = lastNode;
            lastNode = node.prev;
            --size;
            return node.getData();
        }
        return null;
    }
    public Iterator iterator(){
        return null;
    }

    LinkedList.Node node(int index)
    {
        if(index < size())
        {
            Node node = firstNode;
            for(int i=0;i<size();i++)
            {
                node = node.next;
            }
            return node;
        }
        throw new IndexOutOfBoundsException("index:" + index + ",size:"+size());


    }

    void linkLast(Object o) {
        Node lastNode = this.lastNode;
        Node node = new Node(o,lastNode,null);
        this.lastNode = node;
        if(lastNode == null)
        {
            firstNode = node;
        }
        else {
            lastNode.next = node;
        }
        size++;
    }



    private static  class Node{
        Object data;
        Node prev;
        Node next;

        public Node(Object data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public  void reverse(){
        for(int i=0;i<size;i++)
        {


        }

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
    public  int[] getElements(LinkedList list){
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素
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