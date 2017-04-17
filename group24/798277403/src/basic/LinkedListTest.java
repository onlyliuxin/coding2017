package basic;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhouliang on 2017-03-10.
 */
public class LinkedListTest {

    private LinkedList<Integer> myLinkedList = new LinkedList<>();

    private java.util.LinkedList<Integer> systemLinkedList = new java.util.LinkedList<>();

    @Before
    public void setUp(){
        for(int i=0; i<10; i++){
            myLinkedList.add(i);
            systemLinkedList.add(i);
        }
    }
    @Test
    public void add() throws Exception {
        for(int i=0; i<10; i++){
            System.out.println(myLinkedList.get(i));
        }
    }

    @Test
    public void remove() throws Exception {
        myLinkedList.remove(5);
        for(int i=0; i<myLinkedList.size(); i++){
            System.out.print(myLinkedList.get(i)+" ");
        }
        System.out.println(myLinkedList.size());

        systemLinkedList.remove(5);
        for(int i=0; i<systemLinkedList.size(); i++){
            System.out.print(systemLinkedList.get(i)+" ");
        }
        System.out.println(systemLinkedList.size());
    }

    @Test
    public void addFirst() throws Exception {
        myLinkedList.addFirst(111);
        for(int i=0; i<myLinkedList.size(); i++){
            System.out.print(myLinkedList.get(i)+" ");
        }
        System.out.println(myLinkedList.size());
    }

}