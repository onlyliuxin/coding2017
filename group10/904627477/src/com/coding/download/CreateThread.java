package com.coding.download;


import com.coding.download.api.Resource;

public class CreateThread extends Thread {
	
	private Resource resource;
	private int  length;
	
	public CreateThread(Resource resource,int length){
		this.resource = resource;
		this.length = length;
	}

	@Override
	public void run() {
		int startPos = 0;
		while(true){
			//System.out.println(startPos);
			if(startPos>=length){
				resource.setFlag(true);
				break;
			}else{
				startPos = resource.increace();
			}
		}
	}
	
	

}
