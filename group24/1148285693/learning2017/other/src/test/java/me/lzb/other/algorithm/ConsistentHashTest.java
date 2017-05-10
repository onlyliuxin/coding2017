package me.lzb.other.algorithm;

import me.lzb.other.algorithm.consistenthash.ConsistentHash;
import me.lzb.other.algorithm.consistenthash.HashFunction;
import me.lzb.other.algorithm.consistenthash.PhysicalNode;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LZB
 * @date 2017/5/10
 */
public class ConsistentHashTest {


    @Test
    public void hashFunctionTest() {

        HashFunction hashFunction = new HashFunction();

        System.out.println(hashFunction.hash("192.168.1.100"));
        System.out.println(hashFunction.hash("192.168.1.101"));
        System.out.println(hashFunction.hash("192.168.1.102"));
        System.out.println(hashFunction.hash("192.168.1.103"));
        System.out.println(hashFunction.hash("192.168.1.104"));
    }

    @Test
    public void consistentHashTest() {
        int all = 100000;

        ConsistentHash ch10 = new ConsistentHash(10, 5);
        put(ch10, all);


        ConsistentHash ch12 = new ConsistentHash(12, 5);
        put(ch12, all);

        System.out.print("增加两台  ");
        hitRate(ch10.getPNodes(), ch12.getPNodes(), all);

        System.out.print("减少两台  ");
        ConsistentHash ch8 = new ConsistentHash(8, 5);
        put(ch8, all);

        hitRate(ch10.getPNodes(), ch8.getPNodes(), all);
    }


    public static void put(ConsistentHash ch, int all) {
        for (int i = 0; i < all; i++) {
            ch.put(String.valueOf(i), "aaaaa" + i);
        }
    }

    public static void hitRate(Map<String, PhysicalNode> m1, Map<String, PhysicalNode> m2, int all) {

        Map<String, PhysicalNode> s;
        Map<String, PhysicalNode> l;

        if (m1.size() < m2.size()) {
            s = m1;
            l = m2;
        } else {
            s = m2;
            l = m1;
        }

        int count = 0;

        for (String key : s.keySet()) {

            PhysicalNode sn = s.get(key);
            PhysicalNode ln = l.get(key);

            HashMap<String, String> sData = sn.getDate();

            HashMap<String, String> lData = ln.getDate();
            for (String k : sData.keySet()) {
                if (lData.containsKey(k)) {
                    count++;
                }
            }
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        System.out.println("hit rate : " + (nf.format((double) count / (double) all)));
    }


}
