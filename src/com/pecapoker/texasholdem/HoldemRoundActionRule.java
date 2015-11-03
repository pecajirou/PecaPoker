package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.RoundActionRule;

public class HoldemRoundActionRule extends RoundActionRule {
	private int callAmount = 0;

	public int getCallAmount() {
		return callAmount;
	}

	public void setCallAmount(int callAmount) {
		this.callAmount = callAmount;
	}
}
