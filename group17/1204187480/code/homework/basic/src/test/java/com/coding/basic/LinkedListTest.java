package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by luoziyihao on 3/23/17.
 */
public class LinkedListTest {

    @Test
    public void add() throws Exception {

    }

    @Test
    public void add1() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

    @Test
    public void addFirst() throws Exception {

    }

    @Test
    public void addLast() throws Exception {

    }

    @Test
    public void removeFirst() throws Exception {

    }

    @Test
    public void removeLast() throws Exception {

    }

    @Test
    public void removeFirstHalf() throws Exception {
        LinkedList linkedList = createAndFillLinkedList(0);
        linkedList.removeFirstHalf();
        Assert.assertEquals("[]", linkedList.toString());
    }
    @Test
    public void removeFirstHalf1() throws Exception {
        LinkedList linkedList = createAndFillLinkedList(1);
        linkedList.removeFirstHalf();
        Assert.assertEquals("[1]", linkedList.toString());
    }
    @Test
    public void removeFirstHalf2() throws Exception {
        LinkedList linkedList = createAndFillLinkedList(2);
        linkedList.removeFirstHalf();
        Assert.assertEquals("[2]", linkedList.toString());
    }
    @Test
    public void removeFirstHalf3() throws Exception {
        LinkedList linkedList = createAndFillLinkedList(3);
        linkedList.removeFirstHalf();
        Assert.assertEquals("[2,3]", linkedList.toString());
    }

    private LinkedList createAndFillLinkedList() {
        return createAndFillLinkedList(4);
    }

    private LinkedList createAndFillLinkedList(int length) {

        LinkedList linkedList = new LinkedList();
        for (int i = 1; i <= length; i++) {
            linkedList.add(i);
        }
        return linkedList;
    }

    @Test
    public void remove1() throws Exception {
        LinkedList list = createAndFillLinkedList(4);
        list.remove(0, 0);
        Assert.assertEquals("[1,2,3,4]", list.toString());
    }
 @Test
    public void remove2() throws Exception {
        LinkedList list = createAndFillLinkedList(4);
        list.remove(0, 1);
        Assert.assertEquals("[2,3,4]", list.toString());
    }
 @Test
    public void remove3() throws Exception {
        LinkedList list = createAndFillLinkedList(4);
        list.remove(1, 0);
        Assert.assertEquals("[1,2,3,4]", list.toString());
    }
 @Test
    public void remove4() throws Exception {
        LinkedList list = createAndFillLinkedList(4);
        list.remove(1, 1);
        Assert.assertEquals("[1,3,4]", list.toString());
    }
@Test
    public void remove5() throws Exception {
        LinkedList list = createAndFillLinkedList(4);
        list.remove(1, 3);
        Assert.assertEquals("[1]", list.toString());
    }
@Test
    public void remove6() throws Exception {
        LinkedList list = createAndFillLinkedList(4);
        list.remove(1, 4);
        Assert.assertEquals("[1]", list.toString());
    }
@Test
    public void remove7() throws Exception {
        LinkedList list = createAndFillLinkedList(4);
        list.remove(1, 5);
        Assert.assertEquals("[1]", list.toString());
    }

    @Test
    public void getElements() throws Exception {

    }

    @Test
    public void subtract() throws Exception {

    }

    @Test
    public void removeDuplicateValues() throws Exception {

    }

    @Test
    public void removeRange() throws Exception {

    }

    @Test
    public void intersection() throws Exception {

    }

    @Test
    public void iterator() throws Exception {

        List linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        Assert.assertEquals("[1,2,3,4]", linkedList.toString());
    }

    @Test
    public void reverse() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.reverse();
        Assert.assertEquals("[4,3,2,1]", linkedList.toString());
    }

}