package com.circle.collection;

/**
 * Created by keweiyang on 2017/2/25.
 */
public class LinkedList implements List{
    java.util.LinkedList list;

    private Node first = null;

    private int currentIndex = -1;//主要用于统计链表长度

    //注意Node是静态内部类
    private static class Node {

        Object data;
        Node nextNode;

        public Node(Object obj) {
            this.data = obj;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return "data= " + data;
        }
    }

    /**
     * 插入
     *
     * @param o
     */
    public void add(Object o) {
        //1:生成一个Node
        Node newNode = new Node(o);
        Node currentNode = first;
        //2:插入Node
        if (currentNode == null) {
            first = newNode;
        } else {
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);

        }
        currentIndex++;
    }

    /**
     * 在指定位置插入节点
     *
     * @param index
     * @param o
     */
    public void add(int index, Object o) {

        Node newNode = new Node(o);

        if (index < 0 || index > currentIndex + 1) {
            throw new RuntimeException("索引不正确");
        }

        Node currentNode = first;
        Node previousNode = currentNode;
        int pos = 0;
        while (currentNode != null && pos != index) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
            pos++;
        }

        previousNode.setNextNode(newNode);
        newNode.setNextNode(currentNode);
        currentIndex++;

    }

    public Object get(int index) {

        rangeCheck(index);
        Node node = first;
        int pos = 0;

        while (node != null && pos != index) {

            node = node.getNextNode();
            pos++;
        }
        return node;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > currentIndex) {
            throw new RuntimeException("索引不正确");
        }
    }

    public Object remove(int index) {

        //1:获取要删除的节点
        rangeCheck(index);
        Node currentNode = first;


        if (index == 0) {

            currentNode = first;
            first = first.getNextNode();
        }else{
            Node previousNode = first;
            int pos = 0;
            while (currentNode != null && pos != index) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
                pos++;
            }

            //2:执行删除操作
            previousNode.setNextNode(currentNode.getNextNode());
        }

        currentIndex--;

        return currentNode;
    }

    public int size() {
        return currentIndex + 1;
    }

    public void addFirst(Object o) {
        Node newNode = new Node(o);
        if (first == null) {
            first = newNode;
        } else {
            newNode.setNextNode(first);
            first = newNode;
        }
        currentIndex++;
    }

    public void addLast(Object o) {

        Node newNode = new Node(o);
        Node currentNode = first;
        if (currentNode == null) {
            first=newNode;
        } else {
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);

        }
        currentIndex++;

    }

    public Object removeFirst() {

        Node node = first;
        if (first == null) {
            throw new RuntimeException("链表长度为0，不能执行这步操作");
        } else {
            first = first.getNextNode();
        }
        currentIndex--;
        return node;
    }

    public Object removeLast() {
        Node currentNode = first;
        Node previousNode = currentNode;
        if (currentNode == null) {
            throw new RuntimeException("链表长度为0，不能执行这步操作");
        } else {
            while (currentNode.getNextNode() != null) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }
            previousNode.setNextNode(null);

        }
        currentIndex--;
        return currentNode;
    }

    public Iterator iterator() {
        return new Iterator() {
            int pos = -1;

            public boolean hasNext() {
                if (pos + 1 <= currentIndex) {
                    return true;
                }
                return false;
            }

            public Object next() {
                if (hasNext()) {
                    pos++;
                    return get(pos);
                }
                return null;
            }
        };
    }
}
