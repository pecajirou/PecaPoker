package com.pecapoker.texasholdem;

import com.pecapoker.texasholdem.HdConst.RoundStatus;

public class RaiseAllInAction extends Action {
	public RaiseAllInAction(int chip) {
		super();
		setChip(chip);
		this.roundStatus = RoundStatus.ALLINED;
	}

}
