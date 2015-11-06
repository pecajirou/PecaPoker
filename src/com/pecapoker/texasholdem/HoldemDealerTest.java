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
	HoldemPlayer p3;

	public void _initPersons()
	{
		this.d = new HoldemDealer();
		this.p1 = new HoldemPlayer(1, "jirou");
		this.p2 = new HoldemPlayer(2, "saburou");
		this.p3 = new HoldemPlayer(3, "shirou");
		d.addPlayer(this.p1);
		d.addPlayer(this.p2);
		d.addPlayer(this.p3);
	}

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
		// どっちもカードを持っていなければdraw
		assertEquals(0, p1.getHandSize());
		assertEquals(0, p2.getHandSize());
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

		// 値の優劣があっても、foldしていたら負け
		p2.doFold();
		assertEquals(p1, d.decideWinner(p1, p2));

		// 値の優劣があっても、二人ともfoldしていたら引き分け
		p1.doFold();
		assertEquals(null, d.decideWinner(p1, p2));
}

	@Test
	/**
	 * 全プレイヤーにアクションを促す
	 * アクションしたプレイヤーは、RoundStatusが何かしら変わっている
	 */
	public void testRound() throws RoundRulesException{
		assertEquals(3, d.getPlayers().size());
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

	@Test
	public void testIsRounding() throws RoundRulesException
	{
		// 何もしていなければもう一周
		assertEquals(true, d.isRounding());

		// 全員コールしてたら終わり
		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		for(Player p : d.getPlayers())
		{
			((HoldemPlayer)p).doCall(rar);
		}

		// 全員、コールかFoldなら終わり
		assertEquals(false, d.isRounding());
		for(int i = 0; i < d.getPlayers().size(); i++)
		{
			HoldemPlayer p = (HoldemPlayer)d.getPlayers().get(i);
			if (i % 2 == 0) {
				p.doCall(rar);
			}
			else {
				p.doFold();
			}
		}
		assertEquals(false, d.isRounding());

		// 全員、コールかFoldなら終わり
		assertEquals(false, d.isRounding());
		for(int i = 0; i < d.getPlayers().size(); i++)
		{
			HoldemPlayer p = (HoldemPlayer)d.getPlayers().get(i);
			if (i == 0) {
				p.doRaise(rar, 200);
			}
			else if (i % 2 == 0) {
				p.doCall(rar);
			}
			else {
				p.doFold();
			}
		}
		assertEquals(false, d.isRounding());
	}

	@Test
	public void testInitRoundAction() throws RoundRulesException
	{
		{
			//
			// Setup
			//
			HoldemRoundActionRule rar = new HoldemRoundActionRule();
			for(int i = 0; i < d.getPlayers().size(); i++)
			{
				HoldemPlayer p = (HoldemPlayer)d.getPlayers().get(i);
				if (p == p3) {
					p.doRaise(rar, 200);
				}
				else {
					p.doCall(rar);
				}
			}

			//
			// Execute
			//
			d.initRoundAction(p3);

			//
			// Verify
			//
			assertEquals(RoundStatus.NONE, p1.getRoundStatus());
			assertEquals(RoundStatus.NONE, p2.getRoundStatus());
			assertEquals(RoundStatus.RAISED, p3.getRoundStatus());
		}

		{
			//
			// Setup
			//
			HoldemRoundActionRule rar = new HoldemRoundActionRule();
			for(int i = 0; i < d.getPlayers().size(); i++)
			{
				HoldemPlayer p = (HoldemPlayer)d.getPlayers().get(i);
				if (p == p3) {
					p.doRaise(rar, 200);
				}
				else if (p == p1) {
					p.doFold();
				}
				else {
					p.doCall(rar);
				}
			}

			//
			// Execute
			//
			d.initRoundAction(p3);

			//
			// Verify
			//
			assertEquals(RoundStatus.FOLDED, p1.getRoundStatus());
			assertEquals(RoundStatus.NONE, p2.getRoundStatus());
			assertEquals(RoundStatus.RAISED, p3.getRoundStatus());
		}
	}
}
