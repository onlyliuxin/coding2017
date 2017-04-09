package test.com.coding.basic;

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 *  LinkedList Test
 */
public class LinkedListTest {
    @Test
    public void add() throws Exception {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3,list.size());
    }

    @Test
    public void addByIndex() throws Exception {
        LinkedList list = new LinkedList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        assertEquals(3, list.get(0));
    }

    @Test
    public void get() throws Exception {
        LinkedList list = new LinkedList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        assertEquals(3,list.get(0));
    }

    @Test
    public void remove() throws Exception {
        LinkedList list = new LinkedList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        list.add(3,4);
        list.add(4,5);
        list.remove(0);
        System.out.println(list.size());
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public void size() throws Exception {
        LinkedList list = new LinkedList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        list.add(3,4);
        list.add(4,5);
        assertEquals(5, list.size());
    }

    @Test
    public void addFirst() throws Exception {
        LinkedList list = new LinkedList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        list.add(3,4);
        list.add(4,5);
        list.addFirst(0);
        System.out.println(list.size());
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public void addLast() throws Exception {
        LinkedList list = new LinkedList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        list.add(3,4);
        list.add(4,5);
        list.addLast(0);
        System.out.println(list.size());
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public void removeFirst() throws Exception {
        LinkedList list = new LinkedList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        list.add(3,4);
        list.add(4,5);
        list.removeFirst();
        System.out.println(list.size());
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public void removeLast() throws Exception {
        LinkedList list = new LinkedList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        list.add(3,4);
        list.add(4,5);
        list.removeLast();
        System.out.println(list.size());
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public void iterator() throws Exception {
        LinkedList list = new LinkedList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        list.add(3,4);
        list.add(4,5);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public void reverse(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.reverse();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public  void removeFirstHalf(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(6);
        list.removeFirstHalf();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public void removeByLength(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(6);
        list.remove(1,2);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public void getElements(){
        LinkedList list = new LinkedList();
        list.add(11);
        list.add(101);
        list.add(201);
        list.add(301);
        list.add(401);
        list.add(501);
        list.add(601);
        list.add(701);
        LinkedList index = new LinkedList();
        index.add(1);
        index.add(3);
        index.add(4);
        index.add(6);
        int[] arr = list.getElements(index);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public  void subtract(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(6);
        LinkedList l2 = new LinkedList();
        l2.add(1);
        l2.add(3);
        l2.add(4);
        l2.add(6);
        list.subtract(l2);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public  void removeDuplicateValues(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(6);
        list.add(8);
        list.add(10);
        list.removeDuplicateValues();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public  void removeRange(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(6);
        list.add(8);
        list.add(10);
        list.removeRange(3,9);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    @Test
    public void intersection(){
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(6);
        LinkedList l2 = new LinkedList();
        l2.add(1);
        l2.add(3);
        l2.add(4);
        l2.add(6);
        LinkedList l3 = list.intersection(l2);
        Iterator iterator = l3.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }
}