package com.coderising.ood.ocp;

public class RawLogWithDateOutput extends LogOutput{

	@Override
	public void logOutput(String msg,Logger logger) {
		String txtDate = DateUtil.getCurrentDateAsString();
		String logMsg = txtDate + ": " + msg;
		logger.log(logMsg);
	}

}
