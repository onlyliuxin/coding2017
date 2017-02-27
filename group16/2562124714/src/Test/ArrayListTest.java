package Test;

import com.coding.basic.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangwj on 2017/2/23.
 */
public class ArrayListTest {
     private static ArrayList arraylist = new ArrayList();
    @BeforeClass
    public static void setUp() throws Exception {

        System.out.println("初始化变量");
        for (Integer i = 0; i < 5; i++)
        {
            arraylist.add(i);
        }


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void add() throws Exception {
        Integer i = arraylist.size();
        Integer AddElement = 999;
        arraylist.add(AddElement);
        assertEquals(i + 1, arraylist.size());
        assertEquals(AddElement, arraylist.get(arraylist.size()));
        arraylist.remove(arraylist.size());
    }

    @Test
    public void add1() throws Exception {
        Integer AddElement = 999;
        arraylist.add(1, AddElement);
        assertEquals(AddElement, arraylist.get(1));
        arraylist.remove(1);
    }

    @Test
    public void get() throws Exception {
            assertEquals(null, arraylist.get(9999));
    }

    @Test
    public void remove() throws Exception {
        Integer i = (Integer)arraylist.get(1);
        assertEquals(i, arraylist.remove(1));
        arraylist.add(1, i);

    }

    @Test
    public void size() throws Exception {
            assertEquals(5, arraylist.size());

    }

}