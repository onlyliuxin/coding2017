
public class Email_Deliver implements dileverMsg
{
	public void process(string str)
	{
		MailUtil.send(logMsg);
	}
	
}
