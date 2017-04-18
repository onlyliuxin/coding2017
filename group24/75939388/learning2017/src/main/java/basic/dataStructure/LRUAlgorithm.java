package basic.dataStructure;

/**
 * @author : 温友朝
 * @date : 2017/4/18
 */
public class LRUAlgorithm {
    public Node first;
    public Node last;
    public Object data;

    private static final int MAX_SIZE = 5;


    public LRUAlgorithm(){
        this.first = new Node(null, new Node());
        this.last = this.first;
    }

    public LRUAlgorithm(Object data){
        this.data = data;
        this.first = new Node(data, new Node());
        this.last = this.first;
    }

    public int size(){
        Node temp = first;
        int index = 0;
        while (temp.next != null){
            index ++;
            temp = temp.next;
        }

        return index;
    }

    public void add(Object obj){
        if(this.size() < MAX_SIZE){
            Node temp = first;

        }
    }















    private static class Node {
        Object data;
        Node next;
        Node before;

        private Node() {}

        public Node(Object obj, Node next) {
            this.data = obj;
            this.next = next;
            this.before = null;
        }

        public Node(Object obj, Node next, Node before){
            this.data = obj;
            this.next = next;
            this.before = before;
        }

    }
}
