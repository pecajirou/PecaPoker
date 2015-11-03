package com.pecapoker.texasholdem;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pecapoker.playingcards.Action;
import com.pecapoker.texasholdem.HdConst.RoundStatus;

public class HoldemPlayerTest {

	@Test
	public void testConstructor() {
		HoldemPlayer p = new HoldemPlayer(1, "hiyoten");
		assertEquals(1, p.getId());
		assertEquals("hiyoten", p.getName());
	}

	/**
	 * コールするとチップが減る、状態がCALLEDになる
	 */
	@Test
	public void testCall() throws RoundRulesException {
		HoldemPlayer p = new HoldemPlayer(1, "hiyoten");
		assertEquals(1000, p.getChip());
		assertEquals(RoundStatus.NONE, p.getRoundStatus());

		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		rar.setCallAmount(100);
		Action ac = p.call(rar);

		assertEquals(true, ac instanceof CallAction);
		assertEquals(100, ac.getChip());

		assertEquals(RoundStatus.CALLED, p.getRoundStatus());
		assertEquals(900, p.getChip());
	}

	/**
	 * フォールドするとチップは減らない、状態がFOLDEDになる
	 */
	@Test
	public void testFold() throws RoundRulesException {
		HoldemPlayer p = new HoldemPlayer(1, "hiyoten");
		assertEquals(1000, p.getChip());
		assertEquals(RoundStatus.NONE, p.getRoundStatus());

		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		rar.setCallAmount(100);
		Action ac = p.fold();

		assertEquals(true, ac instanceof FoldAction);
		assertEquals(0, ac.getChip());

		assertEquals(RoundStatus.FOLDED, p.getRoundStatus());
		assertEquals(1000, p.getChip());
	}
}
