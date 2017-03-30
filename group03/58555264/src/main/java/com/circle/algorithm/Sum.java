package com.circle.algorithm;

import java.util.*;

/**
 * Created by keweiyang on 2017/3/1.
 * Problem:
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 * For example, given array S = {-1 0 1 2 -1 -4},
 * A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 */
public class Sum {

    public void sum(int[] as, int target) {
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < as.length; i++) {
            for (int j = i + 1; j < as.length; j++) {
                int k = as.length - 1;


                while (target != as[i] + as[j] + as[k]) {
                    if (target < as[i] + as[j] + as[k]) {
                        k--;
                        if (j > k) {
                            break;
                        }
                    } else if (target > as[i] + as[j] + as[k]) {
                        break;
                    }
                }

                if (target == as[i] + as[j] + as[k]) {
                    Integer[] arr = new Integer[3];
                    arr[0] = as[i];
                    arr[1] = as[j];
                    arr[2] = as[k];
                    list.add(Arrays.asList(arr));
                }



            }
        }

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }

    public static void main(String[] args) {
        Sum sum = new Sum();
        int[] as = new int[]{-1, 0, 1, 2, -1, -4};
        Arrays.sort(as);

        sum.sum(as, 0);

    }
}
