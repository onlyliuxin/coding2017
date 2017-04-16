package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo {

	private static String TYPE = LOCAL_VAR_TABLE;
	private int localVarTableLength;// u2 local_variable_table_length
	private List<LocalVariableItem> itemList = new ArrayList<LocalVariableItem>();

	public LocalVariableTable(int attrNameIndex, int attrLen,
			int localVarTableLength) {
		super(attrNameIndex, attrLen);
		this.localVarTableLength = localVarTableLength;
	}

	public void addItemList(LocalVariableItem item) {
		this.itemList.add(item);
	}

	@Override
	public String getType() {
		return TYPE;
	}
	public static LocalVariableTable parse(ConstantPool pool,
			ByteCodeIterator itr) {
		int attrNameIndex = itr.nextU2toInt();
		int attrLen = itr.nextU4toInt();
		int localVarTableLength = itr.nextU2toInt();
		LocalVariableTable table = new LocalVariableTable(attrNameIndex,
				attrLen, localVarTableLength);
		for (int i = 0; i < localVarTableLength; i++) {
			table.addItemList(LocalVariableItem.parse(pool, itr));
		}
		return table;
	}

	/**
	 * inner class
	 * 
	 * @author Meng
	 *
	 */
	public static class LocalVariableItem {
		@SuppressWarnings("unused")
		private int startPc;
		@SuppressWarnings("unused")
		private int length;
		@SuppressWarnings("unused")
		private int nameIndex;
		@SuppressWarnings("unused")
		private int descriptorIndex;
		@SuppressWarnings("unused")
		private int index;

		public LocalVariableItem(int startPc, int length, int nameIndex,
				int descriptorIndex, int index) {
			super();
			this.startPc = startPc;
			this.length = length;
			this.nameIndex = nameIndex;
			this.descriptorIndex = descriptorIndex;
			this.index = index;
		}

		public static LocalVariableItem parse(ConstantPool pool,
				ByteCodeIterator itr) {
			int startPc = itr.nextU2toInt();
			int length = itr.nextU2toInt();
			int nameIndex = itr.nextU2toInt();
			int descriptorIndex = itr.nextU2toInt();
			int index = itr.nextU2toInt();
			LocalVariableItem item = new LocalVariableItem(startPc, length, nameIndex, descriptorIndex, index);
			return item;
		}
	}

	/*
	 * getter setter
	 */
	public int getLocalVarTableLength() {
		return localVarTableLength;
	}

	public void setLocalVarTableLength(int localVarTableLength) {
		this.localVarTableLength = localVarTableLength;
	}

}
