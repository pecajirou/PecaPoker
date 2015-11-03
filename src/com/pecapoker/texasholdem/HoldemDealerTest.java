package com.pecapoker.texasholdem;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.playingcards.Card;
import com.pecapoker.playingcards.PcConstant.Suits;

public class HoldemDealerTest {

	@Test
	public void testConstructor() {
		HoldemDealer d = new HoldemDealer();
		assertEquals(0, d.getId());
		assertEquals("Johnny", d.getName());
	}

	@Test
	/**
	 * （仮実装）
	 * 勝敗判定　引き分け
	 */
	public void testJudgeWinner_draw() {
		// TODO 勝敗判定は仮実装
		// 値の大きいカードを持っているほうが勝ち
		HoldemDealer d = new HoldemDealer();
		HoldemPlayer p1 = new HoldemPlayer(1, "jirou");
		HoldemPlayer p2 = new HoldemPlayer(2, "saburou");
		d.addPlayer(p1);
		d.addPlayer(p2);

		assertEquals(null, d.judgeWinner(p1, p2));
	}

	@Test
	/**
	 * （仮実装）
	 * 勝敗判定　カードの値が大きい方が勝ち
	 */
	public void testJudgeWinner_p2() {
		// TODO 勝敗判定は仮実装
		// 値の大きいカードを持っているほうが勝ち
		HoldemDealer d = new HoldemDealer();
		HoldemPlayer p1 = new HoldemPlayer(1, "jirou");
		HoldemPlayer p2 = new HoldemPlayer(2, "saburou");
		d.addPlayer(p1);
		d.addPlayer(p2);

		Card c1 = new Card(Suits.CRAB, 2);
		p2.receiveHand(c1);
		assertEquals(p2, d.judgeWinner(p1, p2));

		Card c2 = new Card(Suits.CRAB, 3);
		p1.receiveHand(c2);
		assertEquals(p1, d.judgeWinner(p1, p2));

		//Aが一番強い
		Card c3 = new Card(Suits.CRAB, 1);
		p2.receiveHand(c3);
		assertEquals(p2, d.judgeWinner(p1, p2));
	}
}
