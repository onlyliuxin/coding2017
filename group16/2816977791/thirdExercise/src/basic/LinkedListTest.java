package basic;

import org.junit.Test;

/**
 * @author nvarchar
 *         date 2017/3/28
 */
public class LinkedListTest {

    @Test
    public void reverse() throws Exception {
        LinkedList list = new LinkedList();
        list.addLast(3);
        list.addLast(7);
        list.addLast(10);
        list.reverse();
        System.out.println();
    }

    @Test
    public void removeFirstHalf() throws Exception {
        LinkedList list = new LinkedList();
        list.addLast(2);
        list.addLast(5);
        list.addLast(7);
        list.addLast(8);
        list.addLast(10);
        list.removeFirstHalf();
        System.out.println();
    }

    @Test
    public void remove() throws Exception {
        LinkedList list = new LinkedList();
        list.addLast(2);
        list.addLast(5);
        list.addLast(7);
        list.addLast(8);
        list.addLast(10);
        list.remove(1, 2);
        System.out.println();
    }

    @Test
    public void getElements() throws Exception {
        LinkedList list = new LinkedList();
        list.addLast(11);
        list.addLast(101);
        list.addLast(201);
        list.addLast(301);
        list.addLast(401);
        list.addLast(501);
        list.addLast(601);
        list.addLast(701);

        LinkedList listB = new LinkedList();
        listB.addLast(1);
        listB.addLast(3);
        listB.addLast(4);
        listB.addLast(6);
        list.getElements(listB);

        System.out.println();
    }

    @Test
    public void subtract() throws Exception {
        LinkedList list = new LinkedList();
        list.addLast(11);
        list.addLast(101);
        list.addLast(201);
        list.addLast(301);
        list.addLast(401);
        list.addLast(501);
        list.addLast(601);
        list.addLast(701);

        LinkedList listB = new LinkedList();
        listB.addLast(11);
        listB.addLast(301);
        listB.addLast(401);
        listB.addLast(601);
        list.subtract(listB);

        System.out.println();
    }

    @Test
    public void removeDuplicateValues() throws Exception {
        LinkedList list = new LinkedList();
        list.addLast(11);
        list.addLast(101);
        list.addLast(101);
        list.addLast(101);
        list.addLast(101);
        list.addLast(201);
        list.addLast(301);
        list.addLast(301);
        list.addLast(401);
        list.addLast(401);
        list.addLast(501);
        list.addLast(601);
        list.addLast(601);
        list.addLast(701);
        list.removeDuplicateValues();
        System.out.println();
    }

    @Test
    public void removeRange() throws Exception {
        LinkedList list = new LinkedList();
        list.addLast(11);
        list.addLast(101);
        list.addLast(201);
        list.addLast(301);
        list.addLast(401);
        list.addLast(501);
        list.addLast(601);
        list.addLast(701);
        list.removeRange(200, 500);
        System.out.println();
    }

    @Test
    public void intersection() throws Exception {
        LinkedList list = new LinkedList();
        list.addLast(11);
        list.addLast(101);
        list.addLast(201);
        list.addLast(301);
        list.addLast(401);
        list.addLast(501);
        list.addLast(601);
        list.addLast(701);

        LinkedList listB = new LinkedList();
        listB.addLast(11);
        listB.addLast(301);
        listB.addLast(401);
        listB.addLast(601);
        listB.addLast(901);
        list.intersection(listB);
        System.out.println();
    }

}