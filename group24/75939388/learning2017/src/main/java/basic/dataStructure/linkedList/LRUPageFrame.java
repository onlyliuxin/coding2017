package basic.dataStructure.linkedList;

/**
 * @author : 温友朝
 */
public class LRUPageFrame {
    private static class Node {

        Node prev;
        Node next;
        int pageNum;

        Node() {}

        Node(Node prev, Node next, int pageNum){
            this.prev = prev;
            this.next = next;
            this.pageNum = pageNum;
        }
    }

    private int capacity;

    private int currentSize;
    private Node first;// 链表头
    private Node last;// 链表尾


    public LRUPageFrame(int capacity) {
        this.currentSize = 0;
        this.capacity = capacity;
    }

    /**
     * 获取缓存中对象
     *
     * @param pageNum
     */
    public void access(int pageNum){
        if(first == null){
            last = first = new Node(null, null, pageNum);
            this.currentSize++;
            return;
        }

        if(first.pageNum == pageNum){
            return;
        }

        if(currentSize < capacity){
            addToFirst(pageNum);
            this.currentSize ++;
        }else{
            //遍历
            boolean flag = false;
            for(int i = 0; i < capacity; i++){
                if(get(i) == pageNum){
                    flag = true;
                    break;
                }
            }

            //有相同的，交换该数据至栈顶
            if(flag){
                this.moveToFirst(pageNum);
            }else{
                //没有相同的，移除栈底元素
                removeLast();
                //添加
                addToFirst(pageNum);
            }
        }
    }

    /**
     * 新增至栈底
     * @param pageNum
     */
    private void addToLast(int pageNum){
        Node node = new Node(last, null, pageNum);
        last.next = node;
        this.last = node;
        this.currentSize++;
    }

    /**
     * 新增值栈顶
     * @param pageNum
     */
    private void addToFirst(int pageNum){
        Node node = new Node(null, first, pageNum);
        first.prev = node;
        this.first = node;
        this.currentSize++;
    }

    /**
     * 移除栈底的元素
     */
    public void removeLast(){
        Node node = last.prev;
        node.next = null;
        this.last = node;
        this.currentSize--;
    }

    /**
     * 从栈底数起，取数
     */
    public int get(int index){
        int in = 0;
        Node temp = last;
        int res = -1;
        while (temp != null){
            if(index == in){
                res = temp.pageNum;
            }
            in++;
            temp = temp.prev;
        }

        return res;
    }

    public void moveToFirst(int pageNum){
        Node temp = last;
        while (temp != null){
            if(temp.pageNum == pageNum){
                break;
            }
            temp = temp.prev;
        }

        //处理节点，任意位置时重组链接
        if(temp.next != null){
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }else{
            //栈底remove调
            removeLast();
        }

        addToFirst(pageNum);
    }

    public int size(){
        return this.currentSize;
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
