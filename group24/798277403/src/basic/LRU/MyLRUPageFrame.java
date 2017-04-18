package basic.LRU;

/**
 * Created by zhouliang on 2017-04-04.
 */
public class MyLRUPageFrame {
    private static class Node {
        Node prev;
        Node next;
        int pageNum;

        Node() {
        }
    }

    private int capacity;
    private int currentSize;
    private Node first;// 链表头
    private Node last;// 链表尾

    public MyLRUPageFrame(int capacity) {
        this.currentSize = 0;
        this.capacity = capacity;
    }

    /**
     * 获取缓存中对象
     *
     * @param pageNum
     * @return
     */
    public void access(int pageNum) {
        //1、是否为空号
        //2、不为空，查找后面有没有出现pageNum，如果出现则把pageNum结点移动到head
        //3、如果没有出现pageNum,则判断栈是否满
        //4、如果栈没有满，则添加pageNum到head
        //5、否则删除最后一个再添加pageNum到head
        Node temp = find(pageNum);
        if (temp != null) {
            moveExistingNodeToHead(temp);
        } else {
            temp = new Node();
            temp.pageNum = pageNum;
            if (currentSize >= capacity) {
                removeLast();
            }
            addNewNodetoHead(temp);
        }

    }

    private void addNewNodetoHead(Node node) {
        if(isEmpty()){
            node.prev = null;
            node.next = null;
            first = node;
            last = node;
        }else{
            node.prev = null;
            node.next = first;
            first.prev = node;
            first = node;
        }
        currentSize++;
    }

    private Node find(int data) {
        if (isEmpty()) {
            return null;
        } else {
            Node temp = first;
            while (temp.next != null) {
                if (temp.pageNum == data) {
                    return temp;
                }
                temp = temp.next;
            }
            return null;
        }
    }

    /**
     * 删除链表尾部节点 表示 删除最少使用的缓存对象
     */
    private void removeLast() {
        Node temp = last.prev;
        temp.next = null;
        last.prev = null;
        last = temp;
        currentSize--;
    }

    /**
     * 移动到链表头，表示这个节点是最新使用过的
     */
    private void moveExistingNodeToHead(Node node) {
        if(node == first){
            return;
        }
        if(node != last){
            Node prev = node.prev;
            Node next  = node.next;
            prev.next = next;
            next.prev = prev;
        }else{
            Node prev = node.prev;
            prev.next = null;
            last.prev = null;
            last = prev;
        }
        node.next = first;
        node.prev = null;
        first.prev = node;
        first = node;
    }

    private boolean isEmpty() {
        return (first == null) && (last == null);
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Node node = first;
        while (node != null) {
            buffer.append(node.pageNum);
            node = node.next;
            if (node != null) {
                buffer.append(",");
            }
        }
        return buffer.toString();
    }
}
