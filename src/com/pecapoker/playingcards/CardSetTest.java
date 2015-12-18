package com.pecapoker.playingcards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.playingcards.PcConst.Suits;

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

	@Test
	public void testGetCardSetExcept() {
		CardSet cs = new CardSet();
		Card c = new Card(Suits.CRAB, 1);
		cs.push(c);
		c = new Card(Suits.CRAB, 2);
		cs.push(c);
		c = new Card(Suits.CRAB, 3);
		cs.push(c);
		c = new Card(Suits.CRAB, 4);
		cs.push(c);
		c = new Card(Suits.CRAB, 5);
		cs.push(c);
		c = new Card(Suits.CRAB, 6);
		cs.push(c);
		c = new Card(Suits.CRAB, 7);
		cs.push(c);

		// cs = 7654321の7枚が作られている
		// 先頭から２枚　７、６を抜く
		CardSet cs5 = cs.getFiveCardExcept(0, 1);
		assertEquals(5, cs5.size());
		assertEquals(Suits.CRAB, cs5.getCardList().get(0).getSuits());
		assertEquals(1, cs5.getCardList().get(0).getNo());
		assertEquals(2, cs5.getCardList().get(1).getNo());
		assertEquals(3, cs5.getCardList().get(2).getNo());
		assertEquals(4, cs5.getCardList().get(3).getNo());
		assertEquals(5, cs5.getCardList().get(4).getNo());

		// 末尾から２枚　１、２を抜く
		cs5 = cs.getFiveCardExcept(5, 6);
		assertEquals(5, cs5.size());
		assertEquals(Suits.CRAB, cs5.getCardList().get(0).getSuits());
		assertEquals(3, cs5.getCardList().get(0).getNo());
		assertEquals(4, cs5.getCardList().get(1).getNo());
		assertEquals(5, cs5.getCardList().get(2).getNo());
		assertEquals(6, cs5.getCardList().get(3).getNo());
		assertEquals(7, cs5.getCardList().get(4).getNo());


	}
}
