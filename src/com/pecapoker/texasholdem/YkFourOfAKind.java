package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.CardSet;

public class YkFourOfAKind extends Yaku {
	int fourRank;

	public int getFourRank()
	{
		return fourRank;
	}

	@Override
	protected int getYakuValue()
	{
		return 7;
	}
	public YkFourOfAKind(FiveCard fv, int rank) {
		super(fv);
		fourRank = rank;
	}

	@Override
	public int compareTo(Yaku other) {
		// 役で差がつく
		if (this.getYakuValue() - other.getYakuValue() != 0) {
			return this.getYakuValue() - other.getYakuValue();
		}
		// トリオの数字で差がつく
		if (this.getFourRank() - ((YkFourOfAKind)other).getFourRank() != 0){
			return this.getFourRank() - ((YkFourOfAKind)other).getFourRank();
		}
		// キッカーで差がつく
		CardSet kicker1 = this.getFiveCard().diffCardSetExceptRank(this.getFourRank());
		CardSet kicker2 = other.getFiveCard().diffCardSetExceptRank(((YkFourOfAKind)other).getFourRank());
		return kicker1.compareTo(kicker2);
	}
}
