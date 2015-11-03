package com.pecapoker.playingcards;

public class Pot {
	private int chip = 0;

	public int getChip() {
		return chip;
	}

	public void addChip(int chip) {
		this.chip += chip;
	}

}
