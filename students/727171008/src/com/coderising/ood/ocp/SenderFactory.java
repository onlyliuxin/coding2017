package com.coderising.ood.ocp;

public class SenderFactory {
    public Sender createSender(int type) {
	Sender sender = null;
	if(type == 1) {
	    sender = new MailSenderImp();
	}
	if (type == 2) {
	    sender = new SMSSenderImp();
	}
	if (type == 3) {
	    sender = new PrintSenderImp();
	}
	return sender;
    }
}
