package com.coderising.jvm.attr;

public class LocalVariableTable extends AttributeInfo {

	private int localVarTableLength ;//u2 local_variable_table_length
	
	
	private static class 
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
