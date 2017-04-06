import com.coding.basic.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bshu on 2017/2/25.
 */
public class ArrayList_Test {
    @Test
    public void add_test() {
        ArrayList arr = new ArrayList();
        arr.add("one");
        assertEquals(arr.get(0),"one");
        arr.add("two");
        assertEquals(arr.get(1),"two");
    }

    @Test
    public void add_index_test() {
        ArrayList arr = new ArrayList();
        arr.add(0,"one");
        assertEquals(arr.get(0),"one");
        arr.add(1,"two");
        arr.add(2,"three");
        arr.add(3,"four");
        arr.add(4,"five");
        assertEquals(arr.get(4),"five");
        arr.add(2,"duplicte two");
        assertEquals(arr.get(2),"duplicte two");
    }

    @Test
    public void remove_test() {
        ArrayList arr = new ArrayList();
        arr.add(0,"one");
        arr.add(1,"two");
        arr.add(2,"three");
        arr.remove(1);
        assertEquals(arr.get(1),"three");
    }

    @Test
    public void get_test() {
        ArrayList arr = new ArrayList();
        arr.add(0,"one");
        arr.add(1,"two");
        arr.add(2,"three");
        assertEquals(arr.get(1),"two");
        assertEquals(arr.get(3),null);
    }
}