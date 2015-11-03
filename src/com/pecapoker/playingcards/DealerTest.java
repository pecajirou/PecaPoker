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
}
