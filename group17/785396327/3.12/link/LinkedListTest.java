package link;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by gongxun on 2017/3/13.
 */
public class LinkedListTest {
    private LinkedList<String> linkedList;

    @Before
    public void startUp() {
        linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add(1, "0");
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

    @Test
    public void add2() {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add(1, "0");
        System.out.println(linkedList);
    }

    @Test
    public void addLast() {
        linkedList.add("1");
        linkedList.addLast("2");
        System.out.println(linkedList);
    }

    @Test
    public void get() {
        String value = linkedList.get(2);
        System.out.println(value);
    }

    @Test
    public void remove() {
        String removeValue = linkedList.remove(3);
        System.out.println(removeValue);
    }

    @Test
    public void reverse() {
        System.out.println(linkedList);
        linkedList.reverse();
        System.out.println(linkedList);
    }

    @Test
    public void removeFirstHalf() {
        System.out.println(linkedList);
        linkedList.removeFirstHalf();
        System.out.println(linkedList);
    }

    @Test
    public void removeByRange() {
        System.out.println(linkedList);
        linkedList.remove(0, 2);
        System.out.println(linkedList);
    }

    @Test
    public void getElements() {
        System.out.println(linkedList);
        LinkedList<Integer> indexList = new LinkedList();
        indexList.add(0);
        indexList.add(2);
        indexList.add(3);
        Object[] elements = linkedList.getElements(indexList);
        System.out.println(Arrays.toString(elements));
    }
}
