import org.junit.Test;
import com.coding.basic.*;
import java.lang.Object;

import static org.junit.Assert.assertEquals;

/**
 * LinkedList_Test Tester.
 *
 * @author <Authors name>
 * @since <pre>bshu 26, 2017</pre>
 * @version 1.0
 */
public class LinkedList_Test {
    @Test
    public void get() {
        LinkedList link = new LinkedList();
        link.add("kkk");
        assertEquals(link.get(0),"kkk");
        link.add("kkk1");
        assertEquals(link.get(1),"kkk1");
    }

    @Test
    public void add() {
        LinkedList link = new LinkedList();
        link.add("kkk");
        link.add("aaa");
        link.add("bbb");
        link.add("ccc");
        link.add("ddd");
        link.add("eee");
        Object[] ob = new Object[]{"kkk","aaa","bbb","ccc","ddd","eee"};
        int count = 0;
        for (Iterator iter = link.iterator(); iter.hasNext();) {
            Object data = iter.next();
//            System.out.printf("%s is :%s \n",count, data);
            assertEquals(data,ob[count]);
            ++count;
        }
    }

    @Test
    public void add_index() {
        LinkedList link = new LinkedList();
        link.add("kkk");
//        System.out.println("0 is " + link.get(0));
        link.add("kkk2");
        link.add(0,"0kkk");
        link.add(3,"3kkk");
        assertEquals(link.get(0),"0kkk");
        assertEquals(link.get(2),"kkk2");
    }

    @Test
    public void add_first() {
        LinkedList link = new LinkedList();
        link.add("kkk");
        link.addFirst("F_kkk");
        assertEquals(link.get(0),"F_kkk");
        assertEquals(link.get(1),"kkk");
    }

    @Test
    public void add_last() {
        LinkedList link = new LinkedList();
        link.add("kkk");
        link.addLast("L_kkk");
        assertEquals(link.get(0),"kkk");
        assertEquals(link.get(link.size() - 1),"L_kkk");
    }

    @Test
    public void remove() {
        LinkedList link = new LinkedList();
        link.add("kkk");
//        System.out.println("0 is " + link.get(0));
        link.add("kkk1");
        link.add("kkk2");
        link.remove(2);
        link.add(link.size(),"2kkk");
        link.add("kkk3");
        link.add("kkk4");
        link.add("kkk5");
        link.remove(link.size());
        link.add("kkk6");
        int count = 0;
        Object[] ob = new Object[]{"kkk","kkk1","2kkk","kkk3","kkk4","kkk6"};
        for (Iterator iter = link.iterator(); iter.hasNext();) {
            Object data = iter.next();
            System.out.printf("%s is :%s \n",count, data);
            assertEquals(data,ob[count]);
            ++count;
        }
    }

    @Test
    public void remove_first() {
        LinkedList link = new LinkedList();
        link.add("kkk");
        link.add("kkk1");
        link.add("kkk2");
        link.removeFirst();
        assertEquals("kkk1",link.get(0));
        assertEquals("kkk2",link.get(1));
        assertEquals(2,link.size());
    }

    @Test
    public void remove_last() {
        LinkedList link = new LinkedList();
        link.add("kkk");
        link.add("kkk1");
        link.removeLast();
        assertEquals("kkk",link.get(0));
        link.removeLast();
        link.removeLast();
        link.remove(0);
        link.remove(0);
        assertEquals(null,link.get(0));
    }
}
