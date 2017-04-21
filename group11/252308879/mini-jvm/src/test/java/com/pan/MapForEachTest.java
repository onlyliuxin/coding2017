package com.pan;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QiPan on 2017/4/19.
 */
public class MapForEachTest {

    @Test
    public void  testForEach(){

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 11);
        map.put(2, 22);
        map.put(3, 33);

        Map<Integer, String> map2 = new HashMap<>();
        map.forEach((key, value) -> map2.put(key, value.toString()));

        System.out.println(map2);



    }

}
