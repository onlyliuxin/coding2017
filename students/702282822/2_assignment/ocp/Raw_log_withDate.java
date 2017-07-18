
public class Raw_log_withDate implements Raw_log 
{
	public string format(string msg)
	{
		string msg = super.format(msg); 
		String txtDate = DateUtil.getCurrentDateAsString();
		return txtDate + ": " + msg;	
	}
	

}
