package com.pecapoker.playingcards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.poker.PokerDealer;

public class DealerTest {

	@Test
	public void testConstructor() {
		PokerDealer d = new PokerDealer();
		assertEquals(0, d.getId());
		assertEquals("Johnny", d.getName());
	}
	@Test
	public void testAddPlayer() {
		//
		// Setup
		//
		PokerDealer d = new PokerDealer();
		Player p = new Player(2, "testPlayer");
		//
		// Execute
		//
		d.addPlayer(p);

		//
		// Verify
		//
		assertEquals(1, d.getPlayers().size());
		assertEquals(2,d.getPlayers().get(d.getPlayers().size() -1).getId());
	}

	@Test
	public void testDeal()
	{
		//
		// Setup
		//
		PokerDealer d = new PokerDealer();
		Player p1 = new Player(1, "jirou");
		d.addPlayer(p1);

		assertEquals(52, d.getDeckSize());
		assertEquals(0, p1.getHandSize());

		//
		// Execute
		//
		boolean ret = d.deal(p1);

		//
		// Verify
		//
		assertEquals(true, ret);
		assertEquals(51, d.getDeckSize());
		assertEquals(1, p1.getHandSize());
	}

	@Test
	public void testDeal_fail()
	{
		//
		// Setup
		//
		PokerDealer d = new PokerDealer();
		Player p1 = new Player(1, "jirou");
		d.addPlayer(p1);

		assertEquals(52, d.getDeckSize());
		assertEquals(0, p1.getHandSize());

		//
		// 52回までは正常に配れる
		//
		for (int i = 0; i < 52; i++) {
			boolean ret = d.deal(p1);
			assertEquals(true, ret);
		}
		assertEquals(0, d.getDeckSize());
		assertEquals(52, p1.getHandSize());

		//
		// Execute
		// 53回目は失敗
		//
		boolean ret = d.deal(p1);
		assertEquals(false, ret);
		assertEquals(0, d.getDeckSize());
		assertEquals(52, p1.getHandSize());

	}


	@Test
	/**
	 * 二人に２枚ずつ配るテスト
	 */
	public void testDealAllPlayers()
	{
		//
		// Setup
		//
		PokerDealer d = new PokerDealer();
		Player p1 = new Player(1, "jirou");
		Player p2 = new Player(2, "saburou");
		d.addPlayer(p1);
		d.addPlayer(p2);

		assertEquals(52, d.getDeckSize());

		//
		// Execute
		//
		boolean ret = d.dealAllPlayers();
		assertEquals(true, ret);
		ret = d.dealAllPlayers();
		assertEquals(true, ret);


		//
		// Verify
		//
		assertEquals(48, d.getDeckSize());
		assertEquals(2, p1.getHandSize());
		assertEquals(2, p2.getHandSize());

	}

	@Test
	/**
	 * 全員に配りきれないとき、半端な人数には配らない
	 */
	public void testDealAllPlayers_fail()
	{
		//
		// Setup
		//
		PokerDealer d = new PokerDealer();
		Player p1 = new Player(1, "jirou");
		Player p2 = new Player(2, "saburou");
		d.addPlayer(p1);
		d.addPlayer(p2);

		assertEquals(52, d.getDeckSize());

		//
		// 25枚ずつ配る
		//
		for(int i = 0; i < 25; i++) {
			boolean ret = d.dealAllPlayers();
			assertEquals(true, ret);
		}
		assertEquals(2, d.getDeckSize());
		assertEquals(25, p1.getHandSize());
		assertEquals(25, p2.getHandSize());

		//
		// Execute
		//
		d.deal(p2);
		assertEquals(1, d.getDeckSize());
		boolean ret = d.dealAllPlayers();

		//
		// Verify
		//
		assertEquals(false, ret);
		assertEquals(1, d.getDeckSize());
		assertEquals(25, p1.getHandSize());
		assertEquals(26, p2.getHandSize());
		//
	}
}
