package  com.dataStructure.LRU;

/**
 * Created by wang on 2017/3/27.
 */
public class LRUPageFrame {

    private static class Node {

        Node prev;
        Node next;
        int pageNum;

        Node(int pageNum) {

            this.pageNum = pageNum;
        }
    }

    private int capacity;
    private int size;

    private Node first;// 链表头
    private Node last;// 链表尾


    public LRUPageFrame(int capacity) {

        this.capacity = capacity;

    }



    public boolean contains(int pageNum){
        Node cur = first;
        while(cur != null){
            if(pageNum == cur.pageNum){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean isFull(){
        if(size==capacity){
            return true;
        }
        return false;
    }

    private void isLegal(int pageNum){
        if(pageNum<0){
            throw new IllegalArgumentException();
        }
    }

    /**
     * 获取缓存中对象
     *
     * @param
     * @return
     */
    public void access(int pageNum) {
        isLegal(pageNum);
        if(isFull()){
            if(contains(pageNum)){
                advance(pageNum);
            }else{
                removeLast();
                addFirst(pageNum);
            }
        }else{
            if(contains(pageNum)){
                advance(pageNum);
            }else{
                add(pageNum);
            }
        }

    }


    /**
     * 填充页面
     * @param pageNum
     */

    private void add(int pageNum) {
        if(isEmpty()){
            Node node = new Node(pageNum);
            first = node;
            last = node;
            size++;
        }else{
            Node node = new Node(pageNum);
            Node oldfirst = first;
            node.next = oldfirst;
            oldfirst.prev = node;
            first = node;
            size++;
        }
    }

    private boolean isEmpty() {
        return size==0;
    }

    private void advance(int pageNum) {


        if(pageNum==first.pageNum){

        }else{
            if(pageNum==last.pageNum){
                removeLast();
                addFirst(pageNum);
            }else{
                Node x = findNode(pageNum);
                exchNum(x);
            }
        }

    }

    private void exchNum(Node x) {
        int temp = first.pageNum;
        first.pageNum = x.pageNum;
        x.pageNum = temp;
    }

    private Node findNode(int pageNum) {
        Node cur = first;
        while(cur != null){
            if(pageNum == cur.pageNum){
                break;
            }
            cur = cur.next;
        }
        return cur;
    }

    private void addFirst(int pageNum) {
        Node oldFirst = first;
        Node newFirst = new Node(pageNum);

        oldFirst.prev = newFirst;
        newFirst.next = oldFirst;

        first = newFirst;
        size++;
    }

    private void removeLast() {
        last = last.prev;
        last.next = null;
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