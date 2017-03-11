//代码参考自《数据结构与算法分析》
public class LinkedList implements List {
	
    private Node header;
    
    private Node trailer;
    
    private int theSize;
    
    public LinkedList() {
        header = new Node(null, null, null);
        trailer = new Node(null, header, null);
        header.succ = trailer;
        theSize = 0;
    }
    
    public void add(Object o) {
        add(size(), o);
    }

    public void add(int index , Object o) {
        if (index < 0 || theSize < index) throw new IndexOutOfBoundsException();

        Node p = header;
        while (0 < index--) p = p.succ();
        p.insertAsSucc(o);
        theSize++;        
    }

    public Object get(int index) {
        if (index < 0 || theSize <= index) throw new IndexOutOfBoundsException();

        Node p = header.succ();
        while (0 < index--) p = p.succ();
        return p.data();
    }

    public Object remove(int index) {
        if (0 < index || theSize <= index) throw new IndexOutOfBoundsException();

        Node p = header.succ();
        while (0 < index--) p = p.succ();
        Object removed = p.data();
        p.pred().succ = p.succ();
        p.succ().pred = p.pred();
        theSize--;
        return removed;
    }

    public int size() {
        return theSize;
    }

    public void addFirst(Object o) {
        header.insertAsSucc(o);
    }

    public void addLast(Object o) {
        trailer.insertAsPred(o);
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(theSize - 1);
    }

    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator {
        private Node current = header.succ();

        public boolean hasNext() {
            return current != trailer;
        }

        public Object next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Object item = current.data();
            current = current.succ();
            return item;
        }
    }

    private static class Node {
        //pred、succ代表属性；pred()、succ()代表Node节点
        private Object data;
        private Node pred;
        private Node succ;

        public Node(Object d, Node p, Node s) {
            data = d;
            pred = p;
            succ = s;
        }

        public Object data() { 
            return data; 
        }

        public Node succ() { 
            return succ; 
        }

        public Node pred() { 
            return pred; 
        }

        //插入前驱节点，返回插入的新节点
        public Node insertAsPred(Object data) {
            Node p = new Node(data, pred, this);
            pred = pred().succ = p;
            return p;
        }

        //插入后继节点，返回插入的新节点
        public Node insertAsSucc(Object data) {
            Node p = new Node(data, this, succ);
            succ = succ().pred = p;
            return p;
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
