package Test;

import com.coding.basic.LinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangwj on 2017/2/23.
 */
public class LinkedListTest {
    private LinkedList linkedlist = new LinkedList();

    @Before
    public void Init()
    {
        System.out.println("初始化");
        linkedlist.add(9.9);
        linkedlist.add(9.99);
    }


    @Test
    public void add() throws Exception {
        linkedlist.add(8.8);
        assertEquals(3, linkedlist.size());
        System.out.println("after add size is " + linkedlist.size());
        System.out.println("after add last element is " + linkedlist.get(linkedlist.size()));

    }

    @Test
    public void add1() throws Exception {
        linkedlist.add(2, 7.7);
        assertEquals(3, linkedlist.size());
        System.out.println("after add in 2th size is " + linkedlist.size());
        System.out.println("after add 2th element is " + linkedlist.get(2));
    }

    @Test
    public void get() throws Exception {
        assertEquals(9.9, linkedlist.get(1));
    }

    @Test
    public void remove() throws Exception {
        assertEquals(9.9, linkedlist.remove(1));
    }

    @Test
    public void size() throws Exception {
        assertEquals(2, linkedlist.size());

    }

    @Test
    public void addFirst() throws Exception {
        linkedlist.addFirst(3.3);
        assertEquals(3.3, linkedlist.get(1));
//        System.out.println();
    }

    @Test
    public void addLast() throws Exception {
        linkedlist.addLast(3.3);
        assertEquals(3.3, linkedlist.get(linkedlist.size()));

    }

    @Test
    public void removeFirst() throws Exception {
        assertEquals(9.9, linkedlist.removeFirst());
    }

    @Test
    public void removeLast() throws Exception {
        assertEquals(9.99, linkedlist.removeLast());
    }

}