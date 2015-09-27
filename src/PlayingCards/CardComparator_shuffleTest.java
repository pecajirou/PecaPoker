package PlayingCards;

import static org.junit.Assert.*;

import org.junit.Test;
import java.security.SecureRandom;

public class CardComparator_shuffleTest {

	@Test
	public void testRandom() {
		SecureRandom rnd = new SecureRandom();
		int num = rnd.nextInt(100);
		System.out.println("num = " + num);

	}

}
