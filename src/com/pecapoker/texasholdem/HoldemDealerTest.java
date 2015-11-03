package com.pecapoker.texasholdem;

import org.junit.Test;

import com.pecapoker.playingcards.Card;
import com.pecapoker.playingcards.PcConst.Suits;
import com.pecapoker.playingcards.Player;
import com.pecapoker.texasholdem.HdConst.RoundStatus;

import junit.framework.TestCase;

public class HoldemDealerTest extends TestCase {
	HoldemDealer d;
	HoldemPlayer p1;
	HoldemPlayer p2;

	@Override
	protected void setUp()
	{
		_initPersons();
	}
	protected void tearDown()
	{
		;
	}

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
	public void testDecideWinner_draw() {
		// TODO 勝敗判定は仮実装
		// 値の大きいカードを持っているほうが勝ち
		HoldemDealer d = new HoldemDealer();
		HoldemPlayer p1 = new HoldemPlayer(1, "jirou");
		HoldemPlayer p2 = new HoldemPlayer(2, "saburou");
		d.addPlayer(p1);
		d.addPlayer(p2);

		assertEquals(null, d.decideWinner(p1, p2));
	}

	@Test
	/**
	 * （仮実装）
	 * 勝敗判定　カードの値が大きい方が勝ち
	 */
	public void testDecideWinner() {
		// TODO 勝敗判定は仮実装
		// 値の大きいカードを持っているほうが勝ち
		HoldemDealer d = new HoldemDealer();
		HoldemPlayer p1 = new HoldemPlayer(1, "jirou");
		HoldemPlayer p2 = new HoldemPlayer(2, "saburou");
		d.addPlayer(p1);
		d.addPlayer(p2);

		Card c1 = new Card(Suits.CRAB, 2);
		p2.receiveHand(c1);
		assertEquals(p2, d.decideWinner(p1, p2));

		Card c2 = new Card(Suits.CRAB, 3);
		p1.receiveHand(c2);
		assertEquals(p1, d.decideWinner(p1, p2));

		//Aが一番強い
		Card c3 = new Card(Suits.CRAB, 1);
		p2.receiveHand(c3);
		assertEquals(p2, d.decideWinner(p1, p2));
	}

	public void _initPersons()
	{
		this.d = new HoldemDealer();
		this.p1 = new HoldemPlayer(1, "jirou");
		this.p2 = new HoldemPlayer(2, "saburou");
		d.addPlayer(this.p1);
		d.addPlayer(this.p2);
	}

	@Test
	/**
	 * 全プレイヤーにアクションを促す
	 * アクションしたプレイヤーは、RoundStatusが何かしら変わっている
	 */
	public void testRound() throws RoundRulesException{
		assertEquals(2, d.getPlayers().size());
		//
		// Setup
		//
		d.initRound();
		for (Player p : d.getPlayers()) {
			assertEquals(RoundStatus.NONE, ((HoldemPlayer)p).getRoundStatus());
		}

		//
		// Execute
		//
		d.round();

		//
		// Verify
		//
		for (Player p : d.getPlayers()) {
			assertEquals(true, RoundStatus.NONE != ((HoldemPlayer)p).getRoundStatus());
		}

		d.initRound();
		for (Player p : d.getPlayers()) {
			assertEquals(RoundStatus.NONE, ((HoldemPlayer)p).getRoundStatus());
		}
	}

}
