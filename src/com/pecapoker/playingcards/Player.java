package com.pecapoker.playingcards;

public class Player extends Person {
	private Hand hand;

	public Player(int id, String name)
	{
		super(id, name);
		// Playerのidは1以上　（0はディーラー)
		assert id > 0;
		hand = new Hand();
	}

	/**
	 * 手札の枚数を返す
	 */
	public int getHandSize() {
		return hand.size();
	}

	/**
	 * カードを一枚受け取り、手札に加える
	 * @param c
	 */
	public void receiveHand(Card c)
	{
		assert c != null;
		hand.push(c);
	}
}
