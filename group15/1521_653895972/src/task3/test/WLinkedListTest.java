package task3.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import task3.basic.WLinkedList;

/**
 * Created by wanc on 2017/3/13.
 */
public class WLinkedListTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRemoveDuplicateValues() throws Exception {
        WLinkedList wll = new WLinkedList();
        wll.add(11);
        wll.add(12);
        wll.add(12);
        wll.add(13);
        wll.add(14);
        wll.add(14);
        wll.add(15);
        wll.removeDuplicateValues();
        Assert.assertArrayEquals(new Object[]{11,12,13,14,15},wll.toArray());
    }

    @Test
    public void testRemoveRange() throws Exception {
        WLinkedList wll = new WLinkedList();
        wll.add(11);
        wll.add(12);
        wll.add(13);
        wll.add(14);
        wll.add(15);
        wll.add(16);
        wll.add(17);
        wll.removeRange(12,16);
//        wll.removeRange2(12,16);
        Assert.assertArrayEquals(new Object[]{11,12,16,17},wll.toArray());
    }

    @Test
    public void testIntersection() throws Exception {
        WLinkedList wll = new WLinkedList();
        wll.add(11);
        wll.add(12);
        wll.add(13);
        wll.add(14);
        wll.add(15);
        wll.add(16);
        wll.add(17);
        WLinkedList wll2 = new WLinkedList();
        wll2.add(8);
        wll2.add(10);
        wll2.add(12);
        wll2.add(14);
        wll2.add(16);
        wll2.add(18);
        WLinkedList wll3 =wll.intersection(wll2);
        Assert.assertArrayEquals(new Object[]{12,14,16},wll3.toArray());
    }
}