package me.lzb.basic;

import me.lzb.basic.Iterator;
import me.lzb.basic.LinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * linkedliksTest
 * Created by LZB on 2017/3/11.
 */
public class LinkedListTest {

    private LinkedList linkedList;

    private LinkedList intList;

    private String[] strArray;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void instantiate() throws Exception {
        linkedList = new LinkedList();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");

        strArray = new String[]{"a", "b", "c", "d"};

        intList = new LinkedList();
        intList.add(0);
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(8);

    }


    @Test
    public void iteratoreTest() {
        Iterator iterator = linkedList.iterator();
        int a = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals(strArray[a], iterator.next().toString());
            a = a + 1;
        }
    }


    @Test
    public void sizeTest() {
        Assert.assertEquals(4, linkedList.size(), 0);
    }

    @Test
    public void getTest() throws IndexOutOfBoundsException {
        Assert.assertEquals("a", linkedList.get(0).toString());
        Assert.assertEquals("b", linkedList.get(1).toString());
        Assert.assertEquals("d", linkedList.get(3).toString());

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("index boom");
        linkedList.get(100);
        linkedList.get(-1);
    }


    @Test
    public void addTest() {
        linkedList.add("f");
        Assert.assertEquals("f", linkedList.get(4).toString());
    }

    @Test
    public void removeTest() throws IndexOutOfBoundsException {
        String r1 = linkedList.remove(1).toString();
        Assert.assertEquals("b", r1);
        Assert.assertEquals(3, linkedList.size());

        String r0 = linkedList.remove(0).toString();
        Assert.assertEquals("a", r0);
        Assert.assertEquals(2, linkedList.size());

        String rs = linkedList.remove(linkedList.size() - 1).toString();
        Assert.assertEquals("d", rs);
        Assert.assertEquals(1, linkedList.size());

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("index boom");
        linkedList.remove(100);
        linkedList.remove(-1);
    }


    @Test
    public void addIndexTest() throws IndexOutOfBoundsException {
        linkedList.add(0, "0");
        Assert.assertEquals("0", linkedList.get(0).toString());
        linkedList.add(linkedList.size(), "s");
        Assert.assertEquals("s", linkedList.get(linkedList.size() - 1).toString());
        linkedList.add(2, "2a");
        Assert.assertEquals("2a", linkedList.get(2).toString());
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("index boom");
        linkedList.add(100, "10a");

    }


    @Test
    public void reverseTest() {
        linkedList.reverse();
        Assert.assertEquals("[d,c,b,a]", linkedList.toString());
    }

    @Test
    public void removeFirstHalfTest() {
        intList.removeFirstHalf();
        Assert.assertEquals("[4,5,6,7,8]", intList.toString());
        Assert.assertEquals(5, intList.size());
        linkedList.removeFirstHalf();
        Assert.assertEquals("[c,d]", linkedList.toString());
        Assert.assertEquals(2, linkedList.size());
    }

    @Test
    public void removeITest() {
        intList.remove(0, 10);
        Assert.assertEquals("[]", intList.toString());
        Assert.assertEquals(0, intList.size());


        linkedList.remove(1, 2);
        Assert.assertEquals("[a,d]", linkedList.toString());
        Assert.assertEquals(2, linkedList.size());


        LinkedList l = new LinkedList();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        l.remove(0, 2);
        Assert.assertEquals("[c,d]", l.toString());
        Assert.assertEquals(2, l.size());
    }


    @Test
    public void getElementsTest() {
        int[] a = {1, 3, 4, 6};

        LinkedList l = new LinkedList();
        l.add(1);
        l.add(3);
        l.add(4);
        l.add(6);
        int[] re = intList.getElements(l);

        Assert.assertEquals(a.length, re.length);
        for (int i = 0; i < a.length; i++) {
            Assert.assertEquals(a[i], re[i]);
        }

    }


    @Test
    public void subtractTest() {
        LinkedList l = new LinkedList();
        l.add(1);
        l.add(3);
        l.add(4);
        l.add(6);
        intList.subtract(l);
        Assert.assertEquals(5, intList.size());
        Assert.assertEquals("[0,2,5,7,8]", intList.toString());
    }


    @Test
    public void removeDuplicateValuesTest() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(5);
        list.add(6);
        list.removeDuplicateValues();

        Assert.assertEquals("[1,2,3,5,6]", list.toString());
    }


    @Test
    public void removeRangeTest() {
        {
            LinkedList linkedList = new LinkedList();

            linkedList.add(11);
            linkedList.add(12);
            linkedList.add(13);
            linkedList.add(14);
            linkedList.add(16);
            linkedList.add(16);
            linkedList.add(19);

            linkedList.removeRange(10, 19);
            Assert.assertEquals("[19]", linkedList.toString());
        }

        {
            LinkedList linkedList = new LinkedList();

            linkedList.add(11);
            linkedList.add(12);
            linkedList.add(13);
            linkedList.add(14);
            linkedList.add(16);
            linkedList.add(16);
            linkedList.add(19);

            linkedList.removeRange(10, 14);
            Assert.assertEquals("[14,16,16,19]", linkedList.toString());
        }
    }


    @Test
    public void intersectionTest() {
        LinkedList list1 = new LinkedList();
        list1.add(1);
        list1.add(6);
        list1.add(7);

        LinkedList list2 = new LinkedList();
        list2.add(2);
        list2.add(5);
        list2.add(6);

        LinkedList newList = list1.intersection(list2);
        Assert.assertEquals("[6]", newList.toString());
    }

}
