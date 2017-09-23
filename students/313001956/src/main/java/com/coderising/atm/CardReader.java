package com.coderising.atm;

public class CardReader {
	//ATM atm;

	public CardReader() {
		//this.atm = atm;
	}

	public static final int CARDNUM = 12345678;

	public int getAccount() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!detectCard()) {
			//System.out.println("?????งน??");
			throw new RuntimeException("?????งน??");
		}

		int cardnum = readCardNum();
		return cardnum;
		
	}

	private int readCardNum() {
		// TODO Auto-generated method stub
		return CARDNUM;
	}

	// ????????????????????????????งแ?
	private boolean detectCard() {
		return true;
	}


	public void ejectCard() {
		// TODO Auto-generated method stub
		
	}
}
	
