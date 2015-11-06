package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.Action;

public class RaiseAction extends Action {
	public RaiseAction(int chip) {
		super();
		setChip(chip);
	}

	@Override
	public boolean isRaise()
	{
		return true;
	}

}
