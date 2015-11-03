package com.pecapoker.poker;

import com.pecapoker.playingcards.Player;

class PokerDealer extends com.pecapoker.playingcards.Dealer {

	@Override
	public Player judgeWinner(Player p1, Player p2) {
		// TODO 仮実装
		if (p1.getHighestCard() == null && p2.getHighestCard() == null)
		{
			return null;
		}
		if (p1.getHighestCard() == null)  {
			return p2;
		}
		if (p2.getHighestCard() == null) {
			return p1;
		}

		if (p1.getHighestCard().compareTo(p2.getHighestCard()) == 0) {
			return null;
		}
		if (p1.getHighestCard().compareTo(p2.getHighestCard()) > 0) {
			return p1;
		}

		return p2;
	}


}
