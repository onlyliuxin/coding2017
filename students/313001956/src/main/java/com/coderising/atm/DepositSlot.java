package com.coderising.atm;

public class DepositSlot {

	public int retriveMoney() {
		int rd =  (int) (Math.random() * 10);
		return rd * 100;
	}

}
