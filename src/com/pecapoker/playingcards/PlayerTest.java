package com.pecapoker.playingcards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.poker.PokerPlayer;

public class PlayerTest {

	@Test
	public void testConstructor() {
		PokerPlayer d = new PokerPlayer(1, "hiyoten");
		assertEquals(1, d.getId());
		assertEquals("hiyoten", d.getName());
	}
}
