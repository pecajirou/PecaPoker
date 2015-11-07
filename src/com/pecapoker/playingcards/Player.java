package com.pecapoker.playingcards;

abstract public class Player extends Person {
	protected Hand hand;
	protected int chip = 1000;

	public Player(int id, String name)
	{
		super(id, name);
		// Playerのidは1以上　（0はディーラー)
		assert id > 0;
		hand = new Hand();
	}

	/**
	 * 手札の枚数を返す
	 */
	public int getHandSize() {
		return hand.size();
	}

	/**
	 * カードを一枚受け取り、手札に加える
	 * @param c
	 */
	public void receiveHand(Card c)
	{
		assert c != null;
		hand.push(c);
	}

	/**
	 * チップを受け取る
	 * @param チップ
	 */
	public void receiveChip(int c)
	{
		this.chip += c;
	}

	public int getChip()
	{
		return chip;
	}

	public String getHandStr()
	{
		String ret = "";
		for(Card c : this.hand.getCardList())
		{
			ret += c.toString() + " ";
		}
		return ret;
	}
	public void printHand()
	{
		System.out.println(getHandStr());;
	}

	/**
	 * 手札の中で一番大きいカードを返す スーツは問わない
	 * @return 一番大きいカード　一枚もなければnull
	 */
	public Card getHighestCard() {
		Card maxCard = null;
		for(Card c : this.hand.cardList) {
			if (maxCard == null) {
				maxCard = c;
			}
			else if (c.getValue() > maxCard.getValue()){
				maxCard = c;
			}
		}
		return maxCard;
	}

	/**
	 * 現在の手札を返却してリセット
	 * @return 現在の手札
	 */
	public Hand resetHand() {
		Hand ret = this.hand;
		this.hand = new Hand();
		return ret;
	}
}
