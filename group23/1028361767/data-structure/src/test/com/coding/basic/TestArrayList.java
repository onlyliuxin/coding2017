package test.com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

import com.coding.basic.array.ArrayList;

public class TestArrayList {

    @Test
    public void testAdd() {
        ArrayList list = new ArrayList();
        int i = 0;
        for (; i < 1000; i++) {
            list.add(new Object());
        }
        Assert.assertTrue(list.size() == i);
    }

    @Test
    public void testGet() {
        ArrayList list = new ArrayList();
        int i = 0;
        for (; i < 10; i++) {
            list.add(new Object());
        }
        Assert.assertFalse(list.get(5) == null);
        try {
            list.get(10);
            Assert.assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testAddWithIndex() {
        ArrayList list = new ArrayList();
        int i = 0;
        for (; i < 10; i++) {
            list.add(new Object());
        }
        Object obj = list.get(5);
        list.add(5, new Object());
        Assert.assertTrue(list.size() == (i + 1));
        Assert.assertTrue(obj == list.get(6));
    }

    @Test
    public void testRemove() {
        ArrayList list = new ArrayList();
        int i = 0;
        for (; i < 10; i++) {
            list.add(i);
        }
        Object tempObj = list.get(5);
        Assert.assertTrue(tempObj == list.remove(5));
        Assert.assertTrue(list.size() == (i - 1));
        Assert.assertTrue((int) list.get(list.size() - 1) == (i - 1));
    }
}
