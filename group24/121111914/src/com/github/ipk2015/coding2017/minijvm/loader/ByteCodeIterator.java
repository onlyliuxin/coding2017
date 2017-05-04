package com.github.ipk2015.coding2017.minijvm.loader;

import java.util.Arrays;

import com.github.ipk2015.coding2017.minijvm.util.Util;

public class ByteCodeIterator {
		private byte[] byteArray;
		int pos=0;
		
		public ByteCodeIterator(byte[] codes){
			this.byteArray=codes;
		}
		
		public int nextUNToInt(int n){
			return Util.byteToInt(nextUNToArray(n));
		}
		
		public String nextUNToHexString(int n){
			return Util.byteToHexString(nextUNToArray(n));
		}
		
		public byte[] nextUNToArray(int n){
			byte[] bytes=Arrays.copyOfRange(byteArray, pos, pos+n);
			pos=pos+n;
			return bytes;
		}
		public void back(int n) {
			this.pos -= n;
		}
}
