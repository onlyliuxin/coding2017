package code01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yaoyuan on 2017/3/8.
 */
public class ArrayListTest {
    ArrayList arrayList;
    @Before
    public void setUp(){
        arrayList = new ArrayList();
    }

    @Test
    public void testAdd() throws Exception {

        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            arrayList.add(str);
        }

        // size()
        Assert.assertEquals(array.length,arrayList.size());

        //add(),get()
        for (int i = 0; i < arrayList.size(); i++){
            Assert.assertEquals(array[i],arrayList.get(i));
        }
    }

    @Test
    public void testAddWithIndex() throws Exception {
        ArrayList arrayList2 = new ArrayList(3);//自动扩容
        String[] array = new String[]{"a","b","c","d","e"};
        for (int i = 0; i < array.length; i++){
            arrayList2.add(i,array[i]);
        }
        //add(),get()
        for (int i = 0; i < arrayList2.size(); i++){
            Assert.assertEquals(array[i],arrayList2.get(i));
        }
        arrayList2.add(3,"new");
        Assert.assertEquals("new",arrayList2.get(3));


    }

    @Test
    public void testRemove() throws Exception {

        String[] array = new String[]{"a","b","c","d","e"};
        for (String str : array){
            arrayList.add(str);
        }
        arrayList.remove(0);
        arrayList.remove(0);


        for (int i = 0; i < arrayList.size(); i++) {
            Assert.assertEquals(array[i+2],arrayList.get(i));
        }

    }
}