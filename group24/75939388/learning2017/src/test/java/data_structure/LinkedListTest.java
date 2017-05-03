package data_structure;

import basic.dataStructure.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by macvi on 2017/4/3.
 */
public class LinkedListTest {

    LinkedList ll = new LinkedList();

    @Before
    public void init(){
        for(int i = 0; i < 10; i++){
            ll.add(i);
        }
    }

    @After
    public void print(){

    }


    @Test
    public void testLinkedListAdd(){
       LinkedList ll = new LinkedList();
       ll.add("123");
       ll.add("456");
       ll.add("asdf");
       ll.add("zxcv");


       System.out.println("ll.toString-->" + ll);
       System.out.println("ll.size--->" + ll.size());
    }

    @Test
    public void testLinkedListIndexAdd(){
        System.out.println("12345");
    }

    @Test
    public void testGet(){
        LinkedList ll = new LinkedList();
        for(int i = 0; i < 10; i ++){
            ll.add(i + "");
        }

        System.out.println("get-->" + ll.get(9));
        System.out.println("ll.toString-->" + ll.toString() + "\nsize-->" + ll.size());
    }

    @Test
    public void testIndexAdd(){
        LinkedList ll = new LinkedList();
        for(int i = 0; i < 5; i ++){
            ll.add(i + "");
        }

        ll.add(5, "xxoo");
        System.out.println("index get-->" + ll.get(0));
        System.out.println("ll.toString2-->" + ll.toString() + "\nsize-->" + ll.size());
    }

    @Test
    public void testRemove(){
        LinkedList ll = new LinkedList();
        for(int i = 0; i < 6; i ++){
            ll.add(i + "");
        }

        Object removed = ll.remove(-1);
        System.out.println("ll.toString-->" + ll.toString() + "\nsize-->" + ll.size());
        System.out.println("removed-->" + removed.toString());
    }

    @Test
    public void testReverse(){
        ll.reverse();
        System.out.println("ll.reverse-->" + ll.toString());
    }

    @Test
    public void testRemoveFirstHalf(){
        ll.removeFirstHalf();
        System.out.println("ll.removeFirstHalf-->" + ll.toString());
    }

    @Test
    public void testRemoveL(){
        ll.remove(2, 5);
        System.out.println("ll.toString-->" + ll.toString());
    }

    @Test
    public void testGetElements(){
        LinkedList l2 = new LinkedList();
        l2.add(3);
        l2.add(5);
        l2.add(9);
        l2.add(0);

        int[] arr = ll.getElements(l2);
        System.out.println("arr->" + Arrays.toString(arr));
    }

    @Test
    public void testRemoveDuplicate(){
        ll.add(1);
        ll.add(3);
        ll.add(4);
        ll.add(10);
        ll.add(11);
        ll.removeDuplicateValues();
        System.out.println("ll.toString-->" + ll.toString());
    }

    @Test
    public void testRemoveRange(){
        ll.removeRange(2, 6);
        System.out.println("ll.toString-->" + ll.toString());
    }

    @Test
    public void testSubtract(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(5);

        ll.subtract(list);
        System.out.println("ll.toString-->" + ll);
    }

    @Test
    public void testIntersection(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(5);

        LinkedList list2 = ll.intersection(list);
        System.out.println(list2);
    }
}
