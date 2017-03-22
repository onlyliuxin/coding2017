package com.coding.basic;

public class LinkNode {
	private LinkNode pNextPtr;
	private LinkNode pPrvPtr;
	private Object ndata;
	
	public LinkNode ()
	{
		this.pNextPtr=null;
		this.pPrvPtr=null;
		this.ndata=null;
	}
	
	public LinkNode (Object o, LinkNode pPrvPtr,  LinkNode pNextPtr)
	{
		this.pNextPtr=null;
		this.pPrvPtr=null;
		this.ndata=null;
	}
	
	public void setNext(LinkNode node)
	{
		this.pNextPtr = node;
	}
	
	public void setPrv(LinkNode node)
	{
		this.pPrvPtr = node;
	}
	
	public LinkNode getNext()
	{
		return this.pNextPtr;
	}
	
	public LinkNode getPrv()
	{
		return this.pPrvPtr;
	}
	public Object getData()
	{
		return this.ndata;
	}

}
