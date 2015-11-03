package com.pecapoker.playingcards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.playingcards.PcConstant.Suits;

public class CardTest {

	@Test
	public void testConstructor() {
		Card c = new Card(Suits.SPADE, 1);
		assertEquals(Suits.SPADE, c.getSuits());
		assertEquals(1, c.getNo());
	}

	@Test
	/**
	 * 等価判定のテスト
	 */
	public void testEquals() {
		Card c1 = new Card(Suits.SPADE, 1);
		Card c2 = null;

		assertEquals(false, c1.equals(c2));

		c2 = new Card(Suits.SPADE, 1);
		assertEquals(true, c1.equals(c2));

		Card c3 = new Card(Suits.SPADE, 2);
		assertEquals(false, c1.equals(c3));

		Card c4 = new Card(Suits.CRAB, 1);
		assertEquals(false, c1.equals(c4));
	}

}
