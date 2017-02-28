/**
 * Created by wangtiegang on 2017/2/25.
 */
public class MyLinkedList<E> implements List {
    //当前size
    private int size;
    //第一个节点
    private Node firstNode;
    //最后一个节点
    private Node lastNode;


    //构造存放数据及指针的Node
    private static class Node<E>{
        E data;
        Node<E> preNode;
        Node<E> nextNode;

        public Node(E data,Node preNode,Node nextNode){
            this.data = data;
            this.preNode = preNode;
            this.nextNode = nextNode;
        }
    }

    @Override
    public void add(Object o) {

        //将最后一个node放到preNode
        Node node = new Node(o,lastNode,null);
        //将最后一个node的nextNode设置成o
        if(lastNode != null){
            lastNode.nextNode = node;
        }

        lastNode = node;

        if(firstNode == null){
            firstNode = node;
        }
        //size增加
        size++;
    }

    @Override
    public void add(int index, Object o) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        if(index == size){
            this.add(o);
        }else {
            //找到index个
            Node node = firstNode;
            for(int i = 0;i<index;i++){
                node = node.nextNode;
            }
            //将原index个的pre给o的pre
            Node newNode = new Node(o,node.preNode,node);
            if(index != 0){
                node.preNode.nextNode = newNode;
            }
            //将o的给index的pre
            node.preNode = newNode;
            if(index == 0){
                firstNode = newNode;
            }
            size++;
        }

    }

    @Override
    public Object get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node node = firstNode;
        for(int i = 0;i<index;i++){
            node = node.nextNode;
        }
        return node.data;
    }

    @Override
    public Object remove(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        //找到index个
        Node node = firstNode;
        for(int i = 0;i<index;i++){
            node = node.nextNode;
        }
        //如果移除0，则需要替换firstNode
        if(index != 0){
            node.preNode.nextNode = node.nextNode;
        }else {
            firstNode = node.nextNode;
        }

        //如果移除最后一位，则需要替换lastNode
        if(index < (size-1)){
            node.nextNode.preNode = node.preNode;
        }else{
            lastNode = node.preNode;
        }
        size--;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

}
