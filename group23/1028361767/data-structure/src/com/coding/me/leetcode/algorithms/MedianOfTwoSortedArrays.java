package com.coding.me.leetcode.algorithms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 * 
 * Example 1: nums1 = [1, 3] nums2 = [2]
 * 
 * The median is 2.0 Example 2: nums1 = [1, 2] nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 * 
 * @author hewj
 *
 */
public class MedianOfTwoSortedArrays {
	
	public double mySolution(int[] nums1, int[] nums2) {
		if(nums1.length == 0 || nums2.length == 0){
			return 0;
		}
		int[] nums = new int[nums1.length + nums2.length];
		int i=0,j=0;
		int a,b;
		int k=0;
		while(i<nums1.length && j<nums2.length){
			a = nums1[i];
			b=nums[j];
			if(a == b){
				nums[k++] = a;
				nums[k++] = a;
				i++;
				j++;
			}else if(a < b){
				nums[k++] = a;
				i++;
			}else{
				nums[k++] = b;
				j++;
			}
		}
		if(i < nums.length){
			nums[k++] = nums1[i++];
		}else if(j < nums.length){
			nums[k++] = nums2[j++];
		}
		int mid = k/2;
		int remain = k%2;
		if(remain == 0){
			return mid;
		}
		return (double)(nums[mid] + nums[mid+1])/2;
    }
	
	private MedianOfTwoSortedArrays motsa;
	
	@Before
	public void setup(){
		motsa = new MedianOfTwoSortedArrays();
	}
	
	@Test
	public void testMySolution(){
		int[] nums1 = {2};
		int[] nums2 = {1,3};
		System.out.println(motsa.mySolution(nums1, nums2));
	}
}
