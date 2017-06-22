package com.coderising.ocp;

public class RawLogProcessor implements LogProcessor {

	@Override
	public String process(String msg) {
		return msg;
	}

}
