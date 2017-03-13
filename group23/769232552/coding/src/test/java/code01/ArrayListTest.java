package code01;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yaoyuan on 2017/3/8.
 */
public class ArrayListTest {

    @Test
    public void testAdd() throws Exception {

        ArrayList arrayList = new ArrayList();
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
        ArrayList arrayList = new ArrayList(3);//自动扩容
        String[] array = new String[]{"a","b","c","d","e"};
        for (int i = 0; i < array.length; i++){
            arrayList.add(i,array[i]);
        }
        //add(),get()
        for (int i = 0; i < arrayList.size(); i++){
            Assert.assertEquals(array[i],arrayList.get(i));
        }
        arrayList.add(3,"new");
        Assert.assertEquals("new",arrayList.get(3));


    }

    @Test
    public void testRemove() throws Exception {

        ArrayList arrayList = new ArrayList();
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