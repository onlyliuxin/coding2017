package link;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gongxun on 2017/3/13.
 */
public class LinkedListTest {
    private LinkedList<String> linkedList;

    @Before
    public void startUp() {
        linkedList = new LinkedList();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void addFirst() {
        linkedList.addFirst("1");
        System.out.println(linkedList);
    }

    @Test
    public void add() {
        linkedList.add("1");
        linkedList.add("2");
        System.out.println(linkedList);
    }
}
