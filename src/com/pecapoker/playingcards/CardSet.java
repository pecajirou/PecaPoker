package com.pecapoker.playingcards;

import java.util.ArrayList;
import java.util.List;

public class CardSet {
	protected List<Card> cardList = new ArrayList<Card>();

	public CardSet() {
	}

	public int size(){
		return cardList.size();
	}

	public List<Card> getCardList(){
		return cardList;
	}

	public Card pop() {
		if (cardList.size() > 0)
		{
			Card c = cardList.get(0);
			cardList.remove(0);
			return c;
		}
		else {
			return null;
		}
	}

	public void push(Card c) {
		cardList.add(0, c);
	}

	public void shuffle() {
		cardList.sort(new CardComparator_shuffle());

	}

}
