package com.pecapoker.playingcards;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest {

	@Test
	public void testConstructor() {
		Deck deck = new Deck(false);
		assertEquals(52, deck.getNum());

		for (Suits s : PcConstant.ALL_SUITS) {
			for (int no = 1; no <= PcConstant.NO_KING; no++) {
				Card c = deck.pop();
				assertEquals(s, c.getSuits());
				assertEquals(no, c.getNo());
			}
		}

		// �S�Ĕ������� pop��null��Ԃ�
		Card c = deck.pop();
		assertEquals(null, c);
	}

	@Test
	public void testShuffle() {
		Deck deck = new Deck(false);
		deck.shuffle();

		final int NUM = deck.getNum();
		Suits beforeSuits = Suits.SPADE;
		int beforeNo = 1;
		int diffSuits = 0;
		int diffNo = 0;

		for (int i = 0; i < NUM; i++)
		{
			Card c = deck.pop();
			if (c.getSuits() != beforeSuits) {
				diffSuits++;
			}
			if (c.getNo() != beforeNo + 1)
			{
				diffNo++;
			}
			beforeSuits = c.getSuits();
			beforeNo = c.getNo();
		}
		// �P�O��ȏ�X�[�c�A�������ω����邱��
		System.out.println("diffSuits=" + diffSuits + ", diffNo=" + diffNo);
		assertEquals(true, diffSuits >= 10);
		assertEquals(true, diffNo >= 10);

	}
}
