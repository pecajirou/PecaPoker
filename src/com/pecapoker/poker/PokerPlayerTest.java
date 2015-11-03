package com.pecapoker.poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class PokerPlayerTest {

	@Test
	public void testConstructor() {
		PokerPlayer d = new PokerPlayer(1, "hiyoten");
		assertEquals(1, d.getId());
		assertEquals("hiyoten", d.getName());
	}
}
