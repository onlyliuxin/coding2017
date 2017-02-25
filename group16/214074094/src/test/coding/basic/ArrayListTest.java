package coding.basic;

import com.coding.basic.ArrayList;
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
        printStar();
    }

    @After
    public void after() {
        printStar();
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
    public void testSize(){
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void testRemove() {
        list.remove(5);
        Assert.assertEquals(list.get(3), "d");
    }

}
