package com.pecapoker.texasholdem;

public class YkFullHouse extends Yaku {
	int trioRank;
	int pairRank;

	public int getTrioRank()
	{
		return trioRank;
	}
	public int getPairRank()
	{
		return pairRank;
	}

	@Override
	protected int getYakuValue()
	{
		return 6;
	}

	public YkFullHouse(FiveCard fv, int tRank, int pRank) {
		super(fv);
		trioRank = tRank;
		pairRank = pRank;
	}

	@Override
	public int compareTo(Yaku other) {
		// 役で差がつく
		if (this.getYakuValue() - other.getYakuValue() != 0) {
			return this.getYakuValue() - other.getYakuValue();
		}
		// 3枚のほうの数字で差がつく
		if (this.getTrioRank() - ((YkFullHouse)other).getTrioRank() != 0){
			return this.getTrioRank() - ((YkFullHouse)other).getTrioRank();
		}
		// 小さいほうのペアの数字で差がつく
		if (this.getPairRank() - ((YkFullHouse)other).getPairRank() != 0){
			return this.getPairRank() - ((YkFullHouse)other).getPairRank();
		}
		assert false;
		return 0;
	}

}
