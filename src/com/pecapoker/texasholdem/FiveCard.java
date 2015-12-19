package com.pecapoker.texasholdem;

import java.util.ArrayList;
import java.util.List;

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
		List<Integer> pairRankList = _getPairRankList();
		if (pairRankList.size() >= 2) {
			return new YkTwoPair(this, pairRankList.get(0), pairRankList.get(1));
		}
		else if (pairRankList.size() >= 1) {
			return new YkPair(this, pairRankList.get(0));
		}
		return new Yaku(this);
	}

	/**
	 * はじめに見つけたペアを返す
	 * @return
	 */
	private int _getPairRank() {
		int noArray[] = new int[14];
		for(Card c : this.getCardList())
		{
			noArray[c.getRank()]++;
		}
		for(int i = 1 ; i < noArray.length; i++) {
			if (noArray[i] >= 2) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 見つけたペアのリストを返す
	 * @return
	 */
	private List<Integer> _getPairRankList() {
		List<Integer> pairList = new ArrayList<Integer>();

		int noArray[] = new int[14];
		for(Card c : this.getCardList())
		{
			noArray[c.getRank()]++;
		}
		for(int i = 1 ; i < noArray.length; i++) {
			if (noArray[i] >= 2) {
				pairList.add(i);
			}
		}
		return pairList;
	}

	public Yaku getYaku() {
		return yaku;
	}
}
