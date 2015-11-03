package com.pecapoker.playingcards;

public class Person {

	private int id;
	private String name;
	private Hand hand;

	public Person(int id, String name)
	{
		this.setId(id);
		this.setName(name);
		this.hand = new Hand();
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public Card drawFrom(Deck d) {
		Card c = d.pop();
		hand.push(c);
		return c;
	}

	public CardSet getHand() {
		return this.hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}
}
