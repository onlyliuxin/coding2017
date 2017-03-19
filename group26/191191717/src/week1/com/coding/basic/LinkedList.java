package week1.com.coding.basic;

public class LinkedList implements List
{
    
    private Node head;// 首节点
    
    private Node last;// 未节点
    
    private int size;// 节点长度
    
    public LinkedList()
    {
        this.head = this.last;
    }
    
    /* 新增节点就是将当前最后一个节点的引用指定新增的节点 */
    public void add(Object o)
    {
        addLast(o);
    }
    
    /** 在指定位置增加节点相当于要把前一个节点的引用指向新节点，然后把新节点的引用指向后一个节点 **/
    public void add(int index, Object o)
    {
        if (size == index)
        {
            add(o);
        }
        else
        {
            Node prevNode = getNode(index).prev;
            if (prevNode == null)// 如果没有前驱节点,相当于在首节点前插入
            {
                addFirst(o);
            }
            else
            {
                addBefore(o, getNode(index));
            }
        }
        size++;
    }
    
    // 在节点前增加,前后驱指针要维护
    public void addBefore(Object o, Node curr)
    {
        Node prevNode = curr.prev;// 前一个节点
        Node newNode = new Node(o);
        if (prevNode == null)
        {
            head = newNode;
        }
        else
        {
            prevNode.next = newNode;
        }
        curr.prev = newNode;
        newNode.next = curr;
    }
    
    public Node getNode(int index)
    {
        if (index < 0 || index > size - 1)
        {
            throw new RuntimeException("索引越界");
        }
        // 这里采用的是二分查找法则
        if (index < (size >> 1))
        {// 从前向后找
            Node n = head;
            for (int i = 0; i < index; i++)
                n = n.next;// 一直找到最后一个
            return n;
        }
        else // 从后向前找
        {
            Node n = last;
            for (int i = size; i > index + 1; i--)
                n = last.prev;
            return n;
        }
    }
    
    /* 打印节点 */
    public void printNode()
    {
        Node temp = head;
        while (temp != null)
        {
            System.out.printf("%s ", temp.data);
            temp = temp.next;
        }
    }
    
    public Object get(int index)
    {
        return getNode(index).data;
    }
    
    /*
     * 用前面的二分查找法会更好 public Node getNode(int index) { Node temp = head; int i = 0; while (temp != null) { if (i == index)
     * { return temp; } temp = temp.next; i++; } return temp; }
     */
    
    /* remove就相对于将一个节点删除，同时将前驱节点指向后置节点 */
    public Object remove(int index)
    {
        if (index == 0)
        {
            return removeFirst();
        }
        if (index == size)
        {
            return removeLast();
        }
        if (index > size)
        {
            throw new RuntimeException("删除一个不存在的索引上的元素");
        }
        /*
         * Node temp = head; int i = 0; while (temp != null) { if (i == index - 1) { Node curNode = temp.next;// 要删除的节点
         * temp.next = curNode.next;// 将前驱节点指向后置节点 return curNode.data; } temp = temp.next; i++; }
         */
        // 交换引用
        Node currNode = getNode(index);
        Node prevNode = currNode.prev;
        Node nextNode = currNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
        return currNode.data;
    }
    
    public int size()
    {
        return size;
    }
    
    public void addFirst(Object o)
    {
        Node newNode = new Node(o);
        Node firstTemp = head;
        if (head == null)
        {
            head = newNode;
        }
        else
        {
            firstTemp.prev = newNode;
        }
        head = newNode;
        newNode.next = firstTemp;
        size++;
    }
    
    // 这里在新增节点的时候一定要维护一个前驱指针和后驱指针
    public void addLast(Object o)
    {
        Node newNode = new Node(o);
        Node lastTemp = last;
        if (lastTemp == null)
        {
            head = newNode;
        }
        else
        {
            lastTemp.next = newNode;
        }
        last = newNode;
        newNode.prev = lastTemp;
        size++;
    }
    
    public Object removeFirst()
    {
        Object object = head.data;
        Node node = head.next;
        head = node;
        size--;
        return object;
    }
    
    public Object removeLast()
    {
        Object object = last.data;
        last = last.prev;// 将前一个节点设置为last
        last.next = null;// 并将引用设置为空
        size--;
        return object;
    }
    
    public Iterator iterator()
    {
        return new IteratorImple();
    }
    
    private class IteratorImple implements Iterator
    {
        
        private int cursor;// 游标索引值
        
        private Node curr = head;//遍历都是从head开始的
        
        @Override
        public boolean hasNext()
        {
            return cursor >= size ? false : true;
        }
        
        @Override
        public Object next()
        {
            Node temp=curr;
            curr = temp.next;
            cursor++;
            return temp.data;
        }
        
    }
    
    /**
     * 链表节点内部类
     * 
     * @author Administrator
     *
     */
    private static class Node
    {
        Object data;// 数据区
        
        Node next;// 指针域，下一个对象的引用
        
        Node prev;// 前指针
        
        public Node(Object data)
        {
            this.data = data;
        }
    }
    
    /**
     * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
     */
    public void reverse()
    {
        head = last;
        Node temp = last;
        System.out.println(head.data);
        while ((temp = temp.prev) != null)
        {
            head.next = temp.prev;// 之前的前一个节点，变成后一个节点
            head.prev = temp.next;// 之前的后一个节点前成前一个节点
            System.out.println(head.next.data + "  " + head.prev.data);
            // if ((temp=temp.prev)!= null)
            // {
            // System.out.println(temp.data);
            // }
        }
    }
    
    /**
     * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     * 
     */
    public void removeFirstHalf()
    {
        
    }
    
    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * 
     * @param i
     * @param length
     */
    public void remove(int i, int length)
    {
        
    }
    
    /**
     * 假定当前链表和listB均包含已升序排列的整数 从当前链表中取出那些listB所指定的元素 例如当前链表 = 11->101->201->301->401->501->601->701 listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     * 
     * @param list
     */
    public int[] getElements(LinkedList list)
    {
        return null;
    }
    
    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在listB中出现的元素
     * 
     * @param list
     */
    
    public void subtract(LinkedList list)
    {
        
    }
    
    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues()
    {
        
    }
    
    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * 
     * @param min
     * @param max
     */
    public void removeRange(int min, int max)
    {
        
    }
    
    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同） 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * 
     * @param list
     */
    public LinkedList intersection(LinkedList list)
    {
        return null;
    }
    
    public static void main(String[] args)
    {
        int[] num = {1, 2, 3, 4, 5, 6};
        for (int i = num.length; i > 0; i--)
        {
            System.out.println(num[i - 1]);
        }
    }
}
