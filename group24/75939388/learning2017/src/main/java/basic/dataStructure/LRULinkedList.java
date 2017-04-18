package basic.dataStructure;

/**
 * @author : 温友朝
 * @date : 2017/4/18
 */
public class LRULinkedList {
    public Node first;
    public Node last;
    public Object data;

    private static final int MAX_SIZE = 5;


    public LRULinkedList() {
        this.first = new Node();
        this.last = this.first;
    }

    public LRULinkedList(Object data) {
        this.data = data;
        this.first = new Node(data);
        this.last = this.first;
    }

    public int size() {
        Node temp = first;
        int index = 0;
        while (temp.next != null) {
            index++;
            temp = temp.next;
        }

        return index;
    }

    public void add(Object obj) {
        //未满
        if (this.size() < MAX_SIZE) {
            Node temp = first;
            while(temp.next != null){
                temp = temp.next;
            }
            this.last = temp.next = new Node(obj, null, temp);
        } else {
            //满了，链表中是否有该值的数据，有，升为栈顶；无，栈底数据删除，栈顶存入新数据
            Node temp = first;
            boolean flag = false;
            int index = 0;
            while (temp.next != null) {
                //存在该值的数据
                if (temp.data == obj) {
                    flag = true;
                }
                index ++;
            }
            if(flag){
                Node node = get(index);

                Object data = last.data;
                last.data = node.data;
                node.data = data;
            }else{
                removeFirst();
                while(temp.next != null){
                    temp = temp.next;
                }
                this.last = temp.next = new Node(obj, null, temp);
            }
        }
    }

    @Override
    public String toString() {
        Node temp = this.first;
        StringBuffer sb = new StringBuffer();
        while (temp.next != null){
            sb.append(temp.data).append(",");
            temp = temp.next;
        }

        return sb.toString();
    }

    public Node get(int index){
        Node temp = first;
        int i = 0;
        while(temp.next != null){
            if(index != i){
                temp = temp.next;
            }else break;
            i++;
        }
        return temp;
    }

    public void removeFirst(){
        this.first = this.get(1);
        this.first.before = null;
    }


    private static class Node {
        Object data;
        Node next;
        Node before;

        public Node() {
            this.data = null;
            this.next = null;
            this.before = null;
        }

        public Node(Object obj) {
            this.data = obj;
            this.next = null;
            this.before = null;
        }

        public Node(Object obj, Node next) {
            this.data = obj;
            this.next = next;
            this.before = null;
        }

        public Node(Object obj, Node next, Node before) {
            this.data = obj;
            this.next = next;
            this.before = before;
        }

    }
}
