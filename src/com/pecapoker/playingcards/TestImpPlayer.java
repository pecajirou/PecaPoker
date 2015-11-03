package com.pecapoker.playingcards;

public class TestImpPlayer extends Player {

	public TestImpPlayer(int id, String name) {
		super(id, name);
	}

	@Override
	public Action getRoundAction() {
		return null;
	}

}
