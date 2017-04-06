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
    public void reverse(){		
        int times = theSize;
        int index = 0;
        while (0 < --times)
            add(index++, removeLast());
    }

    /**
    * 删除一个单链表的前半部分
    * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
    * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

    */
    public void removeFirstHalf(){
        int times = theSize / 2;
        while (0 < times--)
            removeFirst();
    }

    /**
    * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
    * @param i
    * @param length
    */
    public void remove(int i, int length){
        Node head = get(i).pred();   //删除(head, tail)之间元素  删除[i, i + length - 1]之间元素
        Node tail = get(i + length - 1).succ();

        head.succ = tail;
        tail.pred = head;
        theSize -= length;
    }
    /**
    * 假定当前链表和list均包含已升序排列的整数
    * 从当前链表中取出那些list所指定的元素
    * 例如当前链表 = 11->101->201->301->401->501->601->701
    * list = 1->3->4->6
    * 返回的结果应该是[101,301,401,601]  
    * @param list
    */
    public int[] getElements(LinkedList list){
        Iterator itSelf = iterator();
        Iterator itList = list.iterator();
        int[] ret = new int[list.size()];
        
        int i = 0;    //list中元素的值，代表当前列表中要取出元素的秩
            lastI = 0;//上一次取出元素的秩
            moveTimes = 0;
            value = itSelf.next();
            index = 0;//要返回的数组中元素的秩

        while (itList.hasNext()) {
            i = itList.next();
            if (theSize <= i) throw new IndexOutOfBoundsException();

            moveTimes = i - lastI;            
            while (0 < moveTimes--)
                value = itSelf.next();

            ret[index++] = value;
            lastI = i;
        }

        return ret;
    }

    /**
    * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
    * 从当前链表中中删除在list中出现的元素 

    * @param list
    */
    //返回与e相等的元素的秩；如果查找失败则返回-1
    private int find(Object e) {
        Iterator it = iterator();
        int i = -1;    //要返回的元素的秩
        Object value = null;
        
        while (it.hasNext()) {
            value = it.next();
            i++;
            if (value == e) return i;
            if (e < value) return -1;
        }

        return -1;
    }       

    public void subtract(LinkedList list){
        Iterator it = list.iterator();
        Object value = null;
        int i = -1;
        
        while (it.hasNext()) {
            value = it.next();
            i = find(value);
            
            //删去重复元素
            while (0 <= i) {
                remove(i);
                i = find(value);
            }
        }
    }

    /**
    * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
    * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
    */
    public  void removeDuplicateValues(){
        Node current = header.succ();
        Node next = current;
        int removedNum = 0;
        
        while ((next = next.succ()) != trailer) {
            if (current.data() == next.data()) {                
                removedNum++;
            } else {
                current.succ = next;
                next.pred = current;
                current = next;                
            }
        }
        theSize -= removedNum;
    }

    /**
    * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
    * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
    * @param min
    * @param max
    */
    //[low, min]U[max, end]


    public  void removeRange(int min, int max){
        //删去(i, j]
        int i = 0, j = 0;
        Iterator it = iterator();
        while (it.hasNext()) {
            Object value = it.next();
            if (value <= min) i++;
            if (value < max) j++;
            else break; //if(max <= value) break;
        }
        
        Node head = get(i);
        Node tail = get(j).succ();
        
        head.succ = tail;
        tail.pred = head;
        theSize -= (j - i);

    }

    /**
    * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
    * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
    * @param list
    */
    //交集：属于A且属于B的元素的合集
    public  LinkedList intersection(LinkedList list){
        LinkedList ret = new LinkedList();
        Iterator it = iterator();
        Iterator itList = list.iterator();
        Object value1 = null, value2 = null;
        
        if (it.hasNext() && itList.hasNext()) {
            value1 = it.next();
            value2 = itList.next();
        }
        
        while (value1 != null && value2 != null) {
            if (value1 < value2)      value1 = it.hasNext() ? it.next() : null;
            else if (value2 < value1) value2 = itList.hasNext() ? itList.next() : null;            
            else {
                ret.add(value1);
                value1 = it.hasNext() ? it.next() : null;
                value2 = itList.hasNext() ? itList.next() : null;
            }       
        }
        return ret;
    }
}
