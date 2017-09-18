package com.coderising.dp.chain;

public class Test {

	public static void main(String[] args) {
		Logger logger = new StdoutLogger(Logger.DEBUG)
				.setNextLogger(new EmailLogger(Logger.NOTICE).setNextLogger(new FileLogger(Logger.ERR)));
		logger.message("进入函数计算", Logger.DEBUG);

		logger.message("第一步已经完成", Logger.NOTICE);

		logger.message("一个致命的错误发生了", Logger.ERR);
	}

}
