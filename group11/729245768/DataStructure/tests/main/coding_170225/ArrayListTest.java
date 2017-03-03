package main.coding_170225;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by peter on 2017/2/25.
 */
public class ArrayListTest extends TestCase {
    @Test
    public void add() throws Exception {
    }

    @Test
    public void add1() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void testSize() throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add(12);
        arrayList.add(15);
        arrayList.add(17);
        Assert.assertEquals(3,arrayList.size());
    }

}