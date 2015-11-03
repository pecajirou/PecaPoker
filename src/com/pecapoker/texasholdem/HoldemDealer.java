package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.Action;
import com.pecapoker.playingcards.Player;
import com.pecapoker.playingcards.Pot;

class HoldemDealer extends com.pecapoker.playingcards.Dealer {
	public HoldemDealer()
	{
		super();
		roundActionRule = new HoldemRoundActionRule();
	}

	@Override
	protected Player decideWinner(Player p1, Player p2) {
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

	public Player judgeWinner() {
		// TODO ２人以上で判定
		// TODO 引き分けはどちらも勝ち
		Player winner = decideWinner(players.get(0), players.get(1));
		if (winner != null) {
			winner.receiveChip(pot.getChip());
		}
		else {
			int val = pot.getChip() / 2;
			players.get(0).receiveChip(val);
			players.get(1).receiveChip(val);
		}
		return winner;
	}
	/**
	 * 全プレイヤーにアクションさせる
	 */
	public void round() throws RoundRulesException {
		for(Player p : players) {
			Action ac = p.getRoundAction(roundActionRule);
			pot.addChip(ac.getChip());
		}
	}

	public void initRound() {
		for(Player p : players) {
			((HoldemPlayer)p).resetRoundStatus();
		}
		((HoldemRoundActionRule)roundActionRule).setCallAmount(100);
	}

	public void resetHand() {
		for(Player p : players) {
			p.resetHand();
		}
		pot = new Pot();
	}


}
