package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.Card;
import com.pecapoker.playingcards.CardSet;

public class FiveCard extends CardSet {

	private Yaku yaku;
	public FiveCard(Card c1, Card c2, Card c3, Card c4, Card c5)
	{
		super();
		this.push(c5);
		this.push(c4);
		this.push(c3);
		this.push(c2);
		this.push(c1);
		yaku = _calcYaku();
	}
	private Yaku _calcYaku() {
		int pairNo = _getPairNo();
		if (pairNo > 0) {
			return new ykPair(this, pairNo);
		}
		return new Yaku();
	}
	/**
	 * はじめに見つけたペアを返す
	 * @return
	 */
	private int _getPairNo() {
		int noArray[] = new int[14];
		for(Card c : this.getCardList())
		{
			noArray[c.getNo()]++;
		}
		for(int i = 1 ; i < noArray.length; i++) {
			if (noArray[i] >= 2) {
				return i;
			}
		}
		return 0;
	}
	public Yaku getYaku() {
		return yaku;
	}
	public int compareTo(FiveCard other) {
		if (this.getYaku().compareTo(other.getYaku()) == 0) {
			return this.getHighestCard().getValue() - other.getHighestCard().getValue();
		}
		else {
			return this.getYaku().compareTo(other.getYaku());
		}
	}

}
