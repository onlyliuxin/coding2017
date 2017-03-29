package com.coding.me;

/**
 * 回文
 *
 */
public class Palindrome {
	
	private static String oddCase(char[] chars, int index) {
		int maxLength = 1;
		int i = 1;
		while((index - i) >= 0 && (index + i) < chars.length - 1){
			if(chars[index - i] != chars[index + i]){
				break;
			}
			maxLength += 2;
			i++;
		}
		return String.valueOf(chars, index + 1 - i, maxLength);
	}
	
	private static String evenCase(char[] chars, int index) {
		int maxLength = 0;
		int i = 0;
		while((index - i) >= 0 && (index + 1 + i) <= chars.length - 1){
			if(chars[index - i] != chars[index + 1 + i]){
				break;
			}
			i++;
			maxLength += 2;
		}
		return String.valueOf(chars, index + 1 - i, maxLength);
	}
	
	public static String findLongestPalindrome(String s){
		char[] chars = s.toCharArray();
		String longestPalindrome = "";
		String tmp = "";
		for(int i=0;i<chars.length;i++){
			tmp = oddCase(chars, i);
			if(longestPalindrome.length() < tmp.length()){
				longestPalindrome = tmp;
			}
			tmp = evenCase(chars, i);
			if(longestPalindrome.length() < tmp.length()){
				longestPalindrome = tmp;
			}
		}
		return longestPalindrome;
	}
	
	public static void main(String[] args) {
		System.out.println(findLongestPalindrome("abcbacda"));
		int[] nums = {3, 123, 78, 90};
		System.out.println(largestNumber(nums));
	}
	
	public static String largestNumber(int[] nums) {

        String[] array = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
        }

        String temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if ((array[i] + array[j]).compareTo(array[j] + array[i]) < 0) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] =temp;
                }
            }
        }
        
        if (array[0].equals("0")) {
            return "0";
        } else {
            return String.join("", array);
        }
    }
}
