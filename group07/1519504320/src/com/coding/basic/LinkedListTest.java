package basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nickdd .
 * on 3/10/2017 11:09 AM
 */

public class LinkedListTest {
    private LinkedList list;

    @Before
    public void setUp() throws Exception {
        list = new LinkedList();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void add() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertEquals(list.toString(), "123");
        list.add(1, 5);
        list.add(1, 9);
        Assert.assertEquals(list.toString(), "19523");
    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void size() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertEquals(list.size(), 3);

    }

    @Test
    public void size2() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(2);
        Assert.assertEquals(list.size(), 2);

    }

    @Test
    public void addFirst() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.addFirst(4);
        list.addFirst(66);
        Assert.assertEquals(list.toString(), "664123");

    }

    @Test
    public void addLast() throws Exception {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        Assert.assertEquals(list.toString(), "123");

    }

    @Test
    public void removeFirst() throws Exception {
        list.removeFirst();
        Assert.assertEquals(list.toString(), "");


    }

    @Test
    public void removeLast() throws Exception {

    }

    @Test
    public void iterator() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void reverse() throws Exception {
        list.add(5);
        list.add(2);
        list.add(1);
        list.reverse();
        Assert.assertEquals(list.toString(), "125");
    }

    @Test
    public void removeFirstHalf() throws Exception {
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(10);
        list.removeFirstHalf();
        Assert.assertEquals(list.toString(), "7810");

    }

    @Test
    public void remove1() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(0);
        Assert.assertEquals(list.toString(), "23");
    }

    @Test
    public void remove2() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.remove(1, 2);
        Assert.assertEquals(list.toString(), "1456");
    }

    //* 例如当前链表 = 11->101->201->301->401->501->601->701
    //       * listB = 1->3->4->6
    //        * 返回的结果应该是[101,301,401,601]
    @Test
    public void getElements() throws Exception {
        list.add(11);
        list.add(101);
        list.add(201);
        list.add(301);
        list.add(401);
        list.add(501);
        list.add(601);
        list.add(701);
        LinkedList list_test = new LinkedList();
        list_test.add(1);
        list_test.add(3);
        list_test.add(4);
        list_test.add(6);
        int[] n = list.getElements(list_test);
        int[] result = new int[4];
        result[0] = 101;
        result[1] = 301;
        result[2] = 401;
        result[3] = 601;

        for (int i = 0; i < n.length; i++) {
            Assert.assertEquals(n[i], result[i]);
        }
    }

    @Test
    public void subtract() throws Exception {
        list.add(1);
        //list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(77);
        list.add(79);

        LinkedList list_test = new LinkedList();
        list_test.add(1);
        list_test.add(2);
        list_test.add(3);
        list_test.add(4);
        list_test.add(5);
        list.subtract(list_test);
        Assert.assertEquals(list.toString(), "7779");

    }

    @Test
    public void removeDuplicateValues() throws Exception {
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(8);
        list.removeDuplicateValues();
        Assert.assertEquals(list.toString(), "123458");

    }

    @Test
    public void removeRange() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.removeRange(0,9);
        Assert.assertEquals(list.toString(), "");

    }

    @Test
    public void intersection() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        LinkedList ne = new LinkedList();
        ne.add(1);
        ne.add(2);
        ne.add(3);
        ne.add(5);
        LinkedList result = list.intersection(ne);
        Assert.assertEquals(result.toString(), "123");

    }

}