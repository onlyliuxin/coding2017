package assignment0326.lru;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {

    private static class Node {
        Node prev;
        Node next;
        int pageNum;
        Node() {
        }
        public Node(int pageNum) {
            this.pageNum = pageNum;
        }

    }

    private int capacity;


    private Node first;// 链表头
    private Node last;// 链表尾

    private int size;
    public LRUPageFrame(int capacity) {
        if(capacity<=0)
            throw new IllegalArgumentException("capacity:"+capacity+" <= 0");
        this.capacity = capacity;
        first=null;
        last=null;
        size=0;

    }

    /**
     * 获取缓存中对象
     *
     * @param pageNum
     * @return
     */
    public void access(int pageNum) {
        Node target=search(pageNum);
        if (target == null) {
            target=new Node(pageNum);
            linkFirst(target);
        }else{
            moveToFirst(target);
        }
        if(size>capacity){
            removeLast();
        }
    }

    private Node search(int pageNum) {
        Node f=first;
        while (f!=null){
            if (f.pageNum == pageNum) {
                return f;
            }
            f=f.next;
        }
        return null;
    }

    private void linkFirst(Node target) {
        if(first==null){
            first=last=target;
        }else {
            target.next = first;
            first.prev = target;
            first = target;
        }
        size++;
    }

    private void moveToFirst(Node target) {
        if(target==first){
            return;
        }

        Node prevOfTarget=target.prev;
        prevOfTarget.next=target.next;

        if(target==last) {
            last=prevOfTarget;
        }else {
            target.next.prev = prevOfTarget;
        }

        target.next=first;
        first.prev=target;
        first=target;

    }

    private void removeLast() {
        Node prevOfLast=last.prev;
        last.prev=null;
        last=prevOfLast;

        if(last==null){
            first=null;
        }else {
           last.next=null;
        }
        size--;
    }


    public String toString(){
        StringBuilder buffer = new StringBuilder();
        Node node = first;
        while(node != null){
            buffer.append(node.pageNum);

            node = node.next;
            if(node != null){
                buffer.append(",");
            }
        }
        return buffer.toString();
    }

}