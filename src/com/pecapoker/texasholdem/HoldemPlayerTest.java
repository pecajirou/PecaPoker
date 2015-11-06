package com.pecapoker.texasholdem;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
		Action ac = p.doCall(rar);

		assertEquals(true, ac instanceof CallAction);
		assertEquals(100, ac.getChip());

		assertEquals(RoundStatus.CALLED, p.getRoundStatus());
		assertEquals(900, p.getChip());
	}

    @Rule
    public ExpectedException exception = ExpectedException.none();

	/**
	 * コールするとチップが減る、状態がCALLEDになる
	 */
	@Test
	public void testRaise() throws RoundRulesException {
		//
		// 普通にRaise
		//
		HoldemPlayer p = new HoldemPlayer(1, "hiyoten");
		assertEquals(1000, p.getChip());
		assertEquals(RoundStatus.NONE, p.getRoundStatus());

		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		rar.setCallAmount(100);
		Action ac = p.doRaise(rar, 200);

		assertEquals(true, ac instanceof RaiseAction);
		assertEquals(200, ac.getChip());

		assertEquals(RoundStatus.RAISED, p.getRoundStatus());
		assertEquals(800, p.getChip());

		//
		// 所持金額以上をかけたらException
		//
        exception.expect(RoundRulesException.class);
        exception.expectMessage("this.chip < amount");
        ac = p.doRaise(rar, 900);
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
		Action ac = p.doFold();

		assertEquals(true, ac instanceof FoldAction);
		assertEquals(0, ac.getChip());

		assertEquals(RoundStatus.FOLDED, p.getRoundStatus());
		assertEquals(1000, p.getChip());
	}
}
