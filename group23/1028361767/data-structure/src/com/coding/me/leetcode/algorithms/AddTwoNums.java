package com.coding.me.leetcode.algorithms;

public class AddTwoNums {
	
	public ListNode mySolution(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode ret = null;
        ListNode first = null;
        while(l1 != null || l2 != null){
        	int i = l1 == null?0 : l1.val;
        	int j = l2 == null?0 : l2.val;
        	int sum = i + j + carry;
        	if(sum >= 10){
        		carry = 1;
        		sum -= 10;
        	}else{
        		carry = 0;
        	}
        	if(ret == null){
        		ret = new ListNode(sum);
        		first = ret;
        	}else{
        		ret.next = new ListNode(sum);
        		ret = ret.next;
        	}
        	if(l1 != null){
        		l1 = l1.next;
        	}
        	if(l2 != null){
        		l2 = l2.next;
        	}
        }
        if(carry > 0){
        	ret.next = new ListNode(carry);
        }
		return first;
    }
	
	public ListNode leetcodeSolution(ListNode l1, ListNode l2) {
	    ListNode dummyHead = new ListNode(0);
	    ListNode p = l1, q = l2, curr = dummyHead;
	    int carry = 0;
	    while (p != null || q != null) {
	        int x = (p != null) ? p.val : 0;
	        int y = (q != null) ? q.val : 0;
	        int sum = carry + x + y;
	        carry = sum / 10;
	        curr.next = new ListNode(sum % 10);
	        curr = curr.next;
	        if (p != null) p = p.next;
	        if (q != null) q = q.next;
	    }
	    if (carry > 0) {
	        curr.next = new ListNode(carry);
	    }
	    return dummyHead.next;
	}
	
	public static void main(String[] args) {
		ListNode l11 = new ListNode(2);
		ListNode l12 = new ListNode(4);
		ListNode l13 = new ListNode(3);
		l11.next = l12;
		l12.next = l13;
		
		ListNode l21 = new ListNode(5);
		ListNode l22 = new ListNode(6);
		ListNode l23 = new ListNode(4);
		l21.next = l22;
		l22.next = l23;
		
		ListNode ret = new AddTwoNums().mySolution(l11, l21);
		while(ret != null){
			System.out.println(ret.val);
			ret = ret.next;
		}
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
