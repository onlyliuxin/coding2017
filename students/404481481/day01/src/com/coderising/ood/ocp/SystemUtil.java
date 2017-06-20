package com.coderising.ood.ocp;

public class SystemUtil implements BaseUtil {

	@Override
	public void send(String msg) {
		System.out.println("send System msg :" + msg);
	}

}
