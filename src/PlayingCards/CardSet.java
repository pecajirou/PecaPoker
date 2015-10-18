package PlayingCards;

import java.util.ArrayList;
import java.util.List;

public class CardSet {
	protected List<Card> cardList = new ArrayList<Card>();

	public CardSet() {
	}

	public int getNum(){
		return cardList.size();
	}

	public Card pop() {
		if (cardList.size() > 0)
		{
			Card c = cardList.get(0);
			cardList.remove(0);
			return c;
		}
		else {
			return null;
		}
	}

	public void push(Card c) {
		cardList.add(0, c);
	}

	public void shuffle() {
		cardList.sort(new CardComparator_shuffle());

	}

}
