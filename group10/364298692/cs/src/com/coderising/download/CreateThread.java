package com.coderising.download;

public class CreateThread extends Thread {

	private Resource resource;
	private int length;
	
	public CreateThread(Resource resource,int length){
		this.resource = resource;
		this.length = length;
	}
	
	public void run(){
		int startPos = 0;
		while(true){
			if(startPos >= length){
				resource.setFlag(true);
				break;
			}else{
				startPos = resource.increase();
			}
		}
	}
}
