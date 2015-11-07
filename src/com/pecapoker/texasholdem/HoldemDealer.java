package com.pecapoker.texasholdem;

import java.util.ArrayList;
import java.util.List;

import com.pecapoker.playingcards.Player;
import com.pecapoker.playingcards.Pot;
import com.pecapoker.texasholdem.HdConst.RoundStatus;

class HoldemDealer extends com.pecapoker.playingcards.Dealer {
	public HoldemDealer()
	{
		super();
	}

	@Override
	protected Player decideWinner2players(Player p1, Player p2) {
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

	private int _selectWinners(List<HoldemPlayer> winners)
	{
		int activePlayerNum = 0;
		for (Player pl : this.players) {
			HoldemPlayer p = (HoldemPlayer)pl;
			if (p.isFolded()) {
				continue;
			}
			activePlayerNum++;
			if (winners.size() == 0) {
				winners.add(p);
			}
			else {
				HoldemPlayer winner = winners.get(0);
				winner = (HoldemPlayer)decideWinner2players(winner, p);
				if (winner == null) {
					winners.add(p);
				}
				else if (winner == p) {
					winners.clear();
					winners.add(p);
				}
				else {
					// 何もしない
					;
				}
			}
		}
		return activePlayerNum;
	}
	// TODO 3.AIを変える
	// TODO 4.AllIn
	// TODO 5.カードが増える＝フロップに進む
	// TODO 6.カードが増える＝ターンに進む
	// TODO 7.カードが増える＝リバーに進む
	// TODO 8.ペアの判定
	public List<HoldemPlayer> concludeHand(int potChip) {
		printAllPlayerHands();

		List<HoldemPlayer> winners = new ArrayList<HoldemPlayer>();
		int activePlayerNum = _selectWinners(winners);
		if (winners.size() > 0) {
			int dividedChip = potChip / winners.size();
			for (Player p : winners) {
				p.receiveChip(dividedChip);
			}
		}
		// 引き分け
		else {
			assert activePlayerNum > 0;
			int dividedChip = potChip / activePlayerNum;
			for (Player p : this.players) {
				if (((HoldemPlayer)p).isFolded()) {
					continue;
				}
				p.receiveChip(dividedChip);
			}
		}
		return winners;
	}

	public void initRoundStatus(Player raiser)
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
				((HoldemPlayer)p).resetActionStatusOnly();
			}
		}
	}
	/**
	 * 全プレイヤーにアクションさせる
	 */
	public void round() throws RoundRulesException {
		/**
		 * 全Playerを１周するまでのルール
		 */
		HoldemRoundActionRule rar = new HoldemRoundActionRule();
		Player raiser = null;
		rar.setCallAmount(100);
		do
		{
			initRoundStatus(raiser);
			raiser = _scanPlayers(raiser, rar);
			if (raiser != null) {
				rar.setCallAmount(((HoldemPlayer)raiser).getLastAction().getChip());
			}
		} while (raiser != null);
	}

	/**
	 * 新たなレイズが入るか、全員回るまで回す
	 * @param raiser　このプレイヤーの次の人から回る
	 * @return　レイズが入ったらそのプレイヤー
	 * @throws RoundRulesException
	 */
	private Player _scanPlayers(Player raiser, HoldemRoundActionRule rar) throws RoundRulesException
	{
		int actionedNum = 0;
		int iRaiser = _getPlayerIndex(raiser);
		if (iRaiser != -1) {
			actionedNum++;
		}
		int iPlayer = this.players.getNextIndex(iRaiser);
		assert iPlayer >= 0;

		while(actionedNum < this.players.size())
		{
			Player p = this.players.get(iPlayer);
			if (((HoldemPlayer)p).getRoundStatus() == RoundStatus.FOLDED) {
				actionedNum++;
				continue;
			}
			// TODO レイズできる条件の判定
			// TODO レイズ額の考慮　それにしたがってCALL, RAISE
			((HoldemPlayer)p).getRoundAction(rar);
			if (((HoldemPlayer)p).isRaised()) {
				return p;
			}
			iPlayer = this.players.getNextIndex(iPlayer);
			actionedNum++;
		}
		return null;
	}

	private int _getPlayerIndex(Player raiser) {
		int iPlayer = -1;
		if (raiser != null) {
			iPlayer = this.players.indexOf(raiser);
			assert iPlayer > 0;
		}
		return iPlayer;
	}

	public void resetHand() {
		for(Player p : players) {
			p.resetHand();
			((HoldemPlayer)p).resetAction();
		}
	}

	public void collectChipToPot(Pot pot) {
		for(Player p : this.players) {
			pot.addChip(((HoldemPlayer)p).getLastAction().getChip());
		}
	}


}
