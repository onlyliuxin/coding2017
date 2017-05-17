package com.coding.me.leetcode.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a string representing an attendance record for a student. The
 * record only contains the following three characters:
 * 
 * 'A' : Absent. 'L' : Late. 'P' : Present. A student could be rewarded if his
 * attendance record doesn't contain more than one 'A' (absent) or more than two
 * continuous 'L' (late).
 * 
 * You need to return whether the student could be rewarded according to his
 * attendance record.
 * 
 * Example 1: Input: "PPALLP" Output: True Example 2: Input: "PPALLL" Output:
 * False
 */
public class StudentAttendanceRecord1 {
	
	public static boolean mySolution(String s) {
		char[] cs = s.toCharArray();
		int aCount = 0;
		List<Character> lList = new ArrayList<>();
		for(int i=0;i<cs.length;i++){
			if(cs[i] == 65){
				aCount++;
				lList.clear();
			}
			else if(cs[i] == 76){
				lList.add(cs[i]);
			}
			else
				lList.clear();
			if(aCount > 1 || lList.size() > 2){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(mySolution("LPLPLPLPLPL"));
	}
}
