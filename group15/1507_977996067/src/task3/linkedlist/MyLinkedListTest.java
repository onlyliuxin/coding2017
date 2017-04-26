package task3.linkedlist;

import org.junit.Test;

import java.util.Arrays;

public class MyLinkedListTest {

    @Test
    public void testReverse() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);
        list.add(7);
        list.add(10);
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }

    @Test
    public void testRemoveFirstHalf() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(10);
        System.out.println(list);
        list.removeFirstHalf();
        System.out.println(list);
    }

    @Test
    public void testRemove() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);
        list.add(7);
        list.add(10);
        System.out.println(list);
        list.remove(2, 1);
        System.out.println(list);
    }

    @Test
    public void testGetElements() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(11);
        list.add(101);
        list.add(201);
        list.add(301);
        list.add(401);
        list.add(501);
        list.add(601);
        list.add(701);

        MyLinkedList<Integer> listB = new MyLinkedList<>();
        listB.add(1);
        listB.add(3);
        listB.add(4);
        listB.add(6);
        Arrays.stream(list.getElements(listB)).forEach(System.out::println);
    }

    @Test
    public void testSubtract() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(11);
        list.add(101);
        list.add(201);
        list.add(301);
        list.add(401);
        list.add(501);
        list.add(601);
        list.add(701);

        MyLinkedList<Integer> listB = new MyLinkedList<>();
        listB.add(101);
        listB.add(501);
        listB.add(201);
        listB.add(601);
        list.subtract(listB);
        System.out.println(list);
    }

    @Test
    public void testRemoveDuplicateValues() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(11);
        list.add(11);
        list.add(201);
        list.add(301);
        list.add(401);
        list.add(401);
        list.add(601);
        list.add(601);
        list.removeDuplicateValues();
        System.out.println(list);
    }

    @Test
    public void testRemoveRange() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(11);
        list.add(101);
        list.add(201);
        list.add(301);
        list.add(401);
        list.add(501);
        list.add(601);
        list.add(701);
        list.removeRange(10,101);
        System.out.println(list);
    }

    @Test
    public void testIntersection() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(11);
        list.add(101);
        list.add(201);
        list.add(301);
        list.add(401);
        list.add(501);
        list.add(601);
        list.add(701);

        MyLinkedList<Integer> listB = new MyLinkedList<>();
        listB.add(101);
        listB.add(201);
        listB.add(501);
        listB.add(601);
        listB.add(701);
        System.out.println(list.intersection(listB));
    }
}
