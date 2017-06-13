package me.lzb.homework0312.basic;

import me.lzb.homework0312.basic.Iterator;
import me.lzb.homework0312.basic.LinkedList;
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
    }


    @Test
    public void iteratoreTest(){
        Iterator iterator = linkedList.iterator();
        int a = 0;
        while (iterator.hasNext()){
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


}
