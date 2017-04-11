package com.coding.me.leetcode.algorithms;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * ["a", "b"] return ""
 * ["a", "a"] return "a"
 * ["ac", "ac", "a", "a"] return "a"
 *
 */
public class LongestCommonPrefix {
	
	public static String mySolution(String[] strs){
		if(strs.length == 0) return "";
		char[] chars = strs[0].toCharArray();
		int count = chars.length;
		if(count == 0) return "";
		char[] tmp;
		for(int i = 1;i<strs.length;i++){
			tmp = strs[i].toCharArray();
			if(tmp.length == 0) return "";
			for(int j=0;j<tmp.length && j<chars.length;j++){
				if(chars[j] == tmp[j]) {
					if(j != tmp.length - 1) continue;
					else{
						if(++j < count) count = j;
						break;
					}
				}
				if(j < count) count = j;
				break;
			}
		}
		return strs[0].substring(0, count);
	}
	
	/**
	 * Time complexity:O(S),where S is the sum of all characters in all strings.
	 * @param strs
	 * @return
	 */
	public static String leetcodeSolution1(String[] strs) {
	    if (strs.length == 0) return "";
	    String prefix = strs[0];
	    for (int i = 1; i < strs.length; i++)
	        while (strs[i].indexOf(prefix) != 0) {
	            prefix = prefix.substring(0, prefix.length() - 1);
	            if (prefix.isEmpty()) return "";
	        }        
	    return prefix;
	}
	
	/**
	 * Time complexity:O(S),where S is the sum of all characters in all strings.
	 * @param strs
	 * @return
	 */
	public String leetcodeSolution2(String[] strs) {
	    if (strs == null || strs.length == 0) return "";
	    for (int i = 0; i < strs[0].length() ; i++){
	        char c = strs[0].charAt(i);
	        for (int j = 1; j < strs.length; j ++) {
	            if (i == strs[j].length() || strs[j].charAt(i) != c)
	                return strs[0].substring(0, i);             
	        }
	    }
	    return strs[0];
	}
	
	public String leetcodeSolution3(String[] strs) {
	    if (strs == null || strs.length == 0) return "";    
	        return longestCommonPrefix(strs, 0 , strs.length - 1);
	}
	
	private int i=1;
	private int j=1;

	private String longestCommonPrefix(String[] strs, int l, int r) {
		System.out.println("第" + i++ + "次进入longestCommonPrefix;l = " + l + ",r = " + r);
	    if (l == r) {
	        return strs[l];
	    }
	    else {
	        int mid = (l + r)/2;
	        String lcpLeft =   longestCommonPrefix(strs, l , mid);
	        String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
	        return commonPrefix(lcpLeft, lcpRight);
	   }
	}

	private String commonPrefix(String left,String right) {
		System.out.println("第" + j++ + "次进入longestCommonPrefix;left = " + left + ",right = " + right);
	    int min = Math.min(left.length(), right.length());       
	    for (int i = 0; i < min; i++) {
	        if ( left.charAt(i) != right.charAt(i) )
	            return left.substring(0, i);
	    }
	    return left.substring(0, min);
	}
	
	/**
	 * Time Complexity O(S*log(n))
	 * Space Complexity O(1)
	 * @param strs
	 * @return
	 */
	public String leetcodeSolution4(String[] strs) {
	    if (strs == null || strs.length == 0)
	        return "";
	    int minLen = Integer.MAX_VALUE;
	    for (String str : strs)
	        minLen = Math.min(minLen, str.length());
	    int low = 1;
	    int high = minLen;
	    while (low <= high) {
	        int middle = (low + high) / 2;
	        if (isCommonPrefix(strs, middle))
	            low = middle + 1;
	        else
	            high = middle - 1;
	    }
	    return strs[0].substring(0, (low + high) / 2);
	}

	private boolean isCommonPrefix(String[] strs, int len){
	    String str1 = strs[0].substring(0,len);
	    for (int i = 1; i < strs.length; i++)
	        if (!strs[i].startsWith(str1))
	            return false;
	    return true;
	}
	
	public String longestCommonPrefix(String q, String[] strs) {
	    if (strs == null || strs.length == 0)
	         return "";  
	    if (strs.length == 1)
	         return strs[0];
	    Trie trie = new Trie();      
	    for (int i = 1; i < strs.length ; i++) {
	        trie.insert(strs[i]);
	    }
	    return trie.searchLongestPrefix(q);
	}

	class TrieNode {

	    // R links to node children
	    private TrieNode[] links;

	    private final int R = 26;

	    private boolean isEnd;

	    public TrieNode() {
	        links = new TrieNode[R];
	    }

	    public boolean containsKey(char ch) {
	        return links[ch -'a'] != null;
	    }
	    public TrieNode get(char ch) {
	        return links[ch -'a'];
	    }
	    public void put(char ch, TrieNode node) {
	        links[ch -'a'] = node;
	    }
	    public void setEnd() {
	        isEnd = true;
	    }
	    public boolean isEnd() {
	        return isEnd;
	    }
	    
	    public int getLinks(){
	    	return links.length;
	    }
	}

	public class Trie {

	    private TrieNode root;

	    public Trie() {
	        root = new TrieNode();
	    }
	    
	 // Inserts a word into the trie.
	    public void insert(String word) {
	        TrieNode node = root;
	        for (int i = 0; i < word.length(); i++) {
	            char currentChar = word.charAt(i);
	            if (!node.containsKey(currentChar)) {
	                node.put(currentChar, new TrieNode());
	            }
	            node = node.get(currentChar);
	        }
	        node.setEnd();
	    }

	//assume methods insert, search, searchPrefix are implemented as it is described
	//in  https://leetcode.com/articles/implement-trie-prefix-tree/)
	    private String searchLongestPrefix(String word) {
	        TrieNode node = root;
	        StringBuilder prefix = new StringBuilder();
	        for (int i = 0; i < word.length(); i++) {
	            char curLetter = word.charAt(i);
	            if (node.containsKey(curLetter) && (node.getLinks() == 1) && (!node.isEnd())) {
	                prefix.append(curLetter);
	                node = node.get(curLetter);
	            }
	            else
	                return prefix.toString();

	         }
	         return prefix.toString();
	    }
	}
	
	public static void main(String[] args) {
		String[] strs = {"abca", "abac", "abdca", "abcac", "abba", "abab"};
		System.out.println(leetcodeSolution1(strs));
		System.out.println(new LongestCommonPrefix().leetcodeSolution3(strs));
	}
}
