package com.coding.basic.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14258 on 2017/5/6.
 * 约瑟夫环问题：一圈共有N个人，开始报数，报到M的人自杀，然后重新开始报数，问最后自杀的人是谁？
 */
public class Josephus {

    public static List<Integer> execute(int n, int m){

        Queue<Integer> queue = new Queue< >();
        for (int i = 0; i < n; i++){
            queue.enQueue(i);
        }

        List<Integer> result = new ArrayList< >();
        int i = 0;

        while (!queue.isEmpty()) {

            int x = queue.deQueue();

            //来回循环
            if (++i % m == 0){
                result.add(x);
            } else{
                queue.enQueue(x);
            }
        }


        return result;
    }

    public static void main(String[] args) {
        System.out.println( Josephus.execute(41, 3));
    }
}
