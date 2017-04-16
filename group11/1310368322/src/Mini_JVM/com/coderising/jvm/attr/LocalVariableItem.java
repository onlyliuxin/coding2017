package com.coderising.jvm.attr;

public class LocalVariableItem {
	
		private int startPC;// 代表了这个局部变量在声明周期开始的时候的字节码偏移量
		private int length; // 字节码的长度
		private int nameIndex; // 局部变量名称
		private int descIndex; // 局部变量描述符
		private int index; // 局部变量在栈帧中的局部变量表中的 slot 的位置
		
		public int getStartPC() {
			return startPC;
		}
		public void setStartPC(int startPC) {
			this.startPC = startPC;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public int getNameIndex() {
			return nameIndex;
		}
		public void setNameIndex(int nameIndex) {
			this.nameIndex = nameIndex;
		}
		public int getDescIndex() {
			return descIndex;
		}
		public void setDescIndex(int descIndex) {
			this.descIndex = descIndex;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
}
