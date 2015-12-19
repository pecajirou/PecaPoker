package com.pecapoker.texasholdem;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.playingcards.Card;
import com.pecapoker.playingcards.CardSet;
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
		assertEquals(false, five.getYaku() instanceof YkFullHouse);
		assertEquals(false, five.getYaku() instanceof YkFourOfAKind);

		five = new FiveCard(new Card(Suits.CRAB, 1),
				new Card(Suits.DIA, 1),
				new Card(Suits.CRAB, 3),
				new Card(Suits.CRAB, 4),
				new Card(Suits.CRAB, 6));

		assertEquals(true, five.getYaku() instanceof Yaku);
		assertEquals(true, five.getYaku() instanceof YkPair);
		assertEquals(false, five.getYaku() instanceof YkTwoPair);
		assertEquals(false, five.getYaku() instanceof YkThreeOfAKind);
		assertEquals(false, five.getYaku() instanceof YkFullHouse);
		assertEquals(false, five.getYaku() instanceof YkFourOfAKind);

		five = new FiveCard(new Card(Suits.CRAB, 1),
				new Card(Suits.DIA, 1),
				new Card(Suits.CRAB, 3),
				new Card(Suits.CRAB, 6),
				new Card(Suits.DIA, 3));

		assertEquals(true, five.getYaku() instanceof Yaku);
		assertEquals(false, five.getYaku() instanceof YkPair);
		assertEquals(true, five.getYaku() instanceof YkTwoPair);
		assertEquals(false, five.getYaku() instanceof YkThreeOfAKind);
		assertEquals(false, five.getYaku() instanceof YkFullHouse);
		assertEquals(false, five.getYaku() instanceof YkFourOfAKind);

		five = new FiveCard(new Card(Suits.CRAB, 1),
				new Card(Suits.SPADE, 3),
				new Card(Suits.CRAB, 3),
				new Card(Suits.CRAB, 6),
				new Card(Suits.DIA, 3));

		assertEquals(true, five.getYaku() instanceof Yaku);
		assertEquals(false, five.getYaku() instanceof YkPair);
		assertEquals(false, five.getYaku() instanceof YkTwoPair);
		assertEquals(true, five.getYaku() instanceof YkThreeOfAKind);
		assertEquals(false, five.getYaku() instanceof YkFullHouse);
		assertEquals(false, five.getYaku() instanceof YkFourOfAKind);

		five = new FiveCard(new Card(Suits.DIA, 6),
				new Card(Suits.SPADE, 3),
				new Card(Suits.CRAB, 3),
				new Card(Suits.CRAB, 6),
				new Card(Suits.DIA, 3));

		assertEquals(true, five.getYaku() instanceof Yaku);
		assertEquals(false, five.getYaku() instanceof YkPair);
		assertEquals(false, five.getYaku() instanceof YkTwoPair);
		assertEquals(false, five.getYaku() instanceof YkThreeOfAKind);
		assertEquals(true, five.getYaku() instanceof YkFullHouse);
		assertEquals(false, five.getYaku() instanceof YkFourOfAKind);

		five = new FiveCard(new Card(Suits.HEART, 3),
				new Card(Suits.SPADE, 3),
				new Card(Suits.CRAB, 3),
				new Card(Suits.CRAB, 6),
				new Card(Suits.DIA, 3));

		assertEquals(true, five.getYaku() instanceof Yaku);
		assertEquals(false, five.getYaku() instanceof YkPair);
		assertEquals(false, five.getYaku() instanceof YkTwoPair);
		assertEquals(false, five.getYaku() instanceof YkThreeOfAKind);
		assertEquals(false, five.getYaku() instanceof YkFullHouse);
		assertEquals(true, five.getYaku() instanceof YkFourOfAKind);
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

		FiveCard threeOfAKind = new FiveCard(
				new Card(Suits.CRAB, 2),
				new Card(Suits.DIA, 2),
				new Card(Suits.CRAB, 3),
				new Card(Suits.SPADE, 2),
				new Card(Suits.CRAB, 6));

		assertEquals(true, twoPair.getYaku().compareTo(threeOfAKind.getYaku()) < 0);

		FiveCard FullHouse = new FiveCard(
				new Card(Suits.CRAB, 2),
				new Card(Suits.DIA, 2),
				new Card(Suits.CRAB, 3),
				new Card(Suits.SPADE, 2),
				new Card(Suits.SPADE, 3));

		assertEquals(true, FullHouse.getYaku().compareTo(threeOfAKind.getYaku()) > 0);

		FiveCard FourOfAKind = new FiveCard(
				new Card(Suits.CRAB, 2),
				new Card(Suits.DIA, 2),
				new Card(Suits.CRAB, 3),
				new Card(Suits.SPADE, 2),
				new Card(Suits.HEART, 2));

		assertEquals(true, FullHouse.getYaku().compareTo(FourOfAKind.getYaku()) < 0);
	}

	public void testCardCompare() {
		CardSet cs1 = new CardSet("♠4♥8♥T♠6♥4");
		CardSet cs2 = new CardSet("♠4♥8♥T♠6♠5");

		FiveCard fv1 = new FiveCard(cs1);
		FiveCard fv2 = new FiveCard(cs2);

		assertEquals(true, fv1.getYaku().compareTo(fv2.getYaku()) > 0);
	}

}
