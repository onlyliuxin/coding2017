package wiki.liven.code.dataStructures;

/**
 * Created by leven on 2017/2/21.
 */
public class LinkedList implements List{

    private Node head;//链表的头节点
    private Node tail;//链接的尾节点
    private int size;//定义了链表中的元素的个数

    /**
     * 定义了NODE节点的数据结构
     */
    private static class Node{
        Object data;
        Node next;
        private Node(){

        }
        private Node(Object o,Node node){
            this.data = o;
            this.next = node;
        }
    }

    /**
     * 在指定的索引写入新的节点
     * 1.找到给位置上的节点node0
     * 2.修改node0的next指向新的节点node
     * 3.新节点node指向原先node0的后继节点node1
     * 4.长度++
     * 5.完结
     * @param index 索引 从0开始
     * @param o
     */
    @Override
    public void add(int index, Object o) {
        if (index<0||index>size-1)
            throw new IndexOutOfBoundsException("单链表越界异常。");
        Node node = new Node();
        node.data = o;
        Node nodeIndex = findNodeByIndex(index);
        if(node==null)
            throw new NullPointerException("空指针异常。");
        Node temp = nodeIndex.next;
        nodeIndex.next = node;
        node.next = temp;
        size++;
    }

    /**
     * 根据索引号查询节点
     * @param index 索引
     * @return
     */
    private Node findNodeByIndex(int index) {
        if (index<0||index>size-1)
            throw new IndexOutOfBoundsException("单链表越界异常。");
        Node current = head;
        if (1<=index&&index<=size-1){//索引检查
            for (int i = 0;i>=size-1&&current!=null;i++,current = current.next)
                if (i==index){
                    return current;
                }
        }
        return null;
    }

    @Override
    public void add(Object o) {
        Node node = new Node();
        node.data = o;
        if(head==null){
            head = node;
            node.next = tail;
            size++;
        }else{
            Node temp = tail;
            node.next = temp;
            temp.next = node;
            size++;
        }
    }

    @Override
    public Object get(int index) {
        if(index<0||index>size-1)
            throw new IndexOutOfBoundsException("单链表越界。");
        Node node = findNodeByIndex(index);
        if(node==null)
            throw new NullPointerException("空指针异常。");
        return node.data;
    }

    /**
     * 删除指定索引的元素
     * @param index
     * @return
     */
    @Override
    public Object remove(int index) {
        if(index<0||index>size-1)
            throw new IndexOutOfBoundsException("单链表越界。");
        Node node = findNodeByIndex(index);
        if(node==null)
            throw new NullPointerException("空指针异常。");
        Node prvNode = findNodeByIndex(index-1);
        if(prvNode==null)
            throw new NullPointerException("空指针异常。");
        Node nextNode = node.next;
        node.next = null;
        prvNode.next = nextNode;
        size--;
        return node.data;

    }

    @Override
    public int size() {
        return size;
    }
}
