/**
 * 
 */
package com.coderising.ood.srp;

/**
 * @author funkyxym
 *
 */
public abstract class Theme {
	private String ID = ""; 
	private String Desc = "";
		
	protected void setID(String ID) 
	{ 
		this.ID = ID; 		
	} 
	
	protected String getID() 
	{
		return ID; 
	}
		
	protected void setDesc(String desc) {
		this.Desc = desc;		
	}
	
	protected String getDesc(){
		return Desc;		
	}
}
