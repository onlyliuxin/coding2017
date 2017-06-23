package edu.coerscnu.ood.ocp.logger.method;

public class Print implements LogMethod {

	@Override
	public void send(String logMsg) {
		System.out.println("Print:" + logMsg);
	}
}
