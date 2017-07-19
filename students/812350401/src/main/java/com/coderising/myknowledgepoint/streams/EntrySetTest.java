package com.coderising.myknowledgepoint.streams;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by thomas_young on 20/7/2017.
 */
public class EntrySetTest {
    @Test
    public void test1() {
        System.out.println("----test1----");
        Map<String,String> map = new HashMap<>();
        map.put("01", "zhangsan");
        map.put("02", "lisi");
        map.put("03", "wangwu");
        Collection<String> collection = map.keySet();  // 返回值是个值的Collection集合
        collection.stream().forEach(key -> System.out.println(key + ": " + map.get(key)));
    }

    @Test
    public void test2() {
        System.out.println("----test2----");
        Map<String,String> map = new HashMap<>();
        map.put("01", "zhangsan");
        map.put("02", "lisi");
        map.put("03", "wangwu");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        entrySet.stream().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
