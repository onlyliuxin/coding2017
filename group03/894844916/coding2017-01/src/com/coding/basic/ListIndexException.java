/**
 * 
 */
package com.coding.basic;

/**
 * 序列中索引超出了数组大小的异常。
 * @author patchouli
 *
 */
public class ListIndexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7226695184275539535L;
	
	public ListIndexException(String message){
		super(message);
	}
}
