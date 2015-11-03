package com.pecapoker.poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class PokerDealerTest {

	@Test
	public void testConstructor() {
		PokerDealer d = new PokerDealer();
		assertEquals(0, d.getId());
		assertEquals("Johnny", d.getName());
	}

}
