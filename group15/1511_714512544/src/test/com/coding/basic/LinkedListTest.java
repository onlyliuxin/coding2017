package test.com.coding.basic;

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import org.junit.Test;

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

}