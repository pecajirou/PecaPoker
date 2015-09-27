package PlayingCards;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

enum Suits {SPADE, HEART, DIA, CRAB}

public class CardTest {

	@Test
	public void testConstructor() {
		Card c = new Card(Suits.SPADE, 1);
		assertEquals(Suits.SPADE, c.getSuits());
		assertEquals(1, c.getNo());
	}


}
