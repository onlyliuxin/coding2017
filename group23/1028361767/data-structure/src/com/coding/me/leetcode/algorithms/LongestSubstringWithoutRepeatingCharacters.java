package com.coding.me.leetcode.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * 
 * Given "bbbbb", the answer is "b", with the length of 1.
 * 
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * @author hewj
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
	
	public int mySolution(String s) {
        if(s == null){
        	return 0;
        }else if(s.length() <= 1){
        	return s.length();
        }
        char[] chars = s.toCharArray();
        char[] tmp = new char[chars.length];
        int maxLength = 1;
        int length, k;
        for(int i=0;i<chars.length;i++){
        	length = 1;
        	k = 0;
        	tmp[k++] = chars[i];
        	end:
        	for(int j=i+1;j<chars.length;j++){
        		for(int l=0;l<k;l++){
        			if(tmp[l] == chars[j]){
        				break end;
        			}
        		}
        		tmp[k++] = chars[j];
        		length++;
        	}
        	if(maxLength < length){
        		maxLength = length;
        	}
        }
		return maxLength;
    }
	
	/**
	 * Time complexity : O(n^3)
	 * @param s
	 * @return
	 */
	public int leetCodeSolution1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
    
    /**
     * Sliding Window
	 * The naive approach is very straightforward. But it is too slow. So how
	 * can we optimize it?
	 * 
	 * In the naive approaches, we repeatedly check a substring to see if it has
	 * duplicate character. But it is unnecessary. If a substring S​ij ​​ from
	 * index i i to j 1 j−1 is already checked to have no duplicate characters.
	 * We only need to check if s[j] is already in the substring S​ij ​​ .
	 * 
	 * To check if a character is already in the substring, we can scan the
	 * substring, which leads to an O(n^2) algorithm. But we can do better.
	 * 
	 * By using HashSet as a sliding window, checking if a character in the
	 * current can be done in O 1 O(1).
	 * 
	 * A sliding window is an abstract concept commonly used in array/string
	 * problems. A window is a range of elements in the array/string which
	 * usually defined by the start and end indices, i.e. i j [i,j)
	 * (left-closed, right-open). A sliding window is a window "slides" its two
	 * boundaries to the certain direction. For example, if we slide i j [i,j)
	 * to the right by 1 1 element, then it becomes i 1 j 1 [i+1,j+1)
	 * (left-closed, right-open).
	 * 
	 * Back to our problem. We use HashSet to store the characters in current
	 * window i j [i,j) ( j i j=i initially). Then we slide the index j j to the
	 * right. If it is not in the HashSet, we slide j j further. Doing so until
	 * s[j] is already in the HashSet. At this point, we found the maximum size
	 * of substrings without duplicate characters start with index i i. If we do
	 * this for all i i, we get our answer.
	 * 
	 * Time complexity : O(2n)=O(n). In the worst case each character will be
	 * visited twice by i and j.
	 * 
	 * Space complexity : O(min(m,n)). Same as the previous approach. We need
	 * O(k) space for the sliding window, where k is the size of the Set. The
	 * size of the Set is upper bounded by the size of the string n n and the
	 * size of the charset/alphabet m m.
	 * 
	 * @param s
	 * @return
	 */
    public int leetcodeSolution2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
    
    /**
	 * Sliding Window Optimized 
	 * 
	 * The above solution requires at most 2n steps. In fact, it could be
	 * optimized to require only n steps. Instead of using a set to tell if a
	 * character exists or not, we could define a mapping of the characters to
	 * its index. Then we can skip the characters immediately when we found a
	 * repeated character.
	 * 
	 * The reason is that if s[j] have a duplicate in the range [i,j)
	 * with index j ​′ ​​ , we don't need to increase i little by little.
	 * We can skip all the elements in the range [i,j ​′ ​​ ] and let i i
	 * to be j ​′+1 directly.
	 * 
	 * @param s
	 * @return
	 */
    public int leetCodeSolution3(String s){
    	int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
    
	/**
	 * Java (Assuming ASCII 128)
	 * 
	 * The previous implements all have no assumption on the charset of the
	 * string s.
	 * 
	 * If we know that the charset is rather small, we can replace the Map with
	 * an integer array as direct access table.
	 * 
	 * Commonly used tables are:
	 * 
	 * int[26] for Letters 'a' - 'z' or 'A' - 'Z' 
	 * int[128] for ASCII 
	 * int[256] for Extended ASCII
	 * 
	 * @param s
	 * @return
	 */
    public int leetCodeSolution4(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
    
    
	private LongestSubstringWithoutRepeatingCharacters lswrc;
	@Before
	public void setup(){
		lswrc = new LongestSubstringWithoutRepeatingCharacters();
	}
	
	@Test
	public void testMySolution(){
		Assert.assertEquals(3, lswrc.mySolution("abcabcbb"));
		Assert.assertEquals(4, lswrc.mySolution("abcdabcdbbd"));
	}
	
	@Test
	public void testLeetcodeSolution3(){
		Assert.assertEquals(3, lswrc.leetCodeSolution3("abcabcbb"));
		Assert.assertEquals(4, lswrc.leetCodeSolution3("abcdabcdbbd"));
	}
	
	@Test
	public void testLeetcodeSolution4(){
		Assert.assertEquals(3, lswrc.leetCodeSolution4("abcabcbb"));
//		Assert.assertEquals(4, lswrc.leetCodeSolution4("abcdabcdbbd"));
	}
}
