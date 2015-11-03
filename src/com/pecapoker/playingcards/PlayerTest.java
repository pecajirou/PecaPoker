package com.pecapoker.playingcards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.playingcards.PcConstant.Suits;

public class PlayerTest {

	@Test
	public void testConstructor() {
		Player d = new Player(1, "hiyoten");
		assertEquals(1, d.getId());
		assertEquals("hiyoten", d.getName());
	}

	/**
	 * 一番大きい手札を返すテスト
	 */
	@Test
	public void testGetHighestCard() {
		Player p = new Player(1, "hiyoten");

		assertEquals(null, p.getHighestCard());

		Card c1 = new Card(Suits.CRAB, 7);
		p.receiveHand(c1);
		assertEquals(c1, p.getHighestCard());

		Card c2 = new Card(Suits.DIA, 2);
		p.receiveHand(c2);
		assertEquals(c1, p.getHighestCard());

		Card c3 = new Card(Suits.HEART, 8);
		p.receiveHand(c3);
		assertEquals(c3, p.getHighestCard());
	}

}
