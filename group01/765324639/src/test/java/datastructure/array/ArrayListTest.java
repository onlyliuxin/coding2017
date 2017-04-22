package datastructure.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import datastructure.Iterator;

public class ArrayListTest {

    private ArrayList arrayList = new ArrayList();

    @Before
    public void setUp() {
        for (int i = 0; i < 500; i++) {
            arrayList.add(i);
        }
    }

    @Test
    public void testAddObject() {
        for (int i = 0; i < 500; i++) {
            arrayList.add(i);
        }
    }

    @Test
    public void testAddIntObject() {
        arrayList.add(100, -100);
        Assert.assertEquals(-100, arrayList.get(100));
        Assert.assertEquals(100, arrayList.get(101));
        Assert.assertEquals(501, arrayList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIllegalIntObject() {
        arrayList.add(1000, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIntObject() {
        arrayList.add(-1, 5);
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 500; i++) {
            Assert.assertEquals(i, ((Integer) arrayList.get(i)).intValue());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIllegalGet() {
        arrayList.get(500);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeGet() {
        arrayList.get(-10);
    }

    @Test
    public void testRemove() {
        Assert.assertEquals(100, arrayList.remove(100));
        Assert.assertEquals(101, arrayList.get(100));
        Assert.assertEquals(499, arrayList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIllegalRemove() {
        arrayList.remove(500);
    }

    @Test
    public void testSize() {
        Assert.assertEquals(500, arrayList.size());
    }

    @Test
    public void testIterator() {
        Iterator iterator = arrayList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals(i, iterator.next());
            i++;
        }
        Assert.assertEquals(500, i);
    }

}
