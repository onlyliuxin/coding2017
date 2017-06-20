package main.java.com.coderising.ood.srp;

public class FromAddress {

	private String fromAddress = null;
	
	protected void setFromAddress() {
		fromAddress = Configuration.getConfig().getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}

	public String getFromAddress() {
		return fromAddress;
	}

}
