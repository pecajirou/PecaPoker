package PlayingCards;

import java.security.SecureRandom;
import java.util.Comparator;

public class CardComparator_shuffle implements Comparator<Card>{
	//��r���\�b�h
    public int compare(Card a, Card b) {
    	SecureRandom rnd = new SecureRandom();
    	return rnd.nextInt(2) - 1;
    }

}
