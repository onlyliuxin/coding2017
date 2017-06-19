package yangsongbao.utils;

public class MailUtil {

	public static boolean sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost) {
		try {
            //假装发了一封邮件
            StringBuilder buffer = new StringBuilder();
            buffer.append("From:").append(fromAddress).append("\n");
            buffer.append("To:").append(toAddress).append("\n");
            buffer.append("Subject:").append(subject).append("\n");
            buffer.append("Content:").append(message).append("\n");
            System.out.print(buffer.toString());
            return true;
        } catch (Exception e){
            System.out.println("发送邮件失败，" + e.getMessage());
            return false;
        }
	}

	
}
