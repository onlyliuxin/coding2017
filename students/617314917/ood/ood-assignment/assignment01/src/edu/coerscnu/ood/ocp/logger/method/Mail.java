package edu.coerscnu.ood.ocp.logger.method;

public class Mail implements LogMethod{

	@Override
	public void send(String logMsg) {
		System.out.println("Mail:" + logMsg);
	}
	
}
