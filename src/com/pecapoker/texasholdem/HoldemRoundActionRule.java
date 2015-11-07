package com.pecapoker.texasholdem;

public class HoldemRoundActionRule {
	private int callAmount = 0;

	public int getCallAmount() {
		return callAmount;
	}

	public void setCallAmount(int callAmount) {
		this.callAmount = callAmount;
	}

	public int getMinRaiseAmount() {
		return callAmount * 2;
	}
}
