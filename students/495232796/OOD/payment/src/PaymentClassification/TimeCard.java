package PaymentClassification;

import java.util.Date;

public class TimeCard {
	private Date date;
	private int startTime;
	private int endTime;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getHours() {
		return endTime - startTime;
	}

}
