package com.coderising.download.impl;

import com.coderising.download.api.DownloadListener;

public class DoloadListenerImpl implements DownloadListener{
	boolean isFinish = false;
	@Override
	public void notifyFinished(boolean isfinish) {
		// TODO Auto-generated method stub
		this.isFinish = isfinish;
	}
	public boolean getIsFinished(){
		return isFinish;
	}

}
