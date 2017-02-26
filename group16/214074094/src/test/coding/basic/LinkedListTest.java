package coding.basic;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author shane
 * @Time 2017/2/25 23:32
 * @Email stevenchenguang@gmail.com
 * @Desc ...
 */
public class LinkedListTest extends AbstractTest {

    private static LinkedList list;

    @Before
    public void before() {
        list = new LinkedList();
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
    public void testAddIndex() {
        list.add(0, "after a");
        Assert.assertEquals("after a", list.get(1));

        list.add(3, "after c");
        Assert.assertEquals("after c", list.get(4));

        list.add(6, "after e");
        Assert.assertEquals("after e", list.get(7));
    }

    @Test
    public void testRemove() {
        list.remove(0);
        Assert.assertEquals("b", list.get(0));

        list.remove(list.size() - 1);
        Assert.assertEquals("d", list.get(list.size() - 1));

        Object obj = list.remove(1);
        Assert.assertEquals("c", obj);
        Assert.assertEquals(2, list.size());
    }
}
