package coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author shane
 * @Time 2017/2/25 13:02
 * @Email stevenchenguang@gmail.com
 * @Desc ...
 */

public class ArrayListTest extends AbstractTest {

    private static ArrayList list;

    @Before
    public void before() {

        list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        printStar();
        System.out.println("Before Test data :" + list);
        printHyphen();
    }

    @After
    public void after() {
        printHyphen();
        System.out.println("After Test data : " + list);
        printStar();
    }

    @Test
    public void testAddI() {
        int index = list.size();
        list.add(index, "test add i");
        Assert.assertEquals(list.get(index), "test add i");
    }

    @Test
    public void test() {
        java.util.ArrayList<String> list = new java.util.ArrayList<String>();
        list.add("a");
        list.add("b");
        java.util.Iterator<String> it = list.iterator();
        while (it.hasNext()) {

        }
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void testRemove() {
        list.remove(5);
        Assert.assertEquals(list.get(3), "d");
    }

    @Test
    public void testIterator() {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
