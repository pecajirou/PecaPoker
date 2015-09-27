package PlayingCards;

public class Card {
	private int no;
	private Suits suits;

	public Card(Suits s, int no)
	{
		this.setSuits(s);
		this.setNo(no);
	}

	public Suits getSuits() {
		return suits;
	}

	public void setSuits(Suits suits) {
		this.suits = suits;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}

