package PlayingCards;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	private List<Card> cardList = new ArrayList<Card>();

	public Deck(boolean useJoker) {
		_initCards();
		if (useJoker) {
			;
		}
	}

	private void _initCards()
	{
		Suits[] sArray = {Suits.SPADE, Suits.HEART, Suits.DIA, Suits.CRAB};
		for(Suits s : sArray)
		{
			for(int no = 1; no <= PcConstant.NO_KING; no++)
			{
				cardList.add(new Card(s, no));
			}
		}
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

	public void shuffle() {
		cardList.sort(new CardComparator_shuffle());

	}

}
