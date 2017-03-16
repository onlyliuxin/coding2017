package com.coding.basic;


public class LinkedList<T> implements List {

    private static class Node {
        Object data;
        Node next;
    }

    private Node head = null;
    private int size = 0;

    private Node getNode(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node n = head;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n;
    }

    public void add(Object o) {
        addLast(o);
    }

    public void add(int index, Object o) {
        if (index == 0) {
            Node tmp = head;
            head = new Node();
            head.data = o;
            head.next = tmp;
        } else {
            Node pre = getNode(index - 1);
            Node tmp = new Node();
            tmp.data = o;
            tmp.next = getNode(index);
            pre.next = tmp;
        }
        size++;
    }

    public Object get(int index) {
        return getNode(index).data;
    }

    public Object remove(int index) {
        Node n = getNode(index);
        if (index == 0) {
            head = n.next;
            return head;
        } else {
            Node pre = getNode(index - 1);
            pre.next = n.next;
        }
        size--;

        return n;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        if (head == null) {
            head = new Node();
            head.data = o;
        }
        Node n = new Node();
        Node tmp = head;
        n.data = o;
        n.next = tmp;
        head = n;
        size++;
    }

    public void addLast(Object o) {
        if (head == null) {
            head = new Node();
            head.data = o;
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            Node x = new Node();
            x.data = o;
            x.next = null;
            n.next = x;
        }
        size++;

    }

    public Object removeFirst() {
        Node tmp;
        if (head == null) {
            return null;
        } else {
            tmp = head;
            head = head.next;
        }
        size--;
        return tmp;
    }

    public Object removeLast() {
        return null;
    }


    public Iterator iterator() {

        return new Iterator() {
            int cursor = 0;

            @Override
            public boolean hasNext() {

                return cursor != size;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    int i = cursor;
                    Object next = get(i);

                    cursor = i + 1;
                    return next;

                }
                return null;
            }
        };
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node n = head;
        while (n != null) {
            sb.append(n.data);
            n = n.next;
        }
        return sb.toString();
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }

        Node pre = head;
        Node cur = head.next;
        pre.next = null;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head = pre;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        int flag = size() / 2;
        for (int i = 0; i < flag; i++) {
            removeFirst();
        }


    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (i + length >= size) {
            length = size - i - 1;
        }
        for (int j = 0; j < length; j++) {
            remove(i);
        }


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
    public int[] getElements(LinkedList list) throws Exception {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if ((int) list.get(i) < 0 || (int) list.get(i) >= this.size) {
                throw new IndexOutOfBoundsException();
            } else {
                result[i] = (int) this.get((int) list.get(i));
            }
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

        if (head == null) {
            return;
        }
        Node fakeHead = new Node();
        fakeHead.next = head;
        Node pre = fakeHead;
        Node cur = head;
        for (int i = 0; i < list.size(); i++) {
            pre = fakeHead;
            cur = pre.next;
            while (cur != null) {
                if (cur.data == list.get(i) && cur.next != null) {
                    pre.next = cur.next;
                    cur = cur.next;
                } else if (cur.data == list.get(i) && cur.next == null) {
                    pre.next = null;
                } else if (cur.data != list.get(i)) {
                    pre = pre.next;
                    cur = cur.next;
                }
            }
        }
        head = fakeHead.next;
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            return;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (pre.data.equals(cur.data)) {
                if (cur.next != null) {
                    pre.next = cur.next;
                    pre = pre;
                    cur = pre.next;
                    continue;
                } else {
                    pre.next = null;
                    return;
                }
            } else {
                pre = cur;
                cur = cur.next;
            }


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
        if (head == null) {
            return;
        }
        Node node = head;
        while (node != null && (int) node.data > min && (int) node.data < max) {
            node = node.next;
        }
        if (node == null || node == null) {
            head = null;
            return;
        } else {
            head = node;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != null && (int) cur.data > min && (int) cur.data < max) {
            if (cur.next != null) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                return;
            }
        }

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     * @param list
     */
    public LinkedList intersection(LinkedList list) {
        if (list == null && this == null) {
            return null;
        }
        if (list == null || list.size() == 0) {
            return this;
        }
        if (this == null) {
            return list;
        }
        LinkedList result = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            Node n = head;
            while ((int) n.data < (int) list.get(i) && n.next != null) {
                n = n.next;
            }
            if ((int) n.data == (int) list.get(i)) {
                result.add(list.get(i));
            }

        }
        return result;
    }
}
