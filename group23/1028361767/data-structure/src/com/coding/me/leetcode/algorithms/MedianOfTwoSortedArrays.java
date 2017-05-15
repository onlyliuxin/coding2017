package com.coding.me.leetcode.algorithms;

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
		int[] nums = new int[nums1.length + nums2.length];
		int mid = nums.length/2;
		int remain = nums.length%2;
		int i=0,j=0;
		int a,b;
		int k=0;
		while(i<nums1.length || j<nums2.length && k<mid){
			if(i == nums1.length){
				nums[k++] = nums2[j++];
				continue;
			}else if(j == nums2.length){
				nums[k++] = nums1[i++];
				continue;
			}
			a = nums1[i];
			b = nums2[j];
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
		if(remain != 0){
			return nums[mid];
		}
		return (nums[mid] + nums[mid-1])/2.0;
    }
	
	public static double otherSolution(int[] nums1, int[] nums2) {
		int len1 = nums1.length, len2 = nums2.length, sumLen = len1 + len2;
		if (sumLen % 2 != 0) {

			return findKthSmallest(nums1, len1, 0, nums2, len2, 0, sumLen / 2 + 1);
		} else {
			return (findKthSmallest(nums1, len1, 0, nums2, len2, 0, sumLen / 2)
					+ findKthSmallest(nums1, len1, 0, nums2, len2, 0, sumLen / 2 + 1)) / 2.0;
		}

	}
	
	public static int findKthSmallest(int[] a, int m, int begin1, int[] b, int n, int begin2, int k) {

		if (m > n)
			return findKthSmallest(b, n, begin2, a, m, begin1, k);
		if (m == 0)
			return b[begin2 + k - 1];
		if (k == 1)
			return Integer.min(a[begin1], b[begin2]);
		int partA = Integer.min(k / 2, m), partB = k - partA;
		if (a[begin1 + partA - 1] == b[begin2 + partB - 1])
			return a[begin1 + partA - 1];
		else if (a[begin1 + partA - 1] > b[begin2 + partB - 1])
			return findKthSmallest(a, m, begin1, b, n - partB, begin2 + partB, k - partB);
		else
			return findKthSmallest(a, m - partA, begin1 + partA, b, n, begin2, k - partA);

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
