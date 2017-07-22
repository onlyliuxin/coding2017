
public class SMS_Deliver implements dileverMsg
{
	public void process(string str)
	{
		SMSUtil.send(str);
	}
	
	
}
