package me.lzb.homework0312.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * ArrayList测试
 * Created by LZB on 2017/3/11.
 */

public class ArrayListTest {

    private ArrayList arrayList;

    private String[] strArray;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void instantiate() throws Exception {
        arrayList = new ArrayList();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");

        strArray = new String[]{"a", "b", "c", "d"};
    }


    @Test
    public void sizeTest() {
        Assert.assertEquals(4, arrayList.size(), 0);
    }

    @Test
    public void getTest() throws IndexOutOfBoundsException {
        Assert.assertEquals("a", arrayList.get(0).toString());
        Assert.assertEquals("c", arrayList.get(2).toString());
        Assert.assertEquals("d", arrayList.get(3).toString());

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("index boom");
        arrayList.get(100);
        arrayList.get(-1);
    }

    @Test
    public void iteratoreTest(){
        Iterator iterator = arrayList.iterator();
        int a = 0;
        while (iterator.hasNext()){
            Assert.assertEquals(strArray[a], iterator.next().toString());
            a = a + 1;
        }
    }


    @Test
    public void addTest() {
        arrayList.add("f");
        Assert.assertEquals("f", arrayList.get(4).toString());
    }

    @Test
    public void removeTest() throws IndexOutOfBoundsException {

        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");


        String r1 = list.remove(1).toString();
        Assert.assertEquals("b", r1);
        Assert.assertEquals(3, list.size());

        String r0 = list.remove(0).toString();
        Assert.assertEquals("a", r0);
        Assert.assertEquals(2, list.size());

        String rs = list.remove(list.size() - 1).toString();
        Assert.assertEquals("d", rs);
        Assert.assertEquals(1, list.size());

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("index boom");
        list.remove(100);

    }


    @Test
    public void addIndexTest() throws IndexOutOfBoundsException {
        arrayList.add(0, "0");
        Assert.assertEquals("0", arrayList.get(0).toString());
        arrayList.add(arrayList.size(), "s");
        Assert.assertEquals("s", arrayList.get(arrayList.size() - 1).toString());
        arrayList.add(2, "2a");
        Assert.assertEquals("2a", arrayList.get(2).toString());

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("index boom");
        arrayList.add(10, "10a");
    }


}
