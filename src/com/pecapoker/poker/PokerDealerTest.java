package com.pecapoker.poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class PokerDealerTest {

	@Test
	public void test() {
		PokerDealer d = new PokerDealer();
		assertEquals("Johnny", d.getName());
		assertEquals(0, d.getId());
	}

}
