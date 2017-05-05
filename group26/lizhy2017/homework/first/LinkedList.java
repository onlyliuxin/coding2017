package first;

/**
 * LinkedList和ArrayList
 * 共同点：有序、可顺序访问、可随机访问、动态扩容
 * 抽象成List接口
 */
public class LinkedList implements List {
    private int mSize;
    private Node mHeadNode;//头节点
    private Node mFootNode;//头节点

    /**
     * 每次add Object 相当于在尾部新加一个节点
     * 每次都需要new Node出来，然后将Node加在尾部
     */
    public void add(Object o) {
        if (null == mHeadNode) {
            addFirst(o);
        } else {
            //顺着链表找到最后一个加上我们要add 的Object，并且记录上一个节点
            addLast(o);
        }
    }

    public void add(int index, Object o) {
        checkRange(index);
        if (null == mHeadNode) {
            addFirst(o);
        } else if (index == (mSize - 1)) {
            addLast(o);
        } else {
            Node preNode = getNode(index - 1);//前一个位置的Node
            Node newNode = new Node(o);
            newNode.next = preNode.next;//新节点 保存之前的 next
            preNode.next = newNode;
            mSize++;
        }
    }

    /**
     * 把index节点中的data取出来
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        checkRange(index);
        return getNode(index);
    }

    public Object remove(int index) {
        checkRange(index);
        if (index == 0) return removeFirst();
        else {
            Node preNode = getNode(index - 1);
            preNode.next = preNode.next.next;//移除了index节点对象
            mSize--;
            return preNode.next.data;
        }
    }

    public int size() {
        return mSize;
    }

    /**
     * 链表的头加入一个元素，head指针需要前移
     *
     * @param o
     */
    public void addFirst(Object o) {
        Node nextNode = mHeadNode;
        mHeadNode = new Node(o);
        if (nextNode != null) {
            mHeadNode.next = nextNode;
        } else {
            mFootNode = mHeadNode;
        }
        mSize++;
    }

    public void addLast(Object o) {
        Node nextNode = new Node(o);
        if (mFootNode != null) {
            mFootNode.next = nextNode;
        } else {
            mHeadNode = nextNode;
        }
        mFootNode = nextNode;
        mSize++;
    }

    public Object removeFirst() {
        if (mSize > 0) {
            Node node = mHeadNode;
            mHeadNode = mHeadNode.next;
            mSize--;
            return node.data;
        }else {
            System.out.println("链表为空！");
            return null;
        }
    }

    public Object removeLast() {
        if(mSize>0){
            Node preNode = getNode(mSize-2);//找到last节点的上一个节点
            Object node = preNode.next.data;
            preNode.next = null;
            mFootNode = preNode;
            mSize--;
            return node;
        }else{
            System.out.println("链表为空！");
            return null;
        }

    }

//    public Iterator iterator() {
//        return null;
//    }
//
//    /**
//     * 把该链表逆置
//     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
//     */
//    public void reverse() {
//
//    }
//
//    /**
//     * 删除一个单链表的前半部分
//     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
//     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
//     */
//    public void removeFirstHalf() {
//
//    }
//
//    /**
//     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
//     *
//     * @param i
//     * @param length
//     */
//    public void remove(int i, int length) {
//
//    }
//
//    /**
//     * 假定当前链表和listB均包含已升序排列的整数
//     * 从当前链表中取出那些listB所指定的元素
//     * 例如当前链表 = 11->101->201->301->401->501->601->701
//     * listB = 1->3->4->6
//     * 返回的结果应该是[101,301,401,601]
//     *
//     * @param list
//     */
//    public int[] getElements(LinkedList list) {
//        return null;
//    }
//
//    /**
//     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
//     * 从当前链表中中删除在listB中出现的元素
//     *
//     * @param list
//     */
//
//    public void subtract(LinkedList list) {
//
//    }
//
//    /**
//     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
//     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
//     */
//    public void removeDuplicateValues() {
//
//    }
//
//    /**
//     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
//     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
//     *
//     * @param min
//     * @param max
//     */
//    public void removeRange(int min, int max) {
//
//    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        return null;
    }

    private void checkRange(int index) {
        if (index > mSize || index < 0) {
            throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + mSize);
        }
    }

    private Node getNode(int index) {
        if (index == 0) return mHeadNode;
        if (index == mSize - 1) return mFootNode;

        Node nextNode = mFootNode;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                break;
            }
            nextNode = nextNode.next;
        }
        return nextNode;
    }

    /**
     * 实现节点的内部类。
     * ps:静态内部类可以避免 非静态内部类将会各自关联每一个LinkedList实例
     * （静态内部类是class级别一一对应的，非静态内部类是实例级别对应的
     */
    private static class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
        }
    }
}
