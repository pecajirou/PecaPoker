package com.pecapoker.texasholdem;

import static org.junit.Assert.*;

import org.junit.Test;

public class HoldemPlayerTest {

	@Test
	public void testConstructor() {
		HoldemPlayer d = new HoldemPlayer(1, "hiyoten");
		assertEquals(1, d.getId());
		assertEquals("hiyoten", d.getName());
	}
}
