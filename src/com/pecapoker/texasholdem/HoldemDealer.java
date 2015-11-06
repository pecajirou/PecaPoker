package com.pecapoker.texasholdem;

import com.pecapoker.playingcards.Action;
import com.pecapoker.playingcards.Player;
import com.pecapoker.playingcards.Pot;
import com.pecapoker.texasholdem.HdConst.RoundStatus;

class HoldemDealer extends com.pecapoker.playingcards.Dealer {
	public HoldemDealer()
	{
		super();
		roundActionRule = new HoldemRoundActionRule();
	}

	@Override
	protected Player decideWinner(Player p1, Player p2) {
		// TODO 仮実装
		if ((((HoldemPlayer)p1).getRoundStatus() == RoundStatus.FOLDED)
			&& (((HoldemPlayer)p1).getRoundStatus() == RoundStatus.FOLDED) )
		{
			return null;
		}
		if (((HoldemPlayer)p1).getRoundStatus() == RoundStatus.FOLDED)
		{
			return p2;
		}
		if (((HoldemPlayer)p2).getRoundStatus() == RoundStatus.FOLDED)
		{
			return p1;
		}

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

	public void printAllPlayerHands() {
		for(Player p : players) {
			System.out.print(p + " is ");
			p.printHand();
		}
	}

	public Player judgeWinner() {
		// TODO ２人以上で判定
		// TODO 引き分けはどちらも勝ち
		printAllPlayerHands();

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

	public boolean isRounding()
	{
		int called = 0;
		int folded = 0;
		int raised = 0;
		for (Player p : players)
		{
			HoldemPlayer hp = (HoldemPlayer)p;
			if (hp.getRoundStatus() == RoundStatus.CALLED) {
				called++;
			}
			if (hp.getRoundStatus() == RoundStatus.FOLDED) {
				folded++;
			}
			if (hp.getRoundStatus() == RoundStatus.RAISED) {
				raised++;
			}
		}
		return ((called + folded + raised) < players.size());
	}

	public void initRoundAction(Player raiser)
	{
		for(Player p : players)
		{
			if (p == raiser)
			{
				continue;
			}
			if (((HoldemPlayer)p).getRoundStatus() == RoundStatus.FOLDED)
			{
				continue;
			}
			else {
				((HoldemPlayer)p).resetRoundStatus();
			}
		}
	}
	/**
	 * 全プレイヤーにアクションさせる
	 */
	public void round() throws RoundRulesException {
		while (isRounding())
		{
			// TODO 周回開始プレイヤーの選択
			for(Player p : players) {
				// TODO レイズできる条件の判定
				Action ac = p.getRoundAction(roundActionRule);
				if (ac.isRaise()) {
					initRoundAction(p);
					break;
				}
				pot.addChip(ac.getChip());
			}
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
