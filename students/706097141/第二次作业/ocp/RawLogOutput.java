package com.coderising.ood.ocp;

public class RawLogOutput extends LogOutput{

	public void logOutput(String msg,Logger logger) {
		logger.log(msg);
	}

}
