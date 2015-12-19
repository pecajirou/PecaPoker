package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.CardSet;

public class YkPair extends Yaku {
	int pairNo;

	public int getPairNo()
	{
		return pairNo;
	}

	@Override
	protected int getYakuValue()
	{
		return 1;
	}
	public YkPair(FiveCard fv, int pn) {
		super(fv);
		pairNo = pn;
	}

	@Override
	public int compareTo(Yaku other) {
		// 役で差がつく
		if (this.getYakuValue() - other.getYakuValue() != 0) {
			return this.getYakuValue() - other.getYakuValue();
		}
		// ペアの数字で差がつく
		if (this.getPairNo() - ((YkPair)other).getPairNo() != 0){
			return this.getPairNo() - ((YkPair)other).getPairNo();
		}
		// キッカーで差がつく
		CardSet kicker1 = this.getFiveCard().diffCardSetExceptRank(this.getPairNo());
		CardSet kicker2 = other.getFiveCard().diffCardSetExceptRank(((YkPair)other).getPairNo());
		return kicker1.compareTo(kicker2);
	}

}
