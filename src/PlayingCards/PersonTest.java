package PlayingCards;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {

	@Test
	public void testConstructor() {
		Person psn = new Person(0, "abc");
		assertEquals(0, psn.getId());
		assertEquals("abc", psn.getName());
	}

	@Test
	public void testDraw() {
		//
		// Setup
		//
		Person psn = new Person(0, "abc");
		Deck d = new Deck(false);

		//
		// Exec
		//
		assertEquals(52, d.getNum());
		assertEquals(0, psn.getHand().getNum());
		Card c = psn.drawFrom(d);

		//
		// Verify
		//
		assertEquals(true, c != null);
		assertEquals(51, d.getNum());
		assertEquals(1, psn.getHand().getNum());

	}
}
