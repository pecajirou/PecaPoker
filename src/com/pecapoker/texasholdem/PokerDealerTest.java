package com.pecapoker.texasholdem;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.playingcards.Card;
import com.pecapoker.playingcards.PcConstant.Suits;

public class PokerDealerTest {

	@Test
	public void testConstructor() {
		PokerDealer d = new PokerDealer();
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
		PokerDealer d = new PokerDealer();
		PokerPlayer p1 = new PokerPlayer(1, "jirou");
		PokerPlayer p2 = new PokerPlayer(2, "saburou");
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
		PokerDealer d = new PokerDealer();
		PokerPlayer p1 = new PokerPlayer(1, "jirou");
		PokerPlayer p2 = new PokerPlayer(2, "saburou");
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
