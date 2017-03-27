package com.coding.basic;


import javax.management.ListenerNotFoundException;

public class LinkedList implements List {

    private Node head = new Node(-1);

    private int size = 0;

    public void add(Object o) {
        Node addNode = new Node(o);
        if (size == 0) {
            head.next = addNode;
            size++;
        } else {
            /*Node n = getNode(size);
            n.next = temp;
            size++;*/
            Node temp = head;
            for (int i = 0; i < size; i++) {
                temp = temp.next;
            }
            temp.next = addNode;
            size++;
        }

    }


    public String toString() {
        Node temp = head;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {

            temp = temp.next;
            sb.append(temp.data);
            sb.append("->");

        }
        sb.deleteCharAt((sb.length() - 1));
        sb.deleteCharAt((sb.length() - 1));
        String result = sb.toString();
        return result;
    }

    public void add(int index, Object o) {
        /*if (index <= 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node addNode = new Node(o);
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }*/
    }

    public Object get(int index) {
        if (index <= 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;

        }
        return temp.data;
    }

    public Object remove(int index) {
        return null;
    }

/*    public Node getNode(int index) {
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = head.next;
        }
        return temp;
    }*/

    public int size() {
        return this.size;
    }

    public void addFirst(Object o) {
        Node addNode = new Node(o);
        addNode.next = head.next;
        head.next = addNode;
        size++;

    }

    public void addLast(Object o) {

    }

    public Object removeFirst() {
        return null;
    }

    public Object removeLast() {
        return null;
    }

    public Iterator iterator() {
        return null;
    }


    private static class Node {
        Object data;
        Node next;

        Node() {
            this.data = null;
            this.next = null;
        }

        Node(Object o) {
            this.data = o;
            this.next = null;
        }

        Node(Node n) {
            this.data = n.data;
            this.next = n.next;
        }

        public boolean hasNext() {
            if (this.next == null) {
                return false;
            } else {
                return true;
            }
        }

    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        /*LinkedList ll = new LinkedList();
        ll.add(3);
        ll.add(7);
        ll.add(8);
        ll.add(9);
        ll.add(10);
        System.out.println(ll);*/
        //System.out.println(this);
        LinkedList result = new LinkedList();
        Node temp = this.head;
        for (int i = 0; i < this.size(); i++) {
            temp = temp.next;
            result.addFirst(temp.data);
        }
        this.head = result.head;
        //System.out.println(this);
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        Node temp = this.head;
        for (int i = 0; i < (int) (this.size / 2); i++) {
            temp = temp.next;

        }
        this.size = this.size - this.size / 2;
        this.head.next = temp.next;

    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        Node startNode = this.head;

        for (int j = 0; j < i; j++) {
            startNode = startNode.next;

        }
        Node endNode = startNode;
        for (int j = 0; j < length; j++) {
            endNode = endNode.next;
        }
        startNode.next = endNode.next;
        size = size - length;

    }

    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     *
     * @param list
     */
    public int[] getElements(LinkedList list) {
        int[] result = new int[list.size()];
        Node node = list.head;

        for (int i = 0; i < result.length; i++) {
            node = node.next;
            result[i] = (int) (this.get(((int) (node.data)) + 1));
        }
        return result;

    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public void subtract(LinkedList list) {
        Node posNode = this.head;
        Node listNode = list.head.next;

        for (int i = 0; i < list.size(); i++) {
            while (posNode.next.data != listNode.data) {
                posNode = posNode.next;
            }
            posNode.next = posNode.next.next;
            listNode = listNode.next;
        }
        this.size = size - list.size();

    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        Node posnode = this.head;
        //Node temp;
        while(posnode.hasNext()){
            if(posnode.data==posnode.next.data){
                posnode.next=posnode.next.next;
                this.size--;
                continue;
            }
            posnode=posnode.next;
        }


    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {
        Node startPos = this.head;
        Node endPos;
        int count1 = 0;
        int count2 = 1;
        for (int i = 0; i < this.size(); i++) {
            if ((int) startPos.next.data > min) {
                break;
            }
            startPos = startPos.next;
            count1++;
        }
        endPos = startPos.next;
        for (int i = count1; i < this.size(); i++) {
            if ((int) endPos.data > max) {
                break;
            }
            endPos = endPos.next;
            count2++;
        }
        size = size - count2;
        startPos.next = endPos;
    }


    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        LinkedList result = new LinkedList();
        Node a = this.head.next;
        Node b = list.head.next;

        while (a.hasNext() && b.hasNext()) {
            if ((int) a.data == (int) b.data) {
                result.add(a.data);
                a = a.next;
                b = b.next;
            } else if ((int) a.data > (int) b.data) {
                b = b.next;
            } else if ((int) a.data < (int) b.data) {
                a = a.next;
            }


        }
        if (a.hasNext() == false) {
            while (b.hasNext() == true) {
                if ((int) a.data == (int) b.data) {
                    result.add(a.data);
                    break;
                } else {
                    b = b.next;
                }
            }
        } else if (b.hasNext() == false) {
            while (a.hasNext() == true) {
                if ((int) a.data == (int) b.data) {
                    result.add(a.data);
                    break;
                } else {
                    a = a.next;
                }
            }
        }
        return result;


    }


}
