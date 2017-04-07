package third.basic;

/**
 * ${}
 * Created by spark_lizhy on 2017/3/31.
 */
/**
 * 用双向链表实现 LRU 算法
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
    }

    private int capacity;


    private Node first;// 链表头
    private Node last;// 链表尾


    public LRUPageFrame(int capacity) {

        this.capacity = capacity;

    }

    /**
     * 获取缓存中对象
     *
     * @return
     */
    public void access(int pageNum) {


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
