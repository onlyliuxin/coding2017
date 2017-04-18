package data_structure;

import basic.dataStructure.LRULinkedList;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : 温友朝
 * @date : 2017/4/18
 */
public class LRUTest {

    private LRULinkedList lru;

    @Before
    public void init(){
        lru = new LRULinkedList();
    }

    @Test
    public void testAdd(){
        lru.add(1);
//        lru.add(2);
//        lru.add(3);
//        lru.add(4);
//        lru.add(5);
//        lru.add(6);
        System.out.println(lru.toString());
    }
}
