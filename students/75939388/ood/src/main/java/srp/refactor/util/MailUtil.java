package srp.refactor.util;

public class MailUtil {

	private static final String TO_ADDRESS_KEY = "toAddress";
	private static final String MAIL_KEY = "mail";

	public static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
			boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.print(buffer.toString());
		
	}


}
