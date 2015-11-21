package com.pecapoker.texasholdem;

import com.pecapoker.texasholdem.HdConst.RoundStatus;

public class CallAction extends Action {
	public CallAction(int chip) {
		super();
		setChip(chip);
		this.roundStatus = RoundStatus.CALLED;
	}

	@Override
	public boolean isCall()
	{
		return true;
	}
}
