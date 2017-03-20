package com.donaldy.test;

import com.donaldy.basic.ArrayList;
import org.junit.*;

import static org.junit.Assert.assertEquals;


/**
 * Created by donal on 2017/3/7.
 */
public class ArrayListTest {

    ArrayList arrayList;

    @Before
    public void before() throws Exception {
        arrayList = new ArrayList();
        arrayList.add(98);
    }

    @Test
    public void testAddWithArg() {
        assertEquals(98, arrayList.get(0));
    }

    @Test(expected = RuntimeException.class)
    public void testRuntimeException(){
        arrayList.get(100);
        arrayList.get(-1);
    }

    @Test
    public void testAddWithArgs() {
        arrayList.add(0, 99);
        assertEquals(99, arrayList.get(0));
        assertEquals(98, arrayList.get(1));
    }

    @Test
    public void testRemove(){
        arrayList.add(88);
        arrayList.add(78);
        assertEquals(3, arrayList.size());
        assertEquals(88, arrayList.remove(1));
        assertEquals(78, arrayList.get(1));
        assertEquals(2, arrayList.size());
    }

}
