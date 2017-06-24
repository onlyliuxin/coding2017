package edu.coerscnu.ood.ocp.client;

import edu.coerscnu.ood.ocp.logger.Logger;
import edu.coerscnu.ood.ocp.logger.method.Mail;
import edu.coerscnu.ood.ocp.logger.method.LogMethod;
import edu.coerscnu.ood.ocp.logger.type.RawWithDate;
import edu.coerscnu.ood.ocp.logger.type.LogType;

public class Client {
	public static void main(String[] args) {
		LogType type = new RawWithDate();
		LogMethod method = new Mail();
		Logger logger = new Logger(type, method);
		logger.log("Hello World");
	}
}
