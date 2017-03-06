package com.coding.basic;

import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * Created by mark on 17/2/24.
 */
public class ArrayListTest {

    private static ArrayList list;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        list = new ArrayList();
    }

    @After
    public void tearDown() throws Exception {
        list = null;
    }

    @Test
    public void add() throws Exception {
        // 可以加入元素
        list.add("hello");
        Assert.assertEquals(1, list.size());

        // 可以自动扩容
        for (int i=0; i<150; i++) {
            list.add(i);
        }
        Assert.assertEquals(151, list.size());
        Assert.assertTrue(149 == ((Integer) list.get(150)).intValue());
    }

    @Test
    public void add1() throws Exception {
        for (int i=0; i<100; i++) {
            list.add(i);
        }
        list.add(0, "zero");
        list.add(50, "fifty");
        list.add(102, "102");
        Assert.assertEquals("zero", list.get(0));
        Assert.assertEquals("fifty", list.get(50));
        Assert.assertEquals("102", list.get(102));

        list = new ArrayList();
        for (int i=0; i<100; i++) {
            list.add(i);
        }
        list.add(100, "100");
        Assert.assertEquals("100", list.get(100));

        thrown.expect(ArrayIndexOutOfBoundsException.class);
        list.add(102, "102");
    }

    @Test
    public void get() throws Exception {
        list.add("hello");
        Object obj = list.get(0);
        Assert.assertTrue("hello".equals(obj));
    }

    @Test
    public void remove() throws Exception {
        for (int i=0; i<100; i++) {
            list.add(i);
        }
        Assert.assertEquals(99, ((Integer) list.remove(99)).intValue());
        Assert.assertEquals(99, list.size());

        thrown.expect(ArrayIndexOutOfBoundsException.class);
        list.remove(100);
        list.remove(-1);
    }

    @Test
    public void size() throws Exception {
        for (int i=0; i<100; i++) {
            list.add(i);
        }
        Assert.assertEquals(100, list.size());
        list.add("hello");
        Assert.assertEquals(101, list.size());
    }

    @Test
    public void iterator() throws Exception {
        for (int i=0; i<100; i++) {
            list.add(i);
        }

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Assert.assertNotNull(iterator.next());
        }
//        Assert.assertNotNull(iterator.next());

        Object[] target = new Object[list.size()];
        int i = 0;
        iterator = list.iterator();
        while (iterator.hasNext()) {
            target[i++] = iterator.next();
        }
        Assert.assertEquals(100, target.length);

        for (int j = 0; j < 100; j++) {
            Assert.assertEquals(j, ((Integer) target[j]).intValue());
        }

        // 测试迭代器的 remove() 方法
        list = new ArrayList();
        for (int k=0; k<100; k++) {
            list.add(k);
        }
        iterator = list.iterator();
//        thrown.expect(IllegalStateException.class);
//        iterator.remove();

        iterator.next();
        Object i0 = iterator.remove();
        Assert.assertEquals(0, ((Integer) i0).intValue());

        for (int j=0; j<50; j++) {
            iterator.next();
        }
        Object i50 = iterator.remove();
        Assert.assertEquals(50, ((Integer)i50).intValue());

        for (int j = 0; j < 48; j++) {
            iterator.next();
        }
        Object i99 = iterator.remove();
        Assert.assertEquals(98, ((Integer)i99).intValue());
    }

}