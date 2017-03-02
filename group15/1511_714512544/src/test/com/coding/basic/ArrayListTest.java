package test.com.coding.basic;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  ArrayList Test
 */
public class ArrayListTest {
    @Test
    public void add() throws Exception {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    public void addByIndex() throws Exception {
        ArrayList list = new ArrayList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        assertEquals(3,list.get(0));
    }

    @Test
    public void get() throws Exception {
        ArrayList list = new ArrayList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        assertEquals(3,list.get(0));
    }

    @Test
    public void remove() throws Exception {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(0);
        assertEquals(2, list.get(0));
    }

    @Test
    public void size() throws Exception {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(5, list.size());
    }

    @Test
    public void iterator() throws Exception {
        ArrayList list = new ArrayList();
        list.add(0,1);
        list.add(1,2);
        list.add(0,3);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

}