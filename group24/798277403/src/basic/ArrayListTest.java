package basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhouliang on 2017-03-10.
 */
public class ArrayListTest {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    @Before
    public void setUp(){
        for(int i=0; i<100; i++){
            arrayList.add(i);
        }
    }

    @Test
    public void add() throws Exception {
        for(int i=100; i<1000; i++){
            arrayList.add(i);
        }
        Assert.assertEquals(1000,arrayList.size());
    }

    @Test
    public void add1() throws Exception {
        java.util.LinkedList l = new java.util.LinkedList();
    }

    @Test
    public void get() throws Exception {
        System.out.println(arrayList.get(99));
    }

    @Test
    public void remove() throws Exception {
        System.out.println(arrayList.size());
        arrayList.remove(arrayList.size()-1);
        System.out.println(arrayList.size());
        //Assert.assertEquals((Integer)99,(Integer)arrayList.size());
    }

    @Test
    public void iterator() throws Exception {
        Iterator iterator = arrayList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}