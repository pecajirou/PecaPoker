package com.pecapoker.texasholdem;

import com.pecapoker.texasholdem.HdConst.RoundStatus;

public class Action {
	private int chip = 0;
	protected RoundStatus roundStatus = RoundStatus.NONE;

	public RoundStatus getRoundStatus() {
		return roundStatus;
	}
	public int getChip() {
		return chip;
	}

	public void setChip(int chip) {
		this.chip = chip;
	}
}
