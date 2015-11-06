package com.pecapoker.playingcards;

public class Action {
	private int chip = 0;

	public int getChip() {
		return chip;
	}

	public void setChip(int chip) {
		this.chip = chip;
	}

	public boolean isRaise()
	{
		return false;
	}
}
