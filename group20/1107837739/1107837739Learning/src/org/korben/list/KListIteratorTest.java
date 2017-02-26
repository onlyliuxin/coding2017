package org.korben.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Iterator测试
 *
 * Created by Korben on 24/02/2017.
 */
public class KListIteratorTest {

    private KList<Integer> list;

    @Before
    public void init() {
        this.list = new KArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
    }

    @Test
    public void testIterator() {
        KIterator<Integer> iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        int count = 0;
        while (iterator.hasNext()) {
            int value = iterator.next();
            Assert.assertEquals(count, value);
            count++;
        }
    }
}
