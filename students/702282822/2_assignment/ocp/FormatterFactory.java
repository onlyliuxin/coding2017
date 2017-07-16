
public class FormatterFactory {
	public static Formatter createFormate(int type)
	{
		if(type == 1)
		{
			return new Raw_log();
		}
		else if(type == 2)
		{
			return new Raw_log_withDate();		
		}		
	} 
}
