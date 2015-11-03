package com.pecapoker.playingcards;

public class Dealer extends Person {
	private Deck deck;
	private PlayerList players;
	public Dealer () {
		super(0, "Johnny");
		deck = new Deck(false);
		players = new PlayerList();
	}
	public Deck getDeck() {
		return deck;
	}
	public void shuffle() {
		deck.shuffle();
	}

	public void addPlayer(Player p) {
		this.getPlayers().add(p);
	}
	public PlayerList getPlayers() {
		return players;
	}

}
