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
//        System.out.println(linkedList);
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
        linkedList.reverse();
        System.out.println(linkedList);
    }

    @Test
    public void removeFirstHalf() {
        linkedList.removeFirstHalf();
        System.out.println(linkedList);
    }

    @Test
    public void removeByRange() {
        linkedList.remove(0, 2);
        System.out.println(linkedList);
    }

    @Test
    public void getElements() {
        LinkedList<Integer> indexList = new LinkedList();
        indexList.add(0);
        indexList.add(2);
        indexList.add(3);
        Object[] elements = linkedList.getElements(indexList);
        System.out.println(Arrays.toString(elements));
    }

    @Test
    public void subtract() {
        LinkedList<String> indexList = new LinkedList<String>();
        indexList.add("2");
        indexList.add("0");
        linkedList.subtract(indexList);
        System.out.println(linkedList);
    }

    @Test
    public void removeDuplicateValues() {
        linkedList.add("3");
        linkedList.add("7");
        linkedList.add("0");
        linkedList.add("1");
        System.out.println(linkedList);
        linkedList.removeDuplicateValues();
        System.out.println(linkedList);
    }

    @Test
    public void removeRange() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(7);
        System.out.println(list);
        list.removeRange(1, 6);
        System.out.println(list);
    }

    @Test
    public void intersection() {
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        list1.add(2);
        list1.add(3);
        list1.add(7);
        LinkedList<Integer> list2 = new LinkedList<Integer>();
        list2.add(2);
        list2.add(3);
        list2.add(7);
        LinkedList newList = list1.intersection(list2);
        System.out.println(newList);
    }

}
