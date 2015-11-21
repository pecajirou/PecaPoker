package com.pecapoker.texasholdem;

import com.pecapoker.texasholdem.HdConst.RoundStatus;

public class FoldAction extends Action {
	public FoldAction(int lastChip)
	{
		super();
		this.setChip(lastChip);
		this.roundStatus = RoundStatus.FOLDED;
	}

	@Override
	public boolean isFold()
	{
		return true;
	}
}
