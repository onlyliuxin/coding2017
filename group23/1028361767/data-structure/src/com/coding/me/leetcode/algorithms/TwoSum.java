package com.coding.me.leetcode.algorithms;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that
 * they add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you
 * may not use the same element twice.
 * 
 * Example: Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1]. Subscribe to see
 * which companies asked this question.
 */
public class TwoSum {
	/**
	 * Time complexity : O(n^2)
	 * Space complexity : O(1)
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] mySolution(int[] nums, int target) {
        int[] ret = new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=1;j<nums.length;j++){
                if(i != j && target == nums[i]+nums[j]){
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        }
        return ret;
    }
	
	/**
	 * two pass hash table
	 * Time complexity : O(n)
	 * Space complexity : O(n)
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] leetCodeSolution1(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement) && map.get(complement) != i) {
				return new int[] { i, map.get(complement) };
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}
	
	/**
	 * one pass hash table
	 * Time complexity : O(n)
	 * Space complexity : O(n)
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] leetCodeSolution2(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
	    for (int i = 0; i < nums.length; i++) {
	        int complement = target - nums[i];
	        if (map.containsKey(complement)) {
	            return new int[] { map.get(complement), i };
	        }
	        map.put(nums[i], i);
	    }
	    throw new IllegalArgumentException("No two sum solution");
	}
	
	
	public static void main(String[] args) throws InterruptedException {
//		String s = new String("字符串在内存中");
		List s = new ArrayList();
		s.add(new Object());
		WeakReference<Object> wr = new WeakReference<Object>(s.get(0));
		
//		Thread.sleep(500);
		System.out.println(wr.get());
		
		s.remove(0);
		System.gc();
		
		System.out.println(wr.get());
//		Thread.sleep(500);
//		System.out.println(wr.get());
	}
}
