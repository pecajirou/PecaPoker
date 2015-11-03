package com.pecapoker.playingcards;

import com.pecapoker.playingcards.PcConst.Suits;

public class Card {
	private int no;
	private Suits suits;

	public Card(Suits s, int no)
	{
		this.setSuits(s);
		this.setNo(no);
	}

	public Suits getSuits() {
		return suits;
	}

	public void setSuits(Suits suits) {
		this.suits = suits;
	}

	public int getNo() {
		return no;
	}

	/**
	 * カードの強さを返す
	 * No=1の場合は14を返す。それ以外はそのままNoを返す
	 * No=1 を1と評価したい場合や、スーツに強さを持たせる場合はオーバーライドするクラスを作ること
	 * @return
	 */
	public int getValue() {
		if (no == 1) {
			return 14;
		}
		else {
			return no;
		}
	}

	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * 等価判定
	 * @param 比較相手
	 * @return スーツ、数字とも同じならtrue
	 */
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (o instanceof Card) {
			Card c = (Card)o;
			if (c.getSuits() == this.suits && c.getNo() == this.no) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 相手のカードよりも自分のほうが強いか否か
	 * @param 比較相手
	 * @return 同じなら0, 自分のほうが強ければ 正の値, 自分のほうが弱ければ負の値
	 */
	public int compareTo(Card c) {
		if (c == null) {
			return +1;
		}
		return this.getValue() - c.getValue();
	}
}

