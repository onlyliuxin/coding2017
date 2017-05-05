package com.johnChnia.coding2017.basic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * Created by john on 2017/3/8.
 */

public class ArrayListTest {
    private ArrayList<Integer> arrayList1;
    private ArrayList<Integer> arrayList2;
    private ArrayList<Integer> arrayList3;
    private ArrayList<Integer> arrayList4;
    private ArrayList<String> arrayList5;

    @Before
    public void setUp() throws Exception {
        arrayList1 = new ArrayList<>(3);
        arrayList2 = new ArrayList<>(3);
        arrayList3 = new ArrayList<>(3);
        arrayList4 = new ArrayList<>(3);
    }

    @Test
    public void testAddAndGet() {
        arrayList1.add(99);
        assertThat(arrayList1.get(0), equalTo(99));
    }

    @Test
    public void testGrow() {
        for (int i = 0; i < 6; i++) {
            arrayList2.add(10);
        }
        assertThat(arrayList2.size(), equalTo(6));
    }

    @Test
    public void testAddElementByIndex() {
        for (int i = 0; i < 3; i++) {
            arrayList3.add(10);
        }
        arrayList3.add(1, 1000);
        assertThat(arrayList3.get(1), equalTo(1000));
    }

    @Test
    public void testRemoveElementByIndex() {
        for (int i = 0; i < 6; i++) {
            arrayList4.add(i);
        }
        Object removed = arrayList4.remove(4);
        assertThat(removed, equalTo(4));
        assertThat(arrayList4.size(), equalTo(5));
    }


}
