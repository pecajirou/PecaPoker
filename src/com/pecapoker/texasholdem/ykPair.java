package com.pecapoker.texasholdem;

public class ykPair extends Yaku {
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
	public ykPair(FiveCard fiveCard, int pn) {
		pairNo = pn;
	}

	//TODO テスト
	@Override
	public int compareTo(Yaku other) {
		if ((this.getYakuValue() - other.getYakuValue() == 0)
				&& (this.getPairNo() - ((ykPair)other).getPairNo() != 0)) {
			return this.getPairNo() - ((ykPair)other).getPairNo();
		}
		else {
			return super.compareTo(other);
		}
	}

}
