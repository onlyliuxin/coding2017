
public interface DeliveryFactory 
{
	public static Deliver createDelivery(int type)
	{
		if(type == 1)
		{
			return new Email_Deliver();			
		}
		else if(type == 2)
		{
			return new SMS_Deliver();			
		}
		else if(type == 3)
		{			
			return new Print_Deliver();
		}		
	}
	
}
