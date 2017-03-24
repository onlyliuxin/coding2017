package code01;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yaoyuan on 2017/3/8.
 */
public class LinkedListTest {

    @Test
    public void testAdd() throws Exception {

        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            linklist.add(str);
        }

        // size()
        Assert.assertEquals(array.length,linklist.size());

        //add(),get()
        for (int i = 0; i < linklist.size(); i++){
            Assert.assertEquals(array[i],linklist.get(i));
        }

    }

    @Test
    public void testAddWithIndex() throws Exception {
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            linklist.add(str);
        }

        //add(),get()
        for (int i = 0; i < linklist.size(); i++){
            Assert.assertEquals(array[i],linklist.get(i));
        }

        String str = "new";
        linklist.add(0,str);
        Assert.assertEquals(str,linklist.get(0));

        linklist.add(3,str);
        Assert.assertEquals(str,linklist.get(3));

        linklist.add(linklist.size() ,str);
        Assert.assertEquals(str,linklist.get(linklist.size()-1));
    }

    @Test
    public void testRemove() throws Exception {
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            linklist.add(str);
        }

        //remove(),get()
        Assert.assertEquals(linklist.remove(0),array[0]);
        Assert.assertEquals(linklist.size(),array.length - 1);

        Assert.assertEquals(linklist.remove(linklist.size() - 1),array[array.length-1]);
        Assert.assertEquals(linklist.size(),array.length - 2);

    }

    @Test
    public void testAddFirst() throws Exception {
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            linklist.add(str);
        }
        //addFirst(),get()
        String str = "new";
        linklist.addFirst(str);
        Assert.assertEquals(str,linklist.get(0));
        Assert.assertEquals(linklist.size(),array.length + 1);
    }

    @Test
    public void testAddLast() throws Exception {
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            linklist.add(str);
        }
        //addLast(),get()
        String str = "new";
        linklist.addLast(str);
        Assert.assertEquals(str,linklist.get(linklist.size()-1));
        Assert.assertEquals(linklist.size(),array.length + 1);
    }

    @Test
    public void testRemoveFirst() throws Exception {
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            linklist.add(str);
        }
        //removeFirst(),get()
        Assert.assertEquals(linklist.removeFirst(),array[0]);
        Assert.assertEquals(linklist.size(),array.length - 1);
    }

    @Test
    public void testRemoveLast() throws Exception {
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            linklist.add(str);
        }
        //removeLast(),get()
        Assert.assertEquals(linklist.removeLast(),array[array.length-1]);
        Assert.assertEquals(linklist.size(),array.length - 1);

    }

    @Test
    public void testReverse(){
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            linklist.add(str);
        }
        linklist.reverse();
        for(int i=0; i<array.length; i++){
            Assert.assertEquals(array[array.length - i-1], linklist.get(i));
        }
    }

    @Test
    public void testremoveFirstHalf(){
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            linklist.add(str);
        }
        linklist.removeFirstHalf();
        for(int i=0; i<array.length/2; i++){
            Assert.assertEquals(array[i+array.length/2], linklist.get(i));
        }
    }

    @Test
    public void testRemoveDuplicateValues() throws Exception {
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","b","b","d","d","d"};
        for (String str : array){
            linklist.add(str);
        }
        linklist.printList();
        linklist.removeDuplicateValues();
        linklist.printList();

    }

    @Test
    public void testRemove1() throws Exception {
        LinkedList linklist = new LinkedList();
        String[] array = new String[]{"a","b","c","d","e","f","g"};
        for (String str : array){
            linklist.add(str);
        }
        linklist.printList();
        linklist.remove(0,4);
        linklist.printList();
    }
}