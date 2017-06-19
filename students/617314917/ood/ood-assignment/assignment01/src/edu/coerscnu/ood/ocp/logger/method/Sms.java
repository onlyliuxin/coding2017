package edu.coerscnu.ood.ocp.logger.method;

public class Sms implements LogMethod{

	@Override
	public void send(String logMsg) {
		System.err.println("Sms:" + logMsg);
	}
	
}
