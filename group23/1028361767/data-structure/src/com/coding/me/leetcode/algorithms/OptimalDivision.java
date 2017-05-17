package com.coding.me.leetcode.algorithms;

/**
 * Given a list of positive integers, the adjacent integers will perform the
 * float division. For example, [2,3,4] -> 2 / 3 / 4.
 * 
 * However, you can add any number of parenthesis at any position to change the
 * priority of operations. You should find out how to add parenthesis to get the
 * maximum result, and return the corresponding expression in string format.
 * Your expression should NOT contain redundant parenthesis.
 * 
 * Example: Input: [1000,100,10,2] Output: "1000/(100/10/2)" Explanation:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200 However, the bold parenthesis in
 * "1000/((100/10)/2)" are redundant, since they don't influence the operation
 * priority. So you should return "1000/(100/10/2)".
 * 
 * Other cases: 1000/(100/10)/2 = 50 1000/(100/(10/2)) = 50 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2 Note:
 * 
 * The length of the input array is [1, 10]. Elements in the given array will be
 * in range [2, 1000]. There is only one optimal division for each test case.
 * 
 * @author hewj
 *
 */
public class OptimalDivision {
	
	public static String mySolution(int[] nums) {
        if(nums.length == 0 || nums.length > 10) {
        	return "";
        }
        if(nums.length == 1) {
        	return "" + nums[0];
        }
        straightInsertionSort(nums);
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/");
        if(nums.length == 2){
        	sb.append(nums[1]);
        }else{
        	sb.append("(");
        	for(int k=1;k<nums.length;k++){
        		sb.append(nums[k]);
        		if(k == nums.length - 1){
        			sb.append(")");
        		}else{
        			sb.append("/");
        		}
        	}
        }
		return sb.toString();
    }

	private static void straightInsertionSort(int[] nums) {
		for(int i=0;i<nums.length;i++){
        	int max = nums[i];
        	int pos = i;
        	for(int j=i+1;j<nums.length;j++){
        		if(max < nums[j]){
        			max = nums[j];
        			pos = j;
        		}
        	}
        	if(pos != i) {
        		int tmp = nums[i];
        		nums[i] = nums[pos];
        		nums[pos] = tmp;
        	}
        }
	}
	
	public static void main(String[] args) {
		int[] nums = {1000,100,2,10};
		mySolution(nums);
	}
}
