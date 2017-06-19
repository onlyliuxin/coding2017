package com.coderising.ood.ocp;

public class LoggerFactory {
	public final static int EMAIL_LOG = 1;
	public final static int SMS_LOG = 2;
	public final static int PRINT_LOG = 3;

	public static BaseUtil getLogger(int logMethod) {
		BaseUtil result = null;
		switch (logMethod) {
		case EMAIL_LOG:
			result = new MailUtil();
			break;
		case SMS_LOG:
			result = new SMSUtil();
			break;
		case PRINT_LOG:
			result = new SystemUtil();
			break;
		}
		return result;
	}
}
