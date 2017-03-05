package org.apn.coding2017;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QiPan on 2017/2/23.
 */
public class TestJavaUtilArrayList {


    @Test
    public void testAdd() {
        List arrayList = new ArrayList(5);
        arrayList.add(new Object());
        System.out.println("sssssssssssss");
    }

    @Test
    public void testRightShift() {
        int x = 5;

        x = x << 1;
        x = x >> 1;
        System.out.println(x);
    }

}
