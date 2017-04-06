/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass;

import java.util.Arrays;

/**
 *
 * @author CJ
 */
public class LinkedList implements List {

    private Node head;

    private int size;
//    private int curIterIndex;

    public LinkedList() {
        head = new Node();
        size = 0;
//        curIterIndex = 0;
    }

    public void add(Object o) {
        addLast(o);
    }

    private Node getNode(int index) {
        if (index == -1) {
            return head;
        } else {
            Node returnNode = head.next;
            for (int i = 0; i < index; i++) {
                returnNode = returnNode.next;
            }
            return returnNode;
        }
    }

    public void add(int index, Object o) {
        Node preNode = getNode(index - 1);
        Node addNode = new Node();
        addNode.data = o;
        addNode.next = preNode.next;
        preNode.next = addNode;
        size++;
    }

    public Object get(int index) {
        return getNode(index).data;
    }

    public Object remove(int index) {
        Node preNode = getNode(index - 1);
        Node delNode = preNode.next;
        preNode.next = delNode.next;
        // 返回被删除的Node... 可能是为了测试吧
        size--;
        return delNode;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
        Node fNode = new Node();
        fNode.data = o;
        fNode.next = head.next;
        head.next = fNode;
        size++;
    }

    public void addLast(Object o) {
//        System.err.println("Curr add: "+ o);
        Node lastNode = getNode(size - 1);
//        System.err.println(lastNode);
        Node lNode = new Node();
        lNode.data = o;
        lastNode.next = lNode;
        size++;
    }

    public Object removeFirst() {
        return removeFirstNode().data;
    }

    private Node removeFirstNode() {
        Node firstNode = head.next;
        head.next = firstNode.next;
        size--;
        return firstNode;
    }

    public Object removeLast() {
        return removeLastNode().data;
    }

    private Node removeLastNode() {
        Node last2Node = getNode(size - 2);
        Node lastNode = last2Node.next;
        last2Node.next = null;
        size--;
        return lastNode;
    }

    public Iterator iterator() {
        return new Iterator() {
//            int curIterIndex = 0;
            Node curNode = head;

            @Override
            public boolean hasNext() {
                return curNode.next != null;
            }

            @Override
            public Object next() {
                curNode = curNode.next;
                return curNode.data;
            }
        };
    }

    private static class Node {

        Object data;
        Node next;
    }

    /**
     * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
     */
    public void reverse() {

        int oriSize = size;
        Node newHead = new Node();
        newHead.next = this.removeLastNode();
        Node preNode = newHead.next;

        while (size > 0) {
            preNode.next = this.removeLastNode();
            preNode = preNode.next;
        }
        // 不考虑线程安全的情况下，恢复size
        size = oriSize;
        head = newHead;
    }

    /**
     * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
     * ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        int reDirIndex = size / 2; // java会自动 取 0 
        size = size - reDirIndex;
        System.err.println(reDirIndex);
        Node jumpNode = getNode(reDirIndex);
        head.next = jumpNode;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public void remove(int i, int length) {
        Node fromNode = getNode(i - 1);
        for (int j = 0; j < length; j++) {
            fromNode.next = fromNode.next.next;
            size--;
        }
    }

    /**
     * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
     * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     *
     * @param list
     */
//    public static int[] getElements(LinkedList list) {
    // 这个似乎 不应该是 静态方法
    public int[] getElements(LinkedList list) {
        int[] returnIndex = new int[list.size];
        int validCounts = 0;
//        Iterator curIter = list.iterator();
//        int curIndex = 0;
//        while(curIter.hasNext()){
//            returnIndex[curIndex++] =(Integer) this.get((Integer) curIter.next());
//        }

        // 已知是内外都是玩去排序好的，所以是需要一个高效的算法
        Iterator innerIter = this.iterator();
        Iterator curIter = list.iterator();
        int curCount = 0;
        int curIndex;
        int preIndex = 0;

        while (curIter.hasNext()) {
            curIndex = (Integer) curIter.next();
            if (curIndex < preIndex) {
                System.err.println("Warning: Skip index no in sorted order...");
                continue;
            }
//            int skipCounts = curIndex-preIndex;
            for (int i = preIndex; i < curIndex; i++) {
                innerIter.next();
            }
            validCounts++;
            returnIndex[curCount++] = (Integer) innerIter.next();
            preIndex = curIndex + 1;
        }
        return Arrays.copyOf(returnIndex, validCounts);
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */
    public void subtract(LinkedList list) {
        // 假定 当前 链表 和 待删除 链表 都是顺序排序的
        Node preNode = head;
        Node curNode;
        Iterator element2rmIter = list.iterator();
        while (element2rmIter.hasNext()) {
            int curValue2rm = (Integer) element2rmIter.next();
            while (preNode.next != null) {
                curNode = preNode.next;
                if ((Integer) curNode.data == curValue2rm) {
                    // 删除
                    preNode.next = curNode.next;
                }else if((Integer) curNode.data > curValue2rm){
                    break; // 跳出内层循环，从而获取下一个待删除数据
                }else {
                    // 更新
                    preNode = curNode;
                }
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        Node preNode = head;
        Node curNode;
        while (preNode.next != null) {
            curNode = preNode.next;
            if (curNode.data == preNode.data) {
                preNode.next = curNode.next;
            } else {
                preNode = curNode;
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *
     * @param min
     * @param max
     */
    public void removeRange(int min, int max) {
        // 假定 当前链表 是 从小到大 排列的
        Node preNode = head;
        Node curNode;
        while (preNode.next != null) {
            curNode = preNode.next;
            if (min < (Integer) curNode.data && max > (Integer) curNode.data) {
                preNode.next = curNode.next;
            } else {
                preNode = curNode;
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

        
        LinkedList newList = new LinkedList();
        
        // 假定 当前 链表 和 待删除 链表 都是顺序排序的
        Node preNode = head;
        Node curNode;
        Iterator element2rmIter = list.iterator();
        while (element2rmIter.hasNext()) {
            int curValue2rm = (Integer) element2rmIter.next();
            while (preNode.next != null) {
                curNode = preNode.next;
                if ((Integer) curNode.data == curValue2rm) {
                    // 删除
                    preNode = curNode;
                    newList.add(curNode.data); // 添加data
                }else if((Integer) curNode.data > curValue2rm){
                    break; // 跳出内层循环，从而获取下一个待删除数据
                }else {
                    // 更新
                    preNode = curNode;
                }
            }
        }
        
        
        return newList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkdedList [");
        Node curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
            sb.append(curNode.data).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()).append("]");
        return sb.toString();
    }

    public void clear() {
        head.next = null;
        size = 0;
    }

    public static void main(String[] args) {
        // 先写一些测试
        LinkedList ll = new LinkedList();
        System.err.println("Test Add");
        for (int i = 0; i < 10; i++) {
            ll.add(i);
        }
        System.err.println("Size: " + ll.size);
        System.err.println(ll);
        System.err.println("Test Add last");
        ll.addLast(10);
        System.err.println(ll);
        System.err.println("Test Add First");
        ll.addFirst(-1);
        System.err.println(ll);
        System.err.println("Test remove ");
        ll.remove(0);
        System.err.println(ll);
//        ll.remove(11);
//        System.err.println(ll);
        ll.removeFirst();
        System.err.println(ll);
        ll.removeLast();
        System.err.println(ll);
        System.err.println("Test reverse()");
        ll.reverse();
        System.err.println(ll);
        System.err.println("Test removeFirstHalf()");
        ll.removeFirstHalf();
        System.err.println(ll);
//        System.err.println(9/2);
        System.err.println("Test remove()");
        ll.remove(1, 2);
        System.err.println(ll);
//        System.err.println(ll.);
        LinkedList newList = new LinkedList();
        newList.add(3);
        newList.add(4);
        newList.add(6);
        newList.add(5);
//        System.err.println();
        ll.clear();
        System.err.println("Re Init");
        for (int i = 0; i < 10; i++) {
            ll.add(i);
        }
        System.err.println(ll);
        for (int curI : ll.getElements(newList)) {
            System.err.println(curI);
        }
        ll.clear();
        for (int i = 0; i < 10; i++) {
            ll.add(i);
            ll.add(i);
            ll.add(i);
        }
        System.err.println(ll);
        ll.removeDuplicateValues();
        System.err.println(ll);
        System.err.println("Test Remove removeRange(3, 6)");
        ll.removeRange(3, 6);
        System.err.println(ll);
        
        LinkedList rmList = new LinkedList();
        rmList.add(3);
        rmList.add(4);
        rmList.add(7);
        rmList.add(8);
        ll.subtract(rmList);
        System.err.println("Test subtract(3,4,7,8)");
        System.err.println(ll);
        ll.clear();
        for (int i = 0; i < 10; i++) {
            ll.add(i);
        }
        rmList.add(11);
        System.err.println(ll);
        System.err.println(rmList);
        System.err.println(ll.intersection(rmList));
//        System.err.println("Test substract");
//        ll.subtract(newList);
//        System.err.println(ll);

    }

}
