public class LinkedList {
    /**
    * 把该链表逆置
    * 例如链表为 3->7->10 , 逆置后变为  10->7->3
    */
    public void reverse() {		
        int times = theSize - 1;  //第一个元素自然移动到最后，所以只需进行theSize - 1次操作        
        int index = 0;
        while (0 < times) {
            add(index++, removeLast());
            times--;
        }
    }

    /**
    * 删除一个单链表的前半部分
    * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
    * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

    */
    public void removeFirstHalf() {
        int times = theSize / 2;
        while (0 < times--)
            removeFirst();
    }

    /**
    * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
    * @param i
    * @param length
    */
    public void remove(int i, int length) {
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
    public int[] getElements(LinkedList list) {
        Iterator it = iterator();        
        int[] ret = new int[list.size()];
        int start = -1;
        int value = 0;
        int i = 0;   //数组ret的索引
        
        for (Integer num : list) {
            while (start < num && it.hasNext()) {
                value = it.next();
                start++;
            }
            ret[i++] = value;
        }
        return ret;
    }
    
    /**
    * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
    * 从当前链表中中删除在list中出现的元素 

    * @param list
    */ 
    public void subtract(LinkedList list) {
        Object current = null;
        for (Object e : list) {
            Iterator it = iterator();
            while (it.hasNext()) {
                current = it.next();
                if (current.compareTo(e) == 0)
                    it.remove();
                if (current.compareTo(e) > 0)
                    break;
            }
        }
    }

    /**
    * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
    * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
    */
    public  void removeDuplicateValues() {
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


    public  void removeRange(int min, int max) {
        //双链表删去(p, q)间的节点
        Node p = header;
        Node q = null;
        int removedNum = 0; //要删去节点的数目
        while ((p = p.succ()) != trailer && (p.data() <= min))
            ；
        p = p.prev();
        q = p;
        while ((q = q.succ()) != trailer && (q.data() < max))
            removedNum++;
        p.succ = q;
        q.prev = p;
        theSize -= removedNum;
        
        
        
        /*
        //删去(i, j]
        int i = 0, j = 0;
        Iterator it = iterator();
        while (it.hasNext()) {
            int value = it.next();
            if (value <= min) i++;
            if (value < max) j++;
            else break; //if(max <= value) break;
        }
        
        Node head = get(i);
        Node tail = get(j).succ();
        
        head.succ = tail;
        tail.pred = head;
        theSize -= (j - i);
        */

    }

    /**
    * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
    * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
    * @param list
    */
    //交集：属于A且属于B的元素的合集
    public  LinkedList intersection(LinkedList list) {
        LinkedList ret = new LinkedList();
        Iterator it = iterator();
        Iterator itList = list.iterator();
        Object value1 = null, value2 = null;
        
        if (it.hasNext() && itList.hasNext()) {
            value1 = it.next();
            value2 = itList.next();
        }
        //用null作为遍历完毕的标志
        //循环结束标志：其中一个LinkedList已经遍历完毕
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
