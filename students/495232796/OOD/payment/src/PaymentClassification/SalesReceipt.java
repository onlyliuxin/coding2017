package PaymentClassification;

import java.util.Date;

public class SalesReceipt {
	private Date date;
	private double amount;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
