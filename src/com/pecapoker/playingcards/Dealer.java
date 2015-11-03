package com.pecapoker.playingcards;

public class Dealer extends Person {
	private Deck deck;
	private PlayerList players;
	public Dealer () {
		super(0, "Johnny");
		deck = new Deck(false);
		players = new PlayerList();
	}
	public void shuffle() {
		deck.shuffle();
	}
	public int getDeckSize()
	{
		return this.deck.size();
	}

	public void addPlayer(Player p) {
		this.getPlayers().add(p);
	}
	public PlayerList getPlayers() {
		return players;
	}

	/**
	 * 一人のPlayerにカードを一枚配る
	 * @return true : 正常に配った false : カードの枚数が足りない
	 */
	public boolean deal(Player p)
	{
		if (this.deck.size() == 0) {
			return false;
		}
		Card c = this.deck.pop();
		p.receiveHand(c);
		return true;
	}

	/**
	 * すべてのPlayerにカードを一枚ずつ配る
	 * @return true : 正常に配った false : カードの枚数が足りない
	 */
	public boolean dealAllPlayers(){
		if (this.players.size() > this.deck.size()) {
			return false;
		}
		for (Player p : this.players)
		{
			deal(p);
		}
		return true;
	}

}
