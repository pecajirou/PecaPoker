package com.pecapoker.texasholdem;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.playingcards.Card;
import com.pecapoker.playingcards.PcConst.Suits;

public class FiveCardTest {

	@Test
	public void testGetYaku() {
		FiveCard five = new FiveCard(new Card(Suits.CRAB, 1),
				new Card(Suits.DIA, 2),
				new Card(Suits.CRAB, 3),
				new Card(Suits.CRAB, 4),
				new Card(Suits.CRAB, 6));

		assertEquals(true, five.getYaku() instanceof Yaku);
		assertEquals(false, five.getYaku() instanceof YkPair);
		assertEquals(false, five.getYaku() instanceof YkTwoPair);

		five = new FiveCard(new Card(Suits.CRAB, 1),
				new Card(Suits.DIA, 1),
				new Card(Suits.CRAB, 3),
				new Card(Suits.CRAB, 4),
				new Card(Suits.CRAB, 6));

		assertEquals(true, five.getYaku() instanceof Yaku);
		assertEquals(true, five.getYaku() instanceof YkPair);
		assertEquals(false, five.getYaku() instanceof YkTwoPair);

		five = new FiveCard(new Card(Suits.CRAB, 1),
				new Card(Suits.DIA, 1),
				new Card(Suits.CRAB, 3),
				new Card(Suits.CRAB, 6),
				new Card(Suits.DIA, 3));

		assertEquals(true, five.getYaku() instanceof Yaku);
		assertEquals(false, five.getYaku() instanceof YkPair);
		assertEquals(true, five.getYaku() instanceof YkTwoPair);
	}

	/**
	 * 役同士の強さを比較する
	 */
	@Test
	public void testYakuCompare() {
		FiveCard buta = new FiveCard(
				new Card(Suits.CRAB, 1),
				new Card(Suits.DIA, 2),
				new Card(Suits.CRAB, 3),
				new Card(Suits.CRAB, 4),
				new Card(Suits.CRAB, 6));
		FiveCard onePair = new FiveCard(
				new Card(Suits.CRAB, 1),
				new Card(Suits.DIA, 2),
				new Card(Suits.CRAB, 3),
				new Card(Suits.DIA, 3),
				new Card(Suits.CRAB, 6));

		assertEquals(true, buta.getYaku().compareTo(onePair.getYaku()) < 0);

		FiveCard twoPair = new FiveCard(
				new Card(Suits.CRAB, 2),
				new Card(Suits.DIA, 2),
				new Card(Suits.CRAB, 3),
				new Card(Suits.DIA, 3),
				new Card(Suits.CRAB, 6));

		assertEquals(true, twoPair.getYaku().compareTo(onePair.getYaku()) > 0);
	}
}
