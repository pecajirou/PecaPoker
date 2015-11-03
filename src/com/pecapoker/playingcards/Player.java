package com.pecapoker.playingcards;

public class Player extends Person {
	public Player(int id, String name)
	{
		super(id, name);
		// Playerのidは1以上　（0はディーラー)
		assert id > 0;
	}
}
