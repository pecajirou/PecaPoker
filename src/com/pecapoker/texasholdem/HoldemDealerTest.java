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
		assertEquals(null, d.decideWinner2players(p1, p2));

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
		assertEquals(p2, d.decideWinner2players(p1, p2));

		Card c2 = new Card(Suits.CRAB, 3);
		p1.receiveHand(c2);
		assertEquals(p1, d.decideWinner2players(p1, p2));

		//Aが一番強い
		Card c3 = new Card(Suits.CRAB, 1);
		p2.receiveHand(c3);
		assertEquals(p2, d.decideWinner2players(p1, p2));

		// 値の優劣があっても、foldしていたら負け
		p2.doFold();
		assertEquals(p1, d.decideWinner2players(p1, p2));

		// 値の優劣があっても、二人ともfoldしていたら引き分け
		p1.doFold();
		assertEquals(null, d.decideWinner2players(p1, p2));
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
		d.initRoundStatus(null);
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

		d.initRoundStatus(null);
		for (Player p : d.getPlayers()) {
			assertEquals(RoundStatus.NONE, ((HoldemPlayer)p).getRoundStatus());
		}
	}

	/**
	 * Raise, Call をinitRoundする
	 * @throws RoundRulesException
	 */
	@Test
	public void testInitRoundAction() throws RoundRulesException
	{
		//
		// Setup
		//
		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		rar.setCallAmount(100);
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
		d.initRoundStatus(p3);

		//
		// Verify
		//
		assertEquals(RoundStatus.NONE, p1.getRoundStatus());
		assertEquals(100, p1.getLastAction().getChip());
		assertEquals(RoundStatus.NONE, p2.getRoundStatus());
		assertEquals(100, p2.getLastAction().getChip());
		assertEquals(RoundStatus.RAISED, p3.getRoundStatus());
		assertEquals(200, p3.getLastAction().getChip());
	}

	/**
	 * Raise, Call,Fold をinitRoundする
	 * @throws RoundRulesException
	 */
	@Test
	public void testInitRoundAction2() throws RoundRulesException
	{
		//
		// Setup
		//
		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		rar.setCallAmount(10);
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
		d.initRoundStatus(p3);

		//
		// Verify
		//
		assertEquals(RoundStatus.FOLDED, p1.getRoundStatus());
		assertEquals(0, p1.getLastAction().getChip());
		assertEquals(RoundStatus.NONE, p2.getRoundStatus());
		assertEquals(10, p2.getLastAction().getChip());
		assertEquals(RoundStatus.RAISED, p3.getRoundStatus());
		assertEquals(200, p3.getLastAction().getChip());
	}

	/**
	 * １ハンドの勝敗をテスト
	 * @throws RoundRulesException
	 */
	public void testConclueHand() throws RoundRulesException
	{
		//
		// Setup
		//
		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		rar.setCallAmount(100);
		p1.receiveHand(new Card(Suits.CRAB, 5));
		p1.receiveHand(new Card(Suits.CRAB, 6));
		p2.receiveHand(new Card(Suits.CRAB, 7));
		p2.receiveHand(new Card(Suits.CRAB, 8));
		p3.receiveHand(new Card(Suits.CRAB, 9));
		p3.receiveHand(new Card(Suits.CRAB, 10));

		for(int i = 0; i < d.getPlayers().size(); i++)
		{
			HoldemPlayer p = (HoldemPlayer)d.getPlayers().get(i);
			p.doCall(rar);
		}

		//
		// Execute
		//
		d.concludeHand(300);

		//
		// Verify
		//
		assertEquals(900, p1.getChip());
		assertEquals(900, p2.getChip());
		assertEquals(1200, p3.getChip());
	}

	/**
	 * １ハンドの勝敗をテスト
	 * @throws RoundRulesException
	 */
	public void testConclueHand_draw2player() throws RoundRulesException
	{
		//
		// Setup
		//
		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		rar.setCallAmount(100);
		p1.receiveHand(new Card(Suits.CRAB, 10));
		p1.receiveHand(new Card(Suits.CRAB, 6));
		p2.receiveHand(new Card(Suits.CRAB, 7));
		p2.receiveHand(new Card(Suits.CRAB, 8));
		p3.receiveHand(new Card(Suits.CRAB, 9));
		p3.receiveHand(new Card(Suits.CRAB, 10));

		for(int i = 0; i < d.getPlayers().size(); i++)
		{
			HoldemPlayer p = (HoldemPlayer)d.getPlayers().get(i);
			p.doCall(rar);
		}

		//
		// Execute
		//
		d.concludeHand(300);

		//
		// Verify
		//
		assertEquals(1050, p1.getChip());
		assertEquals(900, p2.getChip());
		assertEquals(1050, p3.getChip());
	}
}
