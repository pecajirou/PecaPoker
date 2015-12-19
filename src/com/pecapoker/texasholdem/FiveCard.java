package com.pecapoker.texasholdem;

import java.util.ArrayList;
import java.util.List;

import com.pecapoker.playingcards.Card;
import com.pecapoker.playingcards.CardSet;
import com.pecapoker.playingcards.RankCount;

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
		List<RankCount> rankCountList = _getRankCountList();
		if (rankCountList.size() >= 1 && rankCountList.get(0).count >= 4) {
			return new YkFourOfAKind(this, rankCountList.get(0).rank);
		}
		if (rankCountList.size() >= 2 &&
				(rankCountList.get(0).count >= 3 || rankCountList.get(1).count >= 3)) {
			int trioRank, pairRank;
			if (rankCountList.get(0).count >= 3) {
				trioRank = rankCountList.get(0).rank;
				pairRank = rankCountList.get(1).rank;
			}
			else {
				trioRank = rankCountList.get(1).rank;
				pairRank = rankCountList.get(0).rank;
			}
			return new YkFullHouse(this, trioRank, pairRank);
		}
		if (rankCountList.size() >= 1 && rankCountList.get(0).count >= 3) {
			return new YkThreeOfAKind(this, rankCountList.get(0).rank);
		}
		if (rankCountList.size() >= 2) {
			return new YkTwoPair(this, rankCountList.get(0).rank, rankCountList.get(1).rank);
		}
		if (rankCountList.size() >= 1) {
			return new YkPair(this, rankCountList.get(0).rank);
		}
		return new Yaku(this);
	}

	/**
	 * ランク毎に枚数を数える
	 * @return
	 */
	private List<RankCount> _getRankCountList() {
		List<RankCount> rankCountList = new ArrayList<RankCount>();

		int rankArray[] = new int[14];
		for(Card c : this.getCardList())
		{
			rankArray[c.getRank()]++;
		}
		for(int i = 1 ; i < rankArray.length; i++) {
			if (rankArray[i] >= 2) {
				rankCountList.add(new RankCount(i, rankArray[i]));
			}
		}
		return rankCountList;
	}

	public Yaku getYaku() {
		return yaku;
	}
}
