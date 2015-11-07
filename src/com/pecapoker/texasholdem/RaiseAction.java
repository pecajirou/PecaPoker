package com.pecapoker.texasholdem;

import com.pecapoker.texasholdem.HdConst.RoundStatus;

public class RaiseAction extends Action {
	public RaiseAction(int chip) {
		super();
		setChip(chip);
		this.roundStatus = RoundStatus.RAISED;
	}
}
