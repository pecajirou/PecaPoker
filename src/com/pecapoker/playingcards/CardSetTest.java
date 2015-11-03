package com.pecapoker.playingcards;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardSetTest {

	@Test
	public void testConstructor() {
		CardSet cs = new CardSet();
		assertEquals(0, cs.size());

		// Cardがなければnullを返す
		Card c = cs.pop();
		assertEquals(null, c);
	}

	@Test
	public void testPushPop() {
		CardSet cs = new CardSet();
		assertEquals(0, cs.size());

		Card c = new Card(Suits.CRAB, 1);

		cs.push(c);
		assertEquals(1, cs.size());

		Card c2 = cs.pop();
		assertEquals(c.getNo(), c2.getNo());
		assertEquals(c.getSuits(), c2.getSuits());
	}
}
