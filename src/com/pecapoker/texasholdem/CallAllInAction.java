package com.pecapoker.texasholdem;

import com.pecapoker.texasholdem.HdConst.RoundStatus;

public class CallAllInAction extends Action {
	public CallAllInAction(int chip) {
		super();
		setChip(chip);
		this.roundStatus = RoundStatus.ALLINED;
	}
}
